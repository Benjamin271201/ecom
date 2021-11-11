import React, {useState} from 'react';
import Product from './product.component';
import { Form } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api"

function ChangePassword() {
    const demoImg = "https://semantic-ui.com/images/wireframe/image.png";
    return (
        <Form>
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
        </Form>
    )
}

export default ChangePassword;