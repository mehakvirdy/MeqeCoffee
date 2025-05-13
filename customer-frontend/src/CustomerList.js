import React from 'react';

const CustomerList = ({ customers, onDelete, onEdit }) => {
    return (
        <div>
            <h2>Accounts</h2>
            <ul>
                {customers.map(customer => (
                    <li key={customer.customerId}>
                        {customer.name} | {customer.email} | {customer.phone} | {customer.rewardsPoints + ' '}
                        <button onClick={() => onDelete(customer.customerId)}>Delete</button>
                        <button onClick={() => onEdit(customer)}>Edit</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CustomerList;