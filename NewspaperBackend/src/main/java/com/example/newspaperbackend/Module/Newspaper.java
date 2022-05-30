package com.example.newspaperbackend.Module;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

public class Newspaper {

    private Date dateOfDisplay;
    private List<Article> articles;

    public Newspaper(Date dateOfDisplay, List<Article> articles) {
        this.dateOfDisplay = dateOfDisplay;
        this.articles = articles;
    }
}
