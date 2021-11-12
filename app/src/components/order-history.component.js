import React, {useState, useEffect} from 'react';
import Product from './product.component';
import { Accordion, Icon, Table } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api"

function OrderHistory() {
    const customerId = localStorage.getItem("user") !== null 
    ? JSON.parse(localStorage.getItem("user")).id
    : "";
    const [activeIndex, setActiveIndex] = useState(-1);
    const [orders, setOrders] = useState([]);

    const handleClick = (e, titleProps) => {
        const { index } = titleProps
        const newIndex = activeIndex === index ? -1 : index

        setActiveIndex(newIndex)
    }

    const getOrderHistory = () => {
        api.search("transaction", "transactions/customers", customerId)
        .then(response => {
            setOrders(response)
        })
    }

    useEffect(() => {
        getOrderHistory()
    }, [])

    return (
        <Accordion styled>
            {orders.map(order => {
                return (
                    <div>
                        <Accordion.Title
                            active={activeIndex === order.id}
                            index={order.id}
                            onClick={handleClick}
                        >
                        <Icon name='dropdown' />
                            Transaction #{order.id}
                        </Accordion.Title>
                        <Accordion.Content active={activeIndex === order.id}>
                            <Table>
                                <Table.Row >
                                    <Table.HeaderCell style={{padding:10}}>Product</Table.HeaderCell>
                                    <Table.HeaderCell>Quantity</Table.HeaderCell>
                                    <Table.HeaderCell>Subtotal</Table.HeaderCell>
                                </Table.Row>
                                {order.details.map(detail => {
                                    console.log(detail)
                                    return (
                                        <Table.Row>
                                            <Table.Cell>{detail.productName}</Table.Cell>
                                            <Table.Cell>{detail.quantity}</Table.Cell>
                                            <Table.Cell>đ{detail.subTotal}</Table.Cell>
                                        </Table.Row>
                                    )
                                })}
                            </Table>
                        </Accordion.Content>
                    </div>
                )
            })}
      </Accordion>
    )
}

export default OrderHistory;