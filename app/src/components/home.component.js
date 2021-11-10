import React, {useState, useEffect} from "react";
import Navbar from "./navbar.component";
import Banner from "./banner.component";
import Product from "./product.component";

function Home() {
  const [keyword, setKeyword] = useState("");
  const [category, setCategory] = useState("");
  const searchHandler = (keyword) => {
    setKeyword(keyword);
  }

  const categoryHandler = (category) => {
    setCategory(category);
  }

  useEffect(() => {
  }, [keyword, category])

  return (
    <div>
      <Navbar search={searchHandler} category={categoryHandler} />
      <Product search={keyword} category={category}/>
    </div>
  )
}

export default Home;