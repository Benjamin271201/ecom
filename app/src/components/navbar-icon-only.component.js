import React from 'react';
import { Menu } from 'semantic-ui-react';
import { Link } from 'react-router-dom'

function NavbarIconOnly() {
    const logo = "https://www.seekpng.com/png/detail/364-3648727_lacoste-men-clothing-polos-1-vector-shopping-bag.png";
    return (
        <Menu size="small" className="ui grid" fluid widths={3}>
            <Link to="/" style={{paddingTop: 10, paddingBottom: 10}}>
                <Menu.Item compact>
                    <img auto src={logo} alt="logo" />
                </Menu.Item>
            </Link>
        </Menu>
    )
}

export default NavbarIconOnly;