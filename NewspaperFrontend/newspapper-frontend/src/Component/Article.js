import classes from "../css/Article.module.css";
import betterWorld from "../betterWorld.jpg";
import { useEffect, useState } from "react";
import ArticleAddition from "./ArticleAddition";

function Article(props){

    const [isTrue, setIsTrue] = useState(false);

   useEffect(()=>{
    if(window.location.pathname === "/profile"){
        setIsTrue(true);
    }
   },[window.location])

    return(
        <div className={classes.article}>
            <h2 className={classes.title}>{props.article.title}</h2>
            <div className={classes.content}>
                <div className={classes.contentElements}>
                    <img alt="" src={betterWorld}/>
                    <p>{props.article.textContent}</p>
                </div>
                <div className={classes.buttons}>
                    {isTrue===true ? <ArticleAddition article = {props.article}/> : null}
                </div>
            </div>

        </div>
    )
}

export default Article;