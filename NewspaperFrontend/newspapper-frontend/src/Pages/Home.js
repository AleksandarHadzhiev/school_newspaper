import { useEffect, useState } from "react";
import Articles from "../Component/Articles";
import NavigationBar from "../Component/NavigationBar";
import ArticleServices from "../serivces/ArticleServices";
import classes from "../css/Home.module.css";
import DisplayWithoutArticles from "../Component/DisplayWithoutArticles";

function Home(){

  const [articles, setArticles] = useState(null);

  var today = new Date();
  const month = getMonth(today.getMonth() + 1);
  const date = today.getFullYear() + '-' + month + '-' + today.getDate();
  

  function getMonth(watedMonth){
      if(watedMonth<10){
        return "0"+watedMonth;
      }
      return watedMonth;
  }

  useEffect(()=>{
    ArticleServices.getArticlesForDate(date)
      .then((res)=>{
        if(res.data.length!==0){
          setArticles(res.data);
        }
      })
      .catch((err)=>{console.error(err);})
  },[])

    return(
        <div className={classes.home}>
            <NavigationBar/>
           {articles !== null ? <Articles articles={articles}/> : <DisplayWithoutArticles/>}
        </div>
    )
}

export default Home;