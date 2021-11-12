import React, { useState, useRef } from "react";
import { createBrowserHistory } from "history";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "../services/auth.service";
import { Button } from "semantic-ui-react";
import NavbarIconOnly from "./navbar-icon-only.component";

export const customHistory = createBrowserHistory();

const required = (value) => {
  if (value === "") {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

function LoginForm() {
  const [details, setDetails] = useState({
    username: "",
    password: "",
    loading: false,
    message: "",
  });
  let formInput = useRef(null);
  let checkBtn = useRef(null);
  let inputDetails = {
    username: "",
    password: "",
    loading: false,
    message: "",
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    inputDetails[name] = value;
    setDetails((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = () => {
    setDetails(inputDetails);
  };

  const handleLogin = (e) => {
    handleSubmit();
    e.preventDefault();

    formInput.validateAll();

    if (checkBtn.context._errors.length === 0) {
      AuthService.login(details.username, details.password).then(
        () => {
          customHistory.push("/");
          window.location.reload();
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setDetails(prevDetails => {
              return { 
                ...prevDetails,
                loading: false,
                message: resMessage }
          });
        }
      );
    } else {
      setDetails(prevDetails => {
        return { 
          ...prevDetails,
          loading: false,
        }
      });
    }
  };

  return (
    <div>
      <NavbarIconOnly />
      <Form
        onSubmit={handleLogin}
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
        <h1>LOGIN</h1>
        <label htmlFor="username">Username</label>
        <Input
          type="text"
          className="form-control"
          name="username"
          value={details.username}
          onChange={handleChange}
          validations={[required]}
        />
        <label htmlFor="password">Password</label>
        <Input
          type="password"
          className="form-control"
          name="password"
          value={details.password}
          onChange={handleChange}
          validations={[required]}
        />
        <br />
        <Button type="submit" primary button disabled={details.loading}>
          Login
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
}

export default LoginForm;
