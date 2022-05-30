import { useState } from "react";
import ArticleServices from "../serivces/ArticleServices";
import classes from "../css/ArticleAddition.module.css";
import EditArticle from "./EditArticle";


function ArticleAddition(props){

    const [editArticle, setEditArticle] = useState(false);

    function removeArticle(){
        ArticleServices.removeArticle(props.article.articleId)
        .then((res)=>{
            if(res.data===true){
                window.location.reload();
            }
        })
        .catch((err)=>{console.error(err);})
      }
    return(
        <div className={classes.content}>
            <div className={classes.buttons}>
                <button onClick={removeArticle}>Remove</button>
                <button onClick={()=>{setEditArticle(true)}}>Edit</button>
            </div>
            {editArticle ? <EditArticle article={props.article} /> : null}
        </div>
    )
}

export default ArticleAddition;