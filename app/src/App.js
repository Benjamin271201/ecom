import React from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import {Routes, Route} from 'react-router-dom';
import ProductDetail from './components/product-details.component';

import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Cart from './components/cart.component';
import UserSettings from './components/user-settings.component';

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
      <Route path="/products/:id" element={<ProductDetail />} />
      <Route path="/cart" element={<Cart />} />
      <Route path="/settings" element={<UserSettings />} />
    </Routes>
  );
}

export default App;
