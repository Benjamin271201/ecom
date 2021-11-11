import React, {useState, useEffect} from 'react';
import { Table, Dropdown, Button, Divider, Header, Icon } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api";
import NavBar from "./navbar.component"
import CustomerInfo from './customer-info.component';
import { createBrowserHistory } from "history";

export const customHistory = createBrowserHistory();

const Cart = () => {
    const customerId = localStorage.getItem("user") !== null 
    ? JSON.parse(localStorage.getItem("user")).id
    : "";
    // const [product, setProduct] = useState({});
    const [cart, setCart] = useState([]);
    const [total, setTotal] = useState(0);
    const [addressList, setAddressList] = useState([]);
    const [addressId, setAddressId] = useState(0);

    const handleCheckout = () => {
        const transactionDetail = {
            customerId,
            addressId
        }
        api.create("transaction", transactionDetail)
        .then(
        () => {
            customHistory.push("/");
            window.location.reload();
        })
    }

    const handleAddressSelect = (e, data) => {
        //?????????????????
        setAddressId(data.value);
    }

    const getAddressList = () => {
        api.search("address", "addresses/users", customerId)
        .then(response => {
            const addresses = response.map(address => {
                return {
                    key: address.id,
                    value: address.id,
                    text: address.addressLine 
                    + " " + address.district 
                    + " " + address.city 
                    + " " + address.province
                }
            })
            console.log(addresses)
            setAddressList(addresses)
        })
    }

    const deleteCartDetailFromCart = (id) => {
        let item = cart.find(cart => cart.id === id);
        const newTotal = total - (item.productPrice * item.quantity);
        api.delete("cart-detail", "details", id);
        setCart (
            cart.filter(cart => cart.id !== id),
        );
        setTotal(newTotal);
    }

    const getCart = () => {
        let total = 0;
        api.search("cart", "carts", customerId)
        .then(response => {
            response.details.forEach(detail => {
                total += detail.productPrice * detail.quantity;
            });
            setTotal(total);
            setCart(response.details);
        })
    }

    useEffect(() => {
        getCart()
        getAddressList()
    }, [])

    return (
        <div>
            <NavBar />
            <Divider horizontal>
                <Header as='h4'>
                    <Icon name='user' />
                    Customer Info
                </Header>
            </Divider>

            <CustomerInfo />

            <Dropdown
                onChange = {handleAddressSelect}
                placeholder='Select Address:'
                fluid
                selection
                options = {addressList}
            />

            <Divider horizontal>
                <Header as='h4'>
                    <Icon name='cart' />
                    Cart
                </Header>
            </Divider>
            {cart.length === 0 
                ? <h1 style={{textAlign: "center"}}>Your cart is empty :(</h1> 
                : (
                <div>
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
                        {cart.map(cartDetail => {
                            return (
                                <Table.Row textAlign='center' key={cartDetail.id}>
                                    <Table.Cell>{cartDetail.productName}</Table.Cell>
                                    <Table.Cell>đ{cartDetail.productPrice}</Table.Cell>
                                    <Table.Cell>{cartDetail.quantity}</Table.Cell>
                                    <Table.Cell>{cartDetail.quantity * cartDetail.productPrice}</Table.Cell>
                                    <Table.Cell>
                                        <Button size="mini" onClick={() => deleteCartDetailFromCart(cartDetail.id)} icon="delete" />
                                    </Table.Cell>
                                </Table.Row>
                            )
                        })}
                    </Table.Body>
                </Table>
                <Button style={{float: "right", marginRight: 290}} size="big" positive onClick={handleCheckout}>Checkout</Button>
                <label 
                    style={{fontSize: 30,
                            fontWeight: "bold",
                            color: "red",
                            float: "right",
                            padding: 20,
                            marginRight: 100}}
                >
                    Total: đ{total}
                </label>
                </div>
            )}
        </div>
    )
}

export default Cart;