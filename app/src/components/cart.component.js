import React, {useState, useEffect} from 'react';
import { useParams, Link } from 'react-router-dom';
import { Table, Label, Button } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api";
import NavBar from "./navbar.component"

const Cart = () => {
    const customerId = localStorage.getItem("user") !== null 
    ? JSON.parse(localStorage.getItem("user")).id
    : "";
    const demoImg = "https://semantic-ui.com/images/wireframe/image.png";
    // const [product, setProduct] = useState({});
    const [cart, setCart] = useState([]);
    const [total, setTotal] = useState(0);

    const deleteProductFromCart = ({target}) => {
        
    }

    const getCart = () => {
        api.search("cart", "carts", customerId)
        .then(response => {
            let cartDetails = response.details.map(product => {
                const subtotal = product.quantity * product.productPrice;
                // console.log(subtotal)
                //fix subtotal
                setTotal(subtotal)  
                return (
                    <Table.Row textAlign='center'>
                        <Table.Cell>{product.productName}</Table.Cell>
                        <Table.Cell>đ{product.productPrice}</Table.Cell>
                        <Table.Cell>{product.quantity}</Table.Cell>
                        <Table.Cell>{subtotal}</Table.Cell>
                        <Table.Cell>
                            <Button size="mini" onClick={deleteProductFromCart} icon="delete" />
                        </Table.Cell>
                    </Table.Row>
                )
            })   
            setCart(cartDetails);
        })
        
    }

    useEffect(() => {
        getCart()
    }, [])

    return (
        <div>
            <NavBar />
            <Table celled>
                <Table.Header>
                    <Table.Row textAlign='center'>
                        <Table.HeaderCell>Product</Table.HeaderCell>
                        <Table.HeaderCell>Unit Price</Table.HeaderCell>
                        <Table.HeaderCell>Quantity</Table.HeaderCell>
                        <Table.HeaderCell>Subtotal</Table.HeaderCell>
                        <Table.HeaderCell>Action</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>  
                    {cart.map(cartDetail => cartDetail)}
                </Table.Body>
            </Table>
            <strong>Total: {total}</strong>
        </div>
    )
}

export default Cart;