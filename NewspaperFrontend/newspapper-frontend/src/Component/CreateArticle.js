import { useEffect, useState } from "react";
import classes from "../css/CreateArticle.module.css";
import ArticleServices from "../serivces/ArticleServices";
import ArticleChecker from "../serivces/checkers/ArticleChecker";
import ErrorMessage from "./ErrorMessage";
function CreateArticle(props){

    const [articleTitle, setAticleTitle] = useState("");
    const [articleContent, setArticleContent] = useState("");
    const [errorMessage, setErrorMessage] = useState(null);
    const [token, setToken] = useState(null);

    useEffect(()=>{
        if( JSON.parse(localStorage.getItem('token'))!=null){
            setToken(JSON.parse(localStorage.getItem('token')));
        }
    },[])
    useEffect(()=>{
        if(errorMessage!=null){
            setInterval(()=>{
                setErrorMessage(null);
            },1800)
        }
    },[errorMessage])

    const article = {
        "personId":props.user.id,
        "title":articleTitle,
        "textContent":articleContent
      };

    function checkArticle(article){
        return ArticleChecker.emptyInput(article.title,article.textContent);
    }

    function createArticle(){

        const message = checkArticle(article);

        if(message===""){
            ArticleServices.createArticle(article,token).then((res)=>{
                    if(res.data===true){
                        window.location="/";
                    }
                    setErrorMessage("There was a problem try again later :(")
                }).catch((err)=>{
                  console.error(err);
                })
        }
        else if(message!==""){
            setErrorMessage(message);
        }
      }

    return (
        <div className={classes.background}>
            <div className={classes.content}>
                <form className={classes.form}>
                    <div className={classes.element}>
                        <label>Title:</label>
                        <input type="text" onChange={(e)=>{setAticleTitle(e.target.value)}} placeholder="Input title here...."/>
                    </div>
                    <div className={classes.element}>
                        <label>Content:</label>
                        <textarea onChange={(e)=>{setArticleContent(e.target.value)}} placeholder="Input content here...." rows="15" cols="50"/>
                    </div>
                    <button onClick={(e)=>{e.preventDefault(); createArticle()}}>Submit</button>
                </form>
            </div>
            {errorMessage !== null ? <ErrorMessage errorMessage={errorMessage}/> : null}
        </div>
    )
}

export default CreateArticle;