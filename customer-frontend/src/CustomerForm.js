import React, { useState, useEffect } from 'react';

const CustomerForm = ({ onAdd, onEdit, customerToEdit }) => {
    // Initialize state for form fields
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [id, setId] = useState(null); // state for customer ID

    // If customerToEdit is provided, populate the form fields with the customer data
    useEffect(() => {
        if (customerToEdit) {
            console.log('customer to edit: ', customerToEdit);
            setId(customerToEdit.customerId);  // set the customer ID
            setName(customerToEdit.name);
            setEmail(customerToEdit.email);
            setPhone(customerToEdit.phone);
        }
    }, [customerToEdit]);

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        const customer = { id, name, email, phone };

        if (customerToEdit) {
            // edit the existing customer with the existing ID
            customer.id = id;
            onEdit(customer);
        } else {
            // add a new customer (no ID is passed for new customers)
            onAdd(customer);
        }

        // clear the form after submission
        setName('');
        setEmail('');
        setPhone('');
        setId(null); // reset the ID after submission
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>{customerToEdit ? 'Edit Your Account' : 'Create an Account'}</h2>
            <input
                type="text"
                placeholder="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="text"
                placeholder="Phone"
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
            />
            <button type="submit">{customerToEdit ? 'Update' : 'Add'}</button>
        </form>
    );
};

export default CustomerForm;