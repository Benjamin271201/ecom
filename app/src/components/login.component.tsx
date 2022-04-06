import React, { useState } from "react";
import { createBrowserHistory } from "history";
import AuthService from "../services/auth.service";
import { Button, Form, Header } from "semantic-ui-react";
import NavbarIconOnly from "./navbar-icon-only.component";
import { useForm } from "react-hook-form";
import ErrorHandler from "../util/error.util";

export const customHistory = createBrowserHistory();

type loginValues = {
  username: string;
  password: string;
};

const LoginForm = () => {
  const [message, setMessage] = useState("");

  //react hook form default methods
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<loginValues>();

  const handleLogin =  (input:any) => {
    AuthService.login(input.username, input.password).then(
      () => {
        customHistory.push("/");
        window.location.reload();
      },
      (error) => {
        setMessage(ErrorHandler.getErrorMessage(error.response.data));
      }
    );
  };

  return (
    <div>
      <NavbarIconOnly />
      <Form
        onSubmit={handleSubmit(handleLogin)}
        style={{
          width: "40%",
          height: "40%",
          marginLeft: "auto",
          marginRight: "auto",
        }}
      >
        <Header as="h1">LOGIN</Header>
        <Form.Field>
          <label htmlFor="username">Username</label>
          <input
            {...register("username", { required: "Username is required!" })}
            type="text"
            id="username"
          />
          {errors.username && <p style={{color: "red"}}>{errors.username.message}</p>}
        </Form.Field>
        <Form.Field>
          <label htmlFor="password">Password</label>
          <input
            {...register("password", { required: "Password is required!"})}
            type="password"
            id="password"
          />
          {errors.password && <p style={{color: "red"}}>{errors.password.message}</p>}
          {message && <p style={{color: "red"}}>{message}</p>}
        </Form.Field>
        <br />
        <Button type="submit" primary button>
          Login
        </Button>
      </Form>
    </div>
  );
};

export default LoginForm;
