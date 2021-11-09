import React, {useState} from 'react';
import Product from './product.component';
import { Grid, Image } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import api from "../api/api"

function CategoryItemList() {
    const demoImg = "https://semantic-ui.com/images/wireframe/image.png";

    api.

    return (
        <Grid container columns={5}>
            <Grid.Column>
                <Image src={demoImg} />
            </Grid.Column>
            <Grid.Column>
                <Image src={demoImg} />
            </Grid.Column>
            <Grid.Column>
                <Image src={demoImg} />
            </Grid.Column>
            <Grid.Column>
                <Image src={demoImg}/>
            </Grid.Column>
            <Grid.Column>
                <Image src={demoImg} />
            </Grid.Column>
        </Grid>
    )
}

export default CategoryItemList;