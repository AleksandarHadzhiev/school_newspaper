package com.example.newspaperbackend.Repository;

import com.example.newspaperbackend.Interfaces.IarticleRepository;
import com.example.newspaperbackend.Module.Article;
import com.example.newspaperbackend.Module.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class ArticleRepository implements IarticleRepository {

    HashMap<UUID,Article> articles;

    public ArticleRepository(){
        this.articles= new HashMap();
    }

    private List<Article> GetArticles(UUID userID, String date){
        List<Article> sortedArticles = new ArrayList();
        for (Map.Entry<UUID, Article> set: articles.entrySet())
        {
            Article article = set.getValue();
            UUID wantedID = article.getPersonId();
            String wantedDate = String.valueOf(article.getDateToDisplay());
            if(wantedID.equals(userID) || wantedDate.equals(date)){
                sortedArticles.add(article);
            }
        }
        return  sortedArticles;
    }

    @Override
    public Article getArticle(UUID id) {
        return articles.get(id);
    }

    @Override
    public List<Article> getArticlesByDate(String date) {
        return GetArticles(null,date);
    }

    @Override
    public List<Article> getAll() {
        return ((List<Article>) articles.values());
    }

    @Override
    public boolean createArticle(Article article) {
        if(article!=null){
            articles.put(article.getArticleId(),article);
            return true;
        }
        return false;
    }

    @Override
    public boolean editArticle(Article article) {
        Article a = articles.get(article.getArticleId());
        if(a!=null){
            articles.replace(a.getArticleId(),article);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeArticle(UUID id) {
        Article article = articles.get(id);
        if(article!=null) {
            articles.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Article> getUserArticles(UUID id) {
        return GetArticles(id,null);
    }
}
