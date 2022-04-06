import React, { useState, useRef } from "react";
import {Form, Header, Button} from 'semantic-ui-react'
import { useForm } from "react-hook-form";
import AuthService from "../services/auth.service";
import { createBrowserHistory } from "history";
import NavbarIconOnly from "./navbar-icon-only.component";
import ErrorHandler from "../util/error.util";
import * as Yup from 'yup';

export const customHistory = createBrowserHistory();

// const vusername = (value) => {
//   if (value.length < 4 || value.length > 20) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         The username must be between 4 and 20 characters.
//       </div>
//     );
//   }
// };

// const vpassword = (value) => {
//   const passwordRegex = "^(?=.*\\d)(?=.*[a-zA-Z0-9]).{8,}$";
//   if (value.length < 8 && !value.match(passwordRegex)) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         The password length must be at least 8 and contains only alphabetic characters.
//       </div>
//     );
//   }
// };

// const vconfirmPassword = (value, password) => {
//   if (value !== password) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         Password mismatch!
//       </div>
//     );
//   }
// };

const renderError = (message: string) => <p>{message}</p>

type registerInfo = {
  username: string,
  password: string,
  firstname: string,
  lastname: string,
  email: string,
  phone: string,
  message: string,
};

const RegisterForm = () => {
  const {
    register,
    handleSubmit,
    resetField,
    setError,
    formState: { errors, isSubmitting }
  } = useForm<registerInfo>({
    defaultValues: {
      username: "",
      password: "",
      firstname: "",
      lastname: "",
      email: "",
      phone: "",
      message: "",
    }
  });

  const handleRegister = async (input: any) => {
    await new Promise(f => setTimeout(f, 3000))
    AuthService.register(
      input.username,
      input.password,
      input.firstName,
      input.lastName,
      input.email,
      input.phone
    ).then(
      () => {
        customHistory.push("/login");
        window.location.reload();
      },
      (error) => {
        resetField('password')
        const resMessage = ErrorHandler.getErrorMessage(error.response.data)
      }
    );
  }

  return (
    <div>
      <NavbarIconOnly />
      <Form
        onSubmit={handleSubmit(handleRegister)}
        style={{
          width: "40%",
          height: "40%",
          marginLeft: "auto",
          marginRight: "auto",
        }}
      >
        <Header as="h1">REGISTER</Header>
        <Form.Field>
          <label htmlFor="username">Username</label>
          <input 
            {...register("username", {required: "Username is required!"})} 
            type = "text"
            name = "username"
          />
        </Form.Field>
        
        <Form.Field>
          <label htmlFor="password">Password</label>
          <input 
            {...register("password", {required: "Password is required!"})} 
            type = "text"
            name = "password"
          />
        </Form.Field>
        
        <Form.Field>
          <label htmlFor="firstname">First name</label>
          <input 
            {...register("firstname", {required: "Firstname is required!"})} 
            type = "text"
            name = "username"
          />
        </Form.Field>
        
        <Form.Field>
          <label htmlFor="lastname">Last Name</label>
          <input 
            {...register("lastname", {required: "Lastname is required!"})} 
            type = "text"
            name = "lastname"
          />
        </Form.Field>
        
        <Form.Field>
          <label htmlFor="email">Email</label>
          <input 
            {...register("email", {required: "Email is required!"})} 
            type = "email"
            name = "email"
          />
        </Form.Field>
        
        <Form.Field>
          <label htmlFor="phone">Phone</label>
          <input 
            {...register("phone", {required: "Phone is required!"})} 
            type = "text"
            name = "phone"
          />
        </Form.Field>
        <Button type="submit" primary button disabled={isSubmitting}>
          Register
        </Button>
      </Form>
    </div>
  );
};

export default RegisterForm;
