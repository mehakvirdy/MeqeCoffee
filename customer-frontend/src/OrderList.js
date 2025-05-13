import React from 'react';

const OrderList = ({ orders, customers, onDelete, onEdit }) => {
    return (
        <div>
            <h2>Orders</h2>
            <ul>
                {orders
                    .filter(order => customers.some(c => c.customerId === order.customerId))
                    .map(order => {
                    // Find the customer by customerId
                    const customer = customers.find(c => c.customerId === order.customerId);
                    const customerName = customer ? customer.name : 'Unknown Customer'; // Fallback if customer is not found

                    return (
                        <li key={order.itemID}>
                            {order.item.name} (x{order.quantity}) | customer: {customerName + ' '}
                            <button onClick={() => onEdit(order)}>Edit</button>
                            <button onClick={() => onDelete(order.id)}>Delete</button>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
};

export default OrderList;