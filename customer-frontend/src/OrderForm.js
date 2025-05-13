import React, { useState, useEffect } from 'react';

const BASE_URL = process.env.REACT_APP_BASE_URL;
const OrderForm = ({ onAdd, onEdit, orderItemToEdit }) => {
    const [orderItem, setOrderItem] = useState({ customerId: '', product: '', quantity: 1, email: '', dateTime: new Date().toISOString() });
    const [items, setItems] = useState([]); // store items from the backend
    const [customers, setCustomers] = useState([]);

    useEffect(() => {
        // fetch items from the backend
        const fetchItems = async () => {
            try {
                const response = await fetch(BASE_URL + '/items');
                if (response.ok) {
                    const data = await response.json();
                    setItems(data); // set the fetched items in the state
                } else {
                    console.error('Failed to fetch items');
                }
            } catch (error) {
                console.error('Error fetching items:', error);
            }
        };

        const fetchCustomers = async () => {
            try {
                const response = await fetch(BASE_URL + '/customers');
                if (response.ok) {
                    const data = await response.json();
                    setCustomers(data); // set the fetched items in the state
                } else {
                    console.error('Failed to fetch customers');
                }
            } catch (error) {
                console.error('Error fetching customers:', error);
            }
        };

        fetchItems();
        fetchCustomers()

        if (orderItemToEdit) {
            setOrderItem(orderItemToEdit);
        }
    }, [orderItemToEdit]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setOrderItem(prevState => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            if (!orderItem.email) throw new Error("Email is required");

            // Find the customer by email
            const customer = customers.find(c => c.email === orderItem.email);
            if (!customer) throw new Error("Customer not found");

            // Now we have the customerId
            const customerId = customer.customerId;

            // Find the product
            const product = items.find(item => item.itemId === parseInt(orderItem.product, 10));
            if (!product) throw new Error("Selected product not found");

            // Ensure correct data types & structure
            const updatedOrderItem = {
                item: {
                    itemId: product.itemId,
                    name: product.name,
                    price: product.price,
                    description: product.description
                },
                quantity: parseInt(orderItem.quantity, 10), // Ensure quantity is an integer
                customerId: customerId, // Use the found customerId
                dateTime: orderItem.dateTime // Include the dateTime from state
            };

            if (orderItemToEdit) {
                onEdit(updatedOrderItem);
            } else {
                onAdd(updatedOrderItem);
            }

            // reset form, include dateTime for future submissions
            setOrderItem({ email: '', product: '', quantity: 1, customerId: '', dateTime: new Date().toISOString()  });

        } catch (error) {
            console.error("Error processing order:", error.message);
            alert(error.message);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="email" placeholder="Email" value={orderItem.email} onChange={handleChange} required />
            <select name="product" value={orderItem.itemID} onChange={handleChange} required>
                <option value=""> SELECT ONE </option>
                {items.map(item => (
                    <option key={item.itemId} value={item.itemId}>
                        {item.name} -- {'$' + item.price}
                    </option>
                ))}
            </select>
            <input type="number" name="quantity" placeholder="Quantity" value={orderItem.quantity} onChange={handleChange} required />
            <button type="submit">{orderItemToEdit ? 'Update Order' : 'Add Order'}</button>
        </form>
    );
};

export default OrderForm;