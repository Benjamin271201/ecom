import React, {useState, useEffect} from 'react';
import { useParams, Link } from 'react-router-dom';
import { Grid, Input } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api";

const CustomerInfo = () => {
    const customerId = localStorage.getItem("user") !== null 
    ? JSON.parse(localStorage.getItem("user")).id
    : "";
    const [customer, setCustomer] = useState({});

    const getCustomerInfo = () => {
        api.search("customer", "customers", customerId)
        .then(response => {
            console.log(response)
            setCustomer(response)
        })
    }

    useEffect(() => {
        getCustomerInfo();
    }, [])
    
    return (
        <div>
            <Grid centered >
                <Grid.Row >
                    <Grid.Column width={4}>
                        <label style={{fontWeight: "bold", fontSize: 15, padding: 9}}>Username: {customer.username}</label><br />
                        <label style={{fontWeight: "bold", fontSize: 15, padding: 9}}>Firstname: {customer.firstname}</label><br />
                        <label style={{fontWeight: "bold", fontSize: 15, padding: 9}}>Lastname: {customer.lastname}</label><br />
                    </Grid.Column>
                    <Grid.Column width={4}>
                        <label style={{fontWeight: "bold", fontSize: 15, padding: 9}}>Joindate: {customer.joinDate}</label><br />
                        <label style={{fontWeight: "bold", fontSize: 15, padding: 9}}>Email: {customer.email}</label><br />
                        <label style={{fontWeight: "bold", fontSize: 15, padding: 9}}>Phone: {customer.phone}</label><br />
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </div>
    )
}

export default CustomerInfo;