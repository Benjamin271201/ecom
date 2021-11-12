import React, {useState} from 'react';
import { Grid, GridColumn, Menu} from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import NavbarIconOnly from './navbar-icon-only.component';
import CustomerInfo from './customer-info.component';
import AddressBook from './address-book.component';
import ChangePassword from './change-password.component';
import OrderHistory from './order-history.component';
import authService from '../services/auth.service';

const UserSettings = () => {
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
    }

    return (
        <div>
            {handleItemClick}
            <NavbarIconOnly />
            <Grid>
                <Grid.Row>
                    <Grid.Column width={3}>
                        <Menu pointing vertical style={{marginLeft: 40}}>
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
                            <Menu.Item
                                name='Logout'
                                onClick={authService.logout}
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