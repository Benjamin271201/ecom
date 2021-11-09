import axios from 'axios';
import React, { useState, useEffect} from 'react';
import { Dropdown, Menu, Input, Icon, Button, Search } from 'semantic-ui-react';
import AuthService from "../services/auth.service";
import api from '../api/api';
import { Link } from 'react-router-dom'

function Navbar(props) {
    // const categoryList = api.list("category", "categories");
    const [categoryList, setCategoryList] = useState([]);
    const [user, setUser] = useState();
    const logo ="https://www.seekpng.com/png/detail/364-3648727_lacoste-men-clothing-polos-1-vector-shopping-bag.png";

    const fetchCategories = () => {
        api.list("category", "categories")
        .then(response => {
            let records = response.map((record) => {    
                return (
                {   key: record.id,
                    text: record.categoryName,
                    active: record.active
                })
            }) 
            setCategoryList(records)
        })
    }

    const fetchUser = () => {
        const currentUser = localStorage.getItem('user');
        if (currentUser === null) {
            setUser (
                <Menu.Menu className="three wide column " position="right">
                    <Menu.Item >
                        <Link to="/login" className="ui primary button" id="login-btn">Login</Link>
                    </Menu.Item>
                    <Menu.Item >
                        <Link to="/register" className="ui button" id="register-btn">Register</Link>
                    </Menu.Item>
                </Menu.Menu>
            )
        } else {
            setUser (
                <Menu.Menu className="three wide column " position="right">
                    <Menu.Item >
                        <Link to="/profile">{JSON.parse(currentUser).username}</Link>
                    </Menu.Item>
                    <Menu.Item >
                        <Button onClick={AuthService.logout}>Logout</Button>
                    </Menu.Item>
                </Menu.Menu>
            )
        }
    }

    const handleChange = (e) => {
        e.persist();
        props.submit(e.target.value);
    }

    useEffect(() => { 
        fetchUser();
    }, []);

    return (
        <Menu size="small" className="ui grid">
            {fetchCategories}
            <Menu.Item compact className="one wide column centered">
                <img auto src={logo} alt="logo" />
            </Menu.Item>
            <Menu.Item compact className="two wide column" >
                <Dropdown className="centered" text='Departments' options={categoryList} simple item />
            </Menu.Item>
            <Menu.Item className="nine wide column">
                <Input name="search" onChange={handleChange} placeholder='Search...' />
            </Menu.Item>
            <Menu.Item className="one wide column">
                <Icon name='shopping cart' size="large" />Cart
            </Menu.Item>
            {user}
        </Menu>
    )
}

export default Navbar;