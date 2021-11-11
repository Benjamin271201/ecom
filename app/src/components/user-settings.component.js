import React, {useState, useEffect} from 'react';
import { Grid, GridColumn, Image, Label, Menu} from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api";
import NavbarIconOnly from './navbar-icon-only.component';
import CustomerInfo from './customer-info.component';
import AddressBook from './address-book.component';
import ChangePassword from './change-password.component';
import OrderHistory from './order-history.component';

const UserSettings = () => {
    const customerId =
    localStorage.getItem("user") !== null
      ? JSON.parse(localStorage.getItem("user")).id
      : "";

    const [itemDisplay, setItemDisplay] = useState("");
    const [activeItem, setActiveItem] = useState("profile");
    
    const handleItemClick = (e, {name}) => {
        setActiveItem(name);
        //display corresponding item saved in items
        switch(name) {
            case "Customer profile":
                setItemDisplay(<CustomerInfo />);
                break;
            case "Change password":
                setItemDisplay(<ChangePassword />)
                break;
            case "Address book":
                setItemDisplay(<AddressBook />)
                break;
            case "Order History":
                setItemDisplay(<OrderHistory />)
                break;
            default:
                setItemDisplay(<CustomerInfo />);
                break;
        }
        console.log(itemDisplay)
    }

    return (
        <div>
            <NavbarIconOnly />
            <Grid>
                <Grid.Row>
                    <Grid.Column width={3}>
                        <Menu pointing vertical style={{paddingLeft: 20, marginLeft: 40}}>
                            <Menu.Item
                                name='Customer profile'
                                active={activeItem === 'Customer profile'}
                                onClick={handleItemClick}
                            />
                            <Menu.Item
                                name='Change password'
                                active={activeItem === 'Change password'}
                                onClick={handleItemClick}
                            />
                            <Menu.Item
                                name='Address book'
                                active={activeItem === 'Address book'}
                                onClick={handleItemClick}
                            />
                            <Menu.Item
                                name='Order History'
                                active={activeItem === 'Order History'}
                                onClick={handleItemClick}
                            />
                        </Menu>
                    </Grid.Column>
                    <GridColumn width={8}>
                        {itemDisplay}
                    </GridColumn>
                </Grid.Row>
            </Grid>
      </div>
    )
}

export default UserSettings;