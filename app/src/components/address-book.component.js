import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { Button, Table, Grid, Input, Form} from "semantic-ui-react";
import "semantic-ui-css/semantic.min.css";
import api from "../api/api";

const AddressBook = () => {
  const customerId =
    localStorage.getItem("user") !== null
      ? JSON.parse(localStorage.getItem("user")).id
      : "";
  const [addresses, setAddresses] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [addressInput, setAddressInput] = 
    useState({customerId: customerId, addressLine:"", district:"", city:"", province:""});
  
  const updateAddressInput = ({target}) => {
    setAddressInput(prevInputs => {
      return {
        ...prevInputs,
        [target.name]: target.value
      }
    })
  };
  
  const handleAddressChange = (id) => {

  }

  const handleAddressFormSubmit = () => {
    api.create("address", addressInput);
    getAddresses();
  };

  const newAddressForm = (
    <Form onSubmit={handleAddressFormSubmit}>
        <Form.Field> 
            <label>Address Line</label>
            <input onChange={updateAddressInput} name='addressLine'/>
        </Form.Field>
        <Form.Field> 
            <label>District</label>
            <input onChange={updateAddressInput} name='district' />
        </Form.Field>
        <Form.Field> 
            <label>City</label>
            <input onChange={updateAddressInput} name='city' />
        </Form.Field>
        <Form.Field> 
            <label>Province</label>
            <input onChange={updateAddressInput} name='province' />
        </Form.Field>
        <Button positive type="submit"> Submit </Button>
    </Form>
  )

  const toggleShowForm = () => {
    setShowForm(!showForm);
  }

  const getAddresses = () => {
      api.search("address", "addresses/users", customerId).then((response) => {
        setAddresses(response);
    });
  };

  const deleteAddress = (id) => {
    api.delete("address", "addresses", id);
    setAddresses(
      addresses.filter(address => address.id !== id),
    );
  };


  useEffect(() => {
    getAddresses();
  }, []);

  return (
    <div>
      <Button primary onClick={toggleShowForm}>Add new address</Button>
      {showForm && newAddressForm}
      <Table celled>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>Address Line</Table.HeaderCell>
            <Table.HeaderCell>District</Table.HeaderCell>
            <Table.HeaderCell>City</Table.HeaderCell>
            <Table.HeaderCell>Province</Table.HeaderCell>
            <Table.HeaderCell>Action</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {addresses.map(address => {
            return (
              <Table.Row textAlign="center" key={address.id}>
                <Table.Cell>{address.addressLine}</Table.Cell>
                <Table.Cell>{address.district}</Table.Cell>
                <Table.Cell>{address.city}</Table.Cell>
                <Table.Cell>{address.province}</Table.Cell>
                <Table.Cell>
                <Button
                    size="mini"
                    onClick={() => handleAddressChange(address.id)}
                    icon="edit"
                  />
                  <Button
                    size="mini"
                    onClick={() => deleteAddress(address.id)}
                    icon="delete"
                  />
                </Table.Cell>
              </Table.Row>
            )
          })}
        </Table.Body>
      </Table>
    </div>
  );
};

export default AddressBook;
