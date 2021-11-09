import React, {useState, useEffect} from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import Navbar from './components/navbar.component';
import Banner from './components/banner.component';
import CategoryProductList from './components/category-product-list';
import LoginForm from './components/login.component';
import Product from './components/product.component';
import {Routes, Route, BrowserRouter} from 'react-router-dom';
import ProductDetail from './components/product-details.component';

import AuthService from "./services/auth.service";
import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/board-user.component";

function App() {
  // const [currentUser, setCurrentUser] = useState();
  // const [showAdminBoard, setShowAdminBoard] = useState();

  // useEffect(() => {
  //   const user = AuthService.getCurrentUser();

  //   if (user) {
  //     setCurrentUser(user);
  //     setShowAdminBoard(user.isAdmin);
  //   }
  // })

  // const logOut = () => {
  //   AuthService.logout();
  // }



  return (
    <Routes> 
      <Route exact path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/user" element={<BoardUser />} />
      <Route path="/products/:id" element={<ProductDetail />} />
    </Routes>
  );
}

export default App;
