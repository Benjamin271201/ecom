import React, {useState, useEffect} from 'react';
import { Grid, Image, Label } from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api";

const Product = (props) => {
    const demoImg = "https://semantic-ui.com/images/wireframe/image.png";
    const [productList, setProductList] = useState([]);
    const productFormat = (product) => {
        return (
            <Grid.Column >
                <Link to={`products/${product.id}`}>
                    <Image src={demoImg} />
                    <strong>{product.name}</strong><br />
                    <label style={{color: "red"}}>đ{product.price}</label>
                    <label style={{float: "right"}}>Sold: {product.sold}</label>
                </Link>
            </Grid.Column>
        )
    }


    const fetchAllProducts = async () => {
        api.list("product", "products")
        .then(response => {
            const products = response.map(product => {
                return (
                    productFormat(product)
                )
            })
            setProductList(products);
        })
    }

    const fetchProductsByKeyword = async (keyword) => {
        api.search("product", "products", `?keyword=${keyword}`)
        .then(response => {
            const products = response.map(product => {
                return (
                    productFormat(product)
                )
            })
            setProductList(products);
        })
    }

    const fetchProductsByCategory = async (category) => {
        api.search("product", "products", `categories/${category}`)
        .then(response => {
            const products = response.map(product => {
                return (
                    productFormat(product)
                )
            })
            setProductList(products);
        })
    }

    useEffect(() => { 
        if (props.search === "" && props.category === "") {
            fetchAllProducts();
        } else if (props.category !== "") {
            fetchProductsByCategory(props.category);
        }  else {
            fetchProductsByKeyword(props.search);
        }
    }, [props.search, props.category]);

    return (
        <Grid container columns={5}>
            {productList}
        </Grid>
    )
}

export default Product;