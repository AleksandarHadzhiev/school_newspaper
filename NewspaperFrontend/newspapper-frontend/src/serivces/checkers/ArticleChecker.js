class ArticleChecker{
    emptyInput(title,content){
        if(title.trim() === ""){
            return "Missing Title";
        }
        else if(content.trim() === ""){
            return "Missing Content";
        }
        return "";
    }
}

export default new ArticleChecker();