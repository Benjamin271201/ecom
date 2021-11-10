import React, {useState, useEffect} from 'react';
import { useParams, Link } from 'react-router-dom';
import { Grid, Image, Label, Button, Input } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api";
import NavBar from "./navbar.component"
import { createBrowserHistory } from "history";

export const customHistory = createBrowserHistory();

const ProductDetail = () => {
    const {id} = useParams();
    //cartId === customerId === accountId
    const customerId = localStorage.getItem("user") !== null 
        ? JSON.parse(localStorage.getItem("user")).id
        : "";
    const demoImg = "https://semantic-ui.com/images/wireframe/image.png";
    const [product, setProduct] = useState({});
    const [cartInput, setCartInput] = useState({cartId:customerId, productId: id, quantity: 1});

    const getProductInfo = () => {
        console.log(id)
        api.search("product", "products", `?id=${id}`)
        .then(response => {
            setProduct(response);
        })
    }

    const increaseBuyNumber = () => {
        //check cap (50)
        const prevQuantity = cartInput.quantity;
        if (prevQuantity >= 50) {
            return;
        }
        setCartInput((prevCart) => {
            return {
                ...prevCart,
                quantity: prevQuantity + 1
            }
        });
    }

    const decreaseBuyNumber = () => {
        //check 0
        const prevQuantity = cartInput.quantity;
        if (prevQuantity <= 1) {
            return;
        }
        setCartInput((prevCart) => {
            return {
                ...prevCart,
                quantity: prevQuantity - 1
            }
        });
    }

    const handleAddToCart = () => {
        console.log(cartInput);
        try {
            api.create("cart-detail", cartInput)
            .then(result => {
                console.log(result);
            })
        } catch (error) {
            alert(error);
        }
        
    } 

    const handleBuyNow = () => {
        handleAddToCart();
        customHistory.push("/cart");
    } 

    useEffect(() => {
        getProductInfo();
    }, [])

    return (
        <div>
            <NavBar />
            <Grid centered >
                <Grid.Row >
                    <Grid.Column width={3}>
                    </Grid.Column>

                    <Grid.Column width={4}>
                        <Image src={demoImg} />
                    </Grid.Column>

                    <Grid.Column width={9}>
                        <strong style={{fontSize: 35, paddingBottom: 30}}>{product.name}</strong><br />
                        <label style={{fontSize: 18, paddingBottom: 15, paddingTop: 15}}>
                            Brand: {product.brandName}
                        </label><br/>
                        <Label>
                            Rating
                            <Label.Detail>{product.rating   }</Label.Detail>
                        </Label>
                        <Label>
                            Sold
                            <Label.Detail>{product.sold}</Label.Detail>
                        </Label>
                        <Label>
                            Instock
                            <Label.Detail>{product.stock}</Label.Detail>
                        </Label><br/>
                        <label style={{fontSize: 25, fontWeight: "bold", color: "red", padding: 35}}>
                            đ{product.price}
                        </label><br />

                        <Button.Group style={{paddingLeft: 40, paddingRight: 40}}>
                            <Button icon='minus' onClick={decreaseBuyNumber}/>
                            <Button basic id="quantity" >{cartInput.quantity}</Button>
                            <Button icon='plus' onClick={increaseBuyNumber}/>
                        </Button.Group>

                        {localStorage.getItem("user") ? (
                            <Button.Group >
                                <Button onClick={handleAddToCart} positive icon="cart" style={{minWidth: 100}}/>
                                <Button.Or />
                                <Button onClick={handleBuyNow} primary style={{minWidth: 100}}>Buy now</Button>
                            </Button.Group>
                        ) : (
                            <Link to="/login">
                                <Button.Group >
                                    <Button positive icon="cart" style={{minWidth: 100}}/>
                                    <Button.Or />
                                    <Button primary style={{minWidth: 100}}>Buy now</Button>
                                </Button.Group>
                            </Link>
                        )}
                        <br />
                        <label style={{paddingTop: 10}}><strong>Description:</strong> {product.description}</label>
                </Grid.Column>
                </Grid.Row>
                <Grid.Row>
                    <strong style={{fontSize: 35, paddingBottom: 30}}>Review</strong><br />
                    <Grid.Column width={9}> 
                        {product.review}
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </div>
    )
}

export default ProductDetail;