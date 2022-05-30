import Article from "./Article";
import classes from "../css/ArticlesList.module.css";
import { useEffect, useState } from "react";

function ArticleList(props){

    const articles = props.articles;
    const numbers = props.numbers;
    const [article,setArticle] = useState(null);

    useEffect(()=>{
        if(articles.length >=2){
            if(articles[numbers[1]]!=null){
                setArticle(articles[numbers[1]]);

            }
        }
    },[props.page]);

    return (
        <div className={classes.component}>
            <div className={classes.element}>
                <Article article={articles[numbers[0]]}/>
            </div>
            <div className={classes.barrier}></div>
            <div className={classes.element}>
                { article!=null ?<Article article={article}/>: null}
            </div>
            
        </div>
    )
}

export default ArticleList;
