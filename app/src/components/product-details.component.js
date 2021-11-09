import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { Grid, Image, Label } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api";
import Navbar from './navbar.component';

const ProductDetail = (props) => {
    const {id} = useParams();
    const demoImg = "https://semantic-ui.com/images/wireframe/image.png";
    const [product, setProduct] = useState({});

    const getProductInfo = () => {
        console.log(id)
        api.search("product", "products", `?id=${id}`)
        .then(response => {
            // const productDetail = response.map(product => product)
            // console.log(productDetail)
            setProduct(response);
        })
    }

    useEffect(() => {
        getProductInfo();
    }, [])

    return (
        <div>
            <Grid>
                <Grid.Column width={4}>
                    <Image src={demoImg} />
                </Grid.Column>
                <Grid.Column width={9}>
                    <strong>{product.name}</strong>
                </Grid.Column>
            </Grid>
        </div>
    )
}

export default ProductDetail;