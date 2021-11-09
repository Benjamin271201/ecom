import React, {useState, useEffect} from "react";
import Navbar from "./navbar.component";
import Banner from "./banner.component";
import Product from "./product.component";

function Home() {
  const [keyword, setKeyword] = useState("");

  const searchHandler = (keyword) => {
    setKeyword(keyword);
  }

  useEffect(() => {
    console.log(keyword)
  }, [keyword])

  return (
    <div>
      <Navbar submit={searchHandler} />
      <Product search={keyword}/>
    </div>
  )
}

export default Home;