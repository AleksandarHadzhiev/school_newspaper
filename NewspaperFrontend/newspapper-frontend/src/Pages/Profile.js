import { useEffect, useState } from "react";
import Articles from "../Component/Articles";
import CreateArticle from "../Component/CreateArticle";
import DisplayWithoutArticles from "../Component/DisplayWithoutArticles";
import NavigationBar from "../Component/NavigationBar";
import PersonalInformation from "../Component/PersonalInformation";
import classes from "../css/Home.module.css";
import ArticleServices from "../serivces/ArticleServices";

function Profile(){

    const [articles, setArticles] = useState(null);
    const [writeArticle, setWriteArticle] = useState(false);
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(null);

    useEffect(()=>{
        if(JSON.parse(localStorage.getItem('user'))!=null && JSON.parse(localStorage.getItem('token'))!=null){
            setUser(JSON.parse(localStorage.getItem('user')));
            setToken(JSON.parse(localStorage.getItem('token')));
        }
    },[])

    useEffect(()=>{
        if(user !== null){
            ArticleServices.getArticlesForUser(user.id,token).then((res)=>{
                if(res.data.length!==0){
                    setArticles(res.data);
                }
              })
              .catch((err)=>{console.error(err);})
        }
    },[user])

    return (
        <div className={classes.home}>
            <NavigationBar/>
            <div className={classes.content}>
                <div className={classes.personalInformation}>
                    {user !== null ? <PersonalInformation user={user}/> : null}
                </div>
                <div className={classes.mid}>
                    <div className={classes.midContent}>
                        <p>Your stories</p>
                        <button onClick={()=>{setWriteArticle(true)}}>Write Article</button>
                    </div>
                </div>
                <div className={classes.articles}>
                    {articles !== null ? <Articles articles={articles}/> : <DisplayWithoutArticles/>}
                </div>
            </div>
            {writeArticle !== false ? <CreateArticle user={user}/> : null}
        </div>
    )
}

export default Profile;