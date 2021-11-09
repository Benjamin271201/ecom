import React, {useState} from 'react';
import api from "../api/api";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";
import AuthService from "../services/auth.service";

const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
};

const vemail = value => {
    if (!isEmail(value)) {
      return (
        <div className="alert alert-danger" role="alert">
          This is not a valid email.
        </div>
      );
    }
  };
  
  const vusername = value => {
    if (value.length < 4 || value.length > 20) {
      return (
        <div className="alert alert-danger" role="alert">
          The username must be between 4 and 20 characters.
        </div>
      );
    }
  };
  
  const vpassword = value => {
    if (value.length < 6 || value.length > 40) {
      return (
        <div className="alert alert-danger" role="alert">
          The password must be between 6 and 40 characters.
        </div>
      );
    }
  };

const RegisterForm = props => {
    const [details, setDetails] = useState({
        username: "",
        password: "", 
        firstname:"", 
        lastname:"", 
        email:"", 
        phone:""
    });

    const handleChange = ({target}) => {
        const {name, value} = target;
        setDetails(prevDetails => ({
            ...prevDetails,
            [name]: value
        }));
    }

    const handleRegister = e => {
        e.preventDefault();

        setDetails({loading: true, message: ""})

        this.form.validateAll();

        if (this.checkBtn.context._errors.length === 0) {
            AuthService.register(
                details.username, 
                details.password,
                details.email)
                .then(response => {
                    setDetails({
                    message: response.data.message,
                    successful: true
                });
            },
            error => {
                const resMessage =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();

                setDetails({loading: false, message: resMessage})
            });
    }

    return (
            <Form
                onSubmit={handleRegister}
                ref={c => {
                this.form = c;
                }}
            >
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
                <label htmlFor="firstname">First name</label>
                <Input
                    type="text"
                    className="form-control"
                    name="firstname"
                    value={details.firstname}
                    onChange={handleChange}
                />
                <label htmlFor="lastname">Last Name</label>
                <Input
                    type="text"
                    className="form-control"
                    name="lastname"
                    value={details.lastname}
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
                />
                <button disabled={details.loading}>
                    {details.loading && (
                    <span>Login</span>)}
                </button>
    
                {details.message && (
                    <div className="alert alert-danger" role="alert">
                        {details.message}
                    </div>
                )}
                <CheckButton
                style={{ display: "none" }}
                ref={c => {
                    this.checkBtn = c;
                }}
                />
            </Form>
        );

        /* <form onSubmit={submitHandler}>
             <h2>Login</h2>
             <input type="text" name="username" id="username" 
            onChange={handleChange} value={details.username || ""}/><br />
            <input type="password" name="password" id="password" 
            onChange={handleChange} value={details.password || ""}/><br />
            <button type="submit">Submit</button>
        </form> */
    }
}

export default RegisterForm;


