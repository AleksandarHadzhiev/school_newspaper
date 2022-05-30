package com.example.newspaperbackend.Repository;

import com.example.newspaperbackend.Interfaces.IarticleRepository;
import com.example.newspaperbackend.Module.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class ArticleRepository implements IarticleRepository {


    List<Article> articles;

    public ArticleRepository(){
        this.articles= new ArrayList<>();
    }

    @Override
    public Article getArticle(UUID id) {

        return getArticleById(id);
    }



    private Article receiveArticle(Article article){
        for (Article a: articles
        ) {
            if(article.getArticleId().equals(a.getArticleId())){
                return a;
            }
        }
        return null;
    }

    @Override
    public List<Article> getArticles(String date) {
        List<Article> wantedArticles = new ArrayList<>();

        for (Article article:articles
             ) {
            String dateToDisplay = String.valueOf(article.getDateToDisplay());
            if(dateToDisplay.equals(date)){
                wantedArticles.add(article);
            }
        }
        return wantedArticles;
    }

    @Override
    public List<Article> getAll() {
        return articles;
    }

    @Override
    public boolean createArticle(Article article) {
        if(receiveArticle(article)==null){
            articles.add(article);
            return true;
        }
        return false;
    }

    @Override
    public boolean editArticle(Article article) {
        Article a = receiveArticle(article);
        if(a!=null){
            a.setTitle(article.getTitle());
            a.setTextContent(article.getTextContent());
            a.setDateToDisplay(article.getDateToDisplay());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeArticle(UUID id) {
        Article article = getArticleById(id);
        if(article!=null) {
            int indexOfArticle = articles.indexOf(article);
            articles.remove(indexOfArticle);
            return true;
        }
        return false;
    }

    private Article getArticleById(UUID id){
        for (Article article:articles
        ) {
            if(article.getArticleId().equals(id)){
                return article;
            }
        }
        return null;
    }

    @Override
    public List<Article> getUserArticles(UUID id) {
        List<Article> userArticles = new ArrayList<>();
        for (Article article:articles
             ) {
            if(article.getPersonId().equals(id)){
                userArticles.add(article);
            }
        }
        return userArticles;
    }
}
