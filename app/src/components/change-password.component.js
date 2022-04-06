import React, {useState} from 'react';
import Product from './product.component';
import { Form, Button } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api"

function ChangePassword() {
    const passwordRegex = "";

    const handleChangePassword = () => {
        //check if current password is true
        //validate new pass
    }
    
    return (
        <Form onSubmit={handleChangePassword}>
            <Form.Field>
                <label>Current Password</label>
                <input />
            </Form.Field>
            <Form.Field>
                <label>New Password</label>
                <input />
            </Form.Field>
            <Form.Field>
                <label>Confirm New Password</label>
                <input />
            </Form.Field>
            <Button type='submit'>Submit</Button>
        </Form>
    )
}

export default ChangePassword;