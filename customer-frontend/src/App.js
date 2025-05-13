import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CustomerForm from './CustomerForm';
import CustomerList from './CustomerList';
import OrderForm from './OrderForm';
import OrderList from './OrderList';
import coffeeLogo from './coffee.jpeg';
import './App.css';

const BASE_URL = process.env.REACT_APP_BASE_URL;

const App = () => {
    const [customers, setCustomers] = useState([]);
    const [customerToEdit, setCustomerToEdit] = useState(null);

    const [orderItems, setOrderItems] = useState([]);
    const [orderItemsToEdit, setOrderItemsToEdit] = useState(null);

    const [customerOfTheMonth, setCustomerOfTheMonth] = useState({ id: null, name: '', totalSpent: 0 });

    const [minPrice, setMinPrice] = useState('');
    const [maxPrice, setMaxPrice] = useState('');
    const [filteredProducts, setFilteredProducts] = useState([]);

    const [items, setItems] = useState([]);

    // Filter pricing
    const filterProductsByPrice = () => {
        const min = parseFloat(minPrice);
        const max = parseFloat(maxPrice);

        if (!isNaN(min) && !isNaN(max)) {
            const filtered = items.filter(item => {
                const price = parseFloat(item.price);
                return !isNaN(price) && price >= min && price <= max;
            });
            setFilteredProducts(filtered);
        } else {
            console.warn('Invalid min or max price');
        }
    };

    // fetch all items
    useEffect(() => {
        axios.get(BASE_URL + '/items')
            .then(response => setItems(response.data))
            .catch(error => console.error('Error fetching items:', error));
    }, []);


    // Fetch all customers
    useEffect(() => {
        axios.get(BASE_URL + '/customers')
            .then(response => setCustomers(response.data))
            .catch(error => console.error('Error fetching customers:', error));
    }, []);

    useEffect(() => {
        axios.get(BASE_URL + '/customer-of-month')
            .then(response => {
                if (response.data) {
                    setCustomerOfTheMonth({
                        id: response.data.customerId,
                        name: response.data.customerName,
                        totalSpent: response.data.totalSpent,
                    })
                }
            })
            .catch(error => console.error('Error in customer of the month'))
    }, []);


    // fetch all orders
    useEffect(() => {
        axios.get(BASE_URL + '/orderitems')
            .then(response => setOrderItems(response.data))
            .catch(error => console.error('Error fetching order items:', error));
    }, []);

    // add new customer
    const addCustomer = (customer) => {
        axios.post(BASE_URL + '/customers', customer)
            .then(response => setCustomers([...customers, response.data]))
            .catch(error => console.error('Error adding customer:', error));
    };

    // edit customer
    const editCustomer = (customer) => {
        axios.put(BASE_URL + `/customers/${customer.id}`, customer)
            .then(() => axios.get(BASE_URL + '/customers'))
            .then(response => setCustomers(response.data))
            .catch(error => console.error('Error editing customer:', error));
    };

    // delete customer
    const deleteCustomer = (customerId) => {
        axios.delete(BASE_URL + `/customers/${customerId}`)
            .then(() => {
                // Optionally refetch customers from the server after deletion to sync data
                axios.get(BASE_URL + '/customers')
                    .then(response => setCustomers(response.data))
                    .catch(error => console.error('Error fetching customers after deletion:', error));

                axios.get(BASE_URL + '/orderitems')
                    .then(response => setOrderItems(response.data))
                    .catch(error => console.error('Error fetching order items after deletion:', error));
            })
            .catch(error => console.error('Error deleting customer:', error));
    };

    // Add new order
    const addOrder = (orderItem) => {
        console.log(' in addOrder(): ', orderItem); // this works, there is a proper object
        axios.post(BASE_URL + '/orderitems', orderItem)
            .then(response => {
                console.log("New Order:", response.data);  // this is not printed
                setOrderItems([...orderItems, response.data]);
            })
            .catch(error => console.error('Error adding order:', error));
    };

    // Edit order
    const editOrder = (orderItem) => {
        axios.put(BASE_URL + `/orderitems/${orderItem.itemID}`, orderItem)
            .then(() => axios.get(BASE_URL + '/orderitems'))
            .then(response => setOrderItems(response.data))
            .catch(error => console.error('Error editing order:', error));
    };

    // Delete order
    const deleteOrder = (orderItem) => {
        axios.delete(BASE_URL + `/orderitems/${orderItem.itemID}`)
            .then(() => axios.get(BASE_URL + '/orderitems'))
            .catch(error => console.error('Error deleting order:', error));
    };

    return (
        <div className="App">
            <h1>Welcome to Meqe's Coffee Shop!</h1>
            <img src={coffeeLogo} className="App-logo" alt="coffee logo"/>

            <CustomerForm onAdd={addCustomer} onEdit={editCustomer} customerToEdit={customerToEdit} />
            <CustomerList customers={customers} onDelete={deleteCustomer} onEdit={setCustomerToEdit} />

            <OrderForm onAdd={addOrder} onEdit={editOrder} orderToEdit={orderItemsToEdit} />
            <OrderList orders={orderItems || []} customers = {customers} onDelete={deleteOrder} onEdit={setOrderItemsToEdit} />

            {/* Product Selection by Price Range */}
            <div className="product-selection">
                <h2>Product Selection</h2>
                <label>
                    Min Price:
                    <input type="number" value={minPrice} onChange={(e) => setMinPrice(e.target.value)} />
                </label>
                <label>
                    Max Price:
                    <input type="number" value={maxPrice} onChange={(e) => setMaxPrice(e.target.value)} />
                </label>
                <button onClick={filterProductsByPrice}>Search</button>
                <ul>
                    {filteredProducts.map((item, idx) => (
                        <li key={idx}>
                            {item.name} – ${item.price.toFixed(2)}
                        </li>
                    ))}
                </ul>
            </div>

            {/* Customer of the Month Section */}
            <div className="customer-of-the-month">
                <h2>Customer of the Month</h2>
                {customerOfTheMonth.id ? (
                    <p>
                        <strong>{'Congrats ' + customerOfTheMonth.name + '!'}</strong> spent a total of <strong>${customerOfTheMonth.totalSpent.toFixed(2)}</strong>
                    </p>
                ) : (
                    <p>No customer data available.</p>
                )}
            </div>
        </div>
    );
};

export default App;