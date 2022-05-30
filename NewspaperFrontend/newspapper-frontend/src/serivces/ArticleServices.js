import axios from "axios";

const ARTICLE_API_BASE_URL = "http://localhost:8080/article/";

class ArticleServices{

    getArticle(id,token){
        return axios.get(ARTICLE_API_BASE_URL+"get/article/"+id,{ headers: {"Authorization" : `Bearer ${token}`}});
    }

    getArticlesForUser(id,token){
        return axios.get(ARTICLE_API_BASE_URL+"get/all/forUser/"+id,{ headers: {"Authorization" : `Bearer ${token}`}})
    }

    editArticle(article,token){
        return axios.put(ARTICLE_API_BASE_URL+"edit",article,{ headers: {"Authorization" : `Bearer ${token}`}});
    }

    createArticle(article,token){
        return axios.post(ARTICLE_API_BASE_URL+"createArticle",article,{ headers: {"Authorization" : `Bearer ${token}`}});
    }

    removeArticle(id,token){
        return axios.delete(ARTICLE_API_BASE_URL+"removeArticle/"+id,null,{ headers: {"Authorization" : `Bearer ${token}`}});
    }

    getArticlesForDate(date){
        return axios.get(ARTICLE_API_BASE_URL+"get/all/"+date)
    }
}

export default new ArticleServices();