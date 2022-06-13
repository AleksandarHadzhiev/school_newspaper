package com.example.newspaperbackend.Interfaces;

import com.example.newspaperbackend.Module.Article;
import java.util.List;
import java.util.UUID;

public interface IArticleService {

    Article getArticle(UUID id);

    List<Article> getArticles(String date);

    List<Article> getAll();

    boolean createArticle(Article article);

    boolean editArticle(Article article);

    boolean removeArticle(UUID id);

    List<Article> getUserArticles(UUID id);
}
