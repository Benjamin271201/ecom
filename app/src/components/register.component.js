import React, { useState, useRef } from "react";
import {Button} from 'semantic-ui-react'
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";
import AuthService from "../services/auth.service";
import { createBrowserHistory } from "history";
import NavbarIconOnly from "./navbar-icon-only.component";

export const customHistory = createBrowserHistory();

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const vemail = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vusername = (value) => {
  if (value.length < 4 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 4 and 20 characters.
      </div>
    );
  }
};

const vpassword = (value) => {
  const passwordRegex = "^(?=.*\\d)(?=.*[a-zA-Z0-9]).{8,}$";
  if (value.length < 8 && !value.match(passwordRegex)) {
    return (
      <div className="alert alert-danger" role="alert">
        The password length must be at least 8 and contains only alphabetic characters.
      </div>
    );
  }
};

// const vconfirmPassword = (value, password) => {
//   if (value !== password) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         Password mismatch!
//       </div>
//     );
//   }
// };

const RegisterForm = (props) => {
  let formInput = useRef(null);
  let checkBtn = useRef(null);
  const [details, setDetails] = useState({
    username: "",
    password: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    loading: false,
    message: "",
  });
  let inputDetails = {
    username: "",
    password: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    loading: false,
    message: "",
  };

  const handleChange = ({ target }) => {
    const { name, value } = target;
    inputDetails[name] = value;
    setDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value,
    }));
  };

  const handleSubmit = () => {
    setDetails(inputDetails);
  };

  const handleRegister = (e) => {
    e.preventDefault();
    handleSubmit();
    console.log(details);

    formInput.validateAll();

    if (checkBtn.context._errors.length === 0) {
      AuthService.register(
        details.username,
        details.password,
        details.firstName,
        details.lastName,
        details.email,
        details.phone
      ).then(
        (response) => {
          setDetails({
            message: response.message,
            successful: true,
          });
          customHistory.push("/login");
          window.location.reload();
        },
        (error) => {
          const resMessage =
            ( error.response &&
              error.response.message) ||
            error.message ||
            error.toString();

          setDetails({ loading: false, message: resMessage });
        }
      );
    }
  };

  return (
    <div>
      <NavbarIconOnly />
      <Form
        onSubmit={handleRegister}
        ref={(c) => {
          formInput = c;
        }}
        style={{
          width: "40%",
          height: "40%",
          marginLeft: "auto",
          marginRight: "auto",
        }}
      >
        <h1>REGISTER</h1>
        <label htmlFor="username">Username</label>
        <Input
          type="text"
          className="form-control"
          name="username"
          value={details.username}
          onChange={handleChange}
          validations={[required, vusername]}
        />
        <label htmlFor="password">Password</label>
        <Input
          type="password"
          className="form-control"
          name="password"
          value={details.password}
          onChange={handleChange}
          validations={[required, vpassword]}
        />
        {/* <label htmlFor="Confirm password">Confirm Password</label>
        <Input
          type="password"
          className="form-control"
          name="confirmPassword"
          onChange={handleChange}
          validations={[required, vconfirmPassword(details.password)]}
        /> */}
        <label htmlFor="firstname">First name</label>
        <Input
          type="text"
          className="form-control"
          name="firstName"
          value={details.firstName}
          onChange={handleChange}
        />
        <label htmlFor="lastname">Last Name</label>
        <Input
          type="text"
          className="form-control"
          name="lastName"
          value={details.lastName}
          onChange={handleChange}
        />
        <label htmlFor="email">Email</label>
        <Input
          type="email"
          className="form-control"
          name="email"
          value={details.email}
          onChange={handleChange}
          validations={[required, vemail]}
        />
        <label htmlFor="phone">Phone</label>
        <Input
          type="text"
          className="form-control"
          name="phone"
          value={details.phone}
          onChange={handleChange}
        /><br/>
        <Button type="submit" primary button disabled={details.loading}>
          Register
        </Button>

        {details.message && (
          <div className="alert alert-danger" role="alert">
            {details.message}
          </div>
        )}
        <CheckButton
          style={{ display: "none" }}
          ref={(c) => {
            checkBtn = c;
          }}
        />
      </Form>
    </div>
  );
};

export default RegisterForm;
