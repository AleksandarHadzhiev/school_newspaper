package com.example.newspaperbackend.Controller;

import com.example.newspaperbackend.Interfaces.IArticleService;
import com.example.newspaperbackend.Module.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@CrossOrigin("*")
@RestController
public class ArticleController {

    @Autowired
    IArticleService iArticleService;

    @GetMapping("/article/get/article/{id}")
    public Article getArticle(@PathVariable("id") UUID articleId){
        return iArticleService.getArticle(articleId);
    }

    @DeleteMapping("/article/removeArticle/{id}")
    public boolean removeArticle(@PathVariable("id") UUID id){
        return iArticleService.removeArticle(id);
    }

    @PostMapping("/article/createArticle")
    public boolean createArticle(@RequestBody Article article){
        return iArticleService.createArticle(article);
    }

    @PutMapping("/article/edit")
    public boolean editArticle(@RequestBody Article article){
        return iArticleService.editArticle(article);
    }

    @GetMapping("/article/get/all/{date}")
    public List<Article> getAll(@PathVariable("date")String date){
        return iArticleService.getArticles(date);
    }

    @GetMapping("/article/get/all/forUser/{id}")
    public List<Article> getAllForUser(@PathVariable("id") String id){
        UUID user_id = UUID.fromString(id);
        return iArticleService.getUserArticles(user_id);
    }

    @GetMapping("/article/get/all")
    public List<Article> getAll(){
        return iArticleService.getAll();
    }
}
