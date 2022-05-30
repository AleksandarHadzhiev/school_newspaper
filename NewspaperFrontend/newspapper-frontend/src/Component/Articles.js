import { useEffect, useState } from "react";
import classes from "../css/Articles.module.css";
import {FaSearch,FaArrowLeft,FaArrowRight } from 'react-icons/fa';
import ArticleList from "./ArticleList";
import Sort from "../serivces/Sort";

function Articles(props){

    const [articles, setArticles] = useState(null);
    const [artilcesNumber, setArticlesNumber] = useState([0,1]);
    const [pages, setPages] = useState("");
    const [amountOfPages, setAmountOfPages] = useState(1);
    const [page, setPage] = useState(1);
    let numbers = artilcesNumber;
    const [title, setTitle] = useState("");

    useEffect(()=>{
        setArticles(props.articles);
        setAmountOfPages(Math.ceil(props.articles.length/2));

        if(title.length>=1){
            sortArticles(title);
        }
      },[title]);

    function getPages(){
        setPages(page + "/" + amountOfPages);
    }

    useEffect(()=>{
        getPages();
    },[articles,page,amountOfPages])

    function forwardPage(){
        let currentPage =page;
        if(currentPage<amountOfPages)
           { currentPage++;
            numbers[0] = numbers[1]+1;
            numbers[1] = numbers[0]+1;}
        else {currentPage=1;  numbers=[0,1];}
        setPage(currentPage);
        setArticlesNumber(numbers);
    }

    function previousPage(){
        let currentPage =page;
        if(currentPage>1)
        {
            currentPage--;
            numbers[0] = numbers[0]-1;
            numbers[1] = numbers[0]-1;
        }
        else 
        {
            numbers=[articles.length-2,articles.length-1];
            currentPage=amountOfPages;
        }
        setPage(currentPage);
        setArticlesNumber(numbers);
    }

    function sortArticles(title){
        const wanted = Sort.sortByTitle(articles,title);

        if(title.length === 0){
            setArticles(props.articles);
        }
        setArticles(wanted);
    }

    return(
        <div className={classes.component}>
            <div  className={classes.header}>
                <div className={classes.search}>
                    <input onChange={(e)=>{setTitle(e.target.value);}} placeholder="Title...."/>
                    <button><FaSearch/></button>
                </div>
                <div  className={classes.pages}>
                    <button onClick={previousPage}><FaArrowLeft/></button>
                    <p>{pages}</p>
                    <button onClick={forwardPage}><FaArrowRight/></button>
                </div>
            </div>
            <div className={classes.articles}>
               {articles !== null ?  articles.length >0 ? <ArticleList page={page} numbers={artilcesNumber} articles={articles}/> : null : null}
            </div>
        </div>
    )
}

export default Articles;