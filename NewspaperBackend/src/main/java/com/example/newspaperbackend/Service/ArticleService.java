package com.example.newspaperbackend.Service;

import com.example.newspaperbackend.Interfaces.IArticleService;
import com.example.newspaperbackend.Interfaces.IarticleRepository;
import com.example.newspaperbackend.Module.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    IarticleRepository iarticleRepository;

    @Override
    public Article getArticle(UUID id) {
        return iarticleRepository.getArticle(id);
    }

    @Override
    public List<Article> getArticles(String date) {
        return iarticleRepository.getArticles(date);
    }

    @Override
    public List<Article> getAll() {
        return iarticleRepository.getAll();
    }

    @Override
    public boolean createArticle(Article article) {
        return iarticleRepository.createArticle(article);
    }

    @Override
    public boolean editArticle(Article article) {
        return iarticleRepository.editArticle(article);
    }

    @Override
    public boolean removeArticle(UUID id) {
        return iarticleRepository.removeArticle(id);
    }

    @Override
    public List<Article> getUserArticles(UUID id) {
        return iarticleRepository.getUserArticles(id);
    }
}
