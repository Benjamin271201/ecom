import React, { useState, useEffect } from "react";
import { Dropdown, Menu, Input, Icon, Button, Search } from "semantic-ui-react";
import AuthService from "../services/auth.service";
import api from "../api/api";
import { Link } from "react-router-dom";

function Navbar(props) {
  // const categoryList = api.list("category", "categories");
  const [categoryList, setCategoryList] = useState([]);
  const [user, setUser] = useState();
  const logo =
    "https://www.seekpng.com/png/detail/364-3648727_lacoste-men-clothing-polos-1-vector-shopping-bag.png";

  const handleSearchChange = (e) => {
    e.persist();
    props.search(e.target.value);
  };

  const handleCategorySearch = (categoryId) => {
    console.log(categoryId)
    props.category(categoryId);
  };
  
  const fetchCategories = () => {
    api.list("category", "categories").then((response) => {
      let records = response.map((record) => {
        return (
          <Dropdown.Item
            onClick={() => handleCategorySearch(record.id)}
            key={record.id}
            text={record.categoryName}
            style={{ paddingTop: 5 }}
          ></Dropdown.Item>
        );
      });
      //get all departments
      const getAllDeparments = (
        <Dropdown.Item
            onClick={() => handleCategorySearch("")}
            key={0}
            text="All"
            style={{ paddingTop: 5 }}
        ></Dropdown.Item>
      )
      records.unshift(getAllDeparments);
      setCategoryList(records);
    });
  };

  const fetchUser = () => {
    const currentUser = localStorage.getItem("user");
    if (currentUser === null) {
      setUser(
        <Menu.Menu className="four wide column " position="right">
          <Menu.Item className="one wide column">
            <Link to="/login">
            <Icon name="shopping cart" size="large" />
              Cart
            </Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/login" className="ui primary button" id="login-btn">
              Login
            </Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/register" className="ui button" id="register-btn">
              Register
            </Link>
          </Menu.Item>
        </Menu.Menu>
      );
    } else {
      setUser(
        <Menu.Menu className="four wide column " position="right">
          <Menu.Item className="one wide column">
            <Link to="/cart">
            <Icon name="shopping cart" size="large" />
              Cart
            </Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/settings">
              <Icon name="user" size="large" />
              {JSON.parse(currentUser).username}
            </Link>
          </Menu.Item>
          <Menu.Item>
            <Button onClick={AuthService.logout}>Logout</Button>
          </Menu.Item>
        </Menu.Menu>
      );
    }
  };

  useEffect(() => {
    fetchUser();
    fetchCategories();
  }, []);

  return (
    <Menu size="small" className="ui grid" borderless>
      <Link to="/" style={{ paddingTop: 10 }}>
        <Menu.Item compact className="one wide column centered">
          <img auto src={logo} alt="logo" />
        </Menu.Item>
      </Link>
      <Menu.Item compact className="two wide column">
        <Dropdown text="Departments" simple item>
          <Dropdown.Menu>{categoryList.map((cat) => cat)}</Dropdown.Menu>
        </Dropdown>
      </Menu.Item>
      <Menu.Item className="nine wide column">
        <Input
          name="search"
          onChange={handleSearchChange}
          placeholder="Search..."
        />
      </Menu.Item>
      {user}
    </Menu>
  );
}

export default Navbar;
