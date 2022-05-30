class Sort{
    sortByTitle(articles, title){

        const searchedArticles = [];

        articles.forEach(article => {
            if(article.title.includes(title)){
                searchedArticles.push(article);
            }
        });

        return searchedArticles;
    }
}

export default new Sort();