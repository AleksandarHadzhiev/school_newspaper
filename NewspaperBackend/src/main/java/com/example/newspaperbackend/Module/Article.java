package com.example.newspaperbackend.Module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

public class Article {

    private UUID personId;
    private UUID articleId;
    private String title;
    private String textContent;
    private LocalDate dateOfCreation;
    private LocalDate dateToDisplay;

    public Article(@JsonProperty("personId") UUID personId,@JsonProperty("title") String title,@JsonProperty("textContent") String textContent){
        this.personId = personId;
        this.articleId = UUID.randomUUID();
        this.title = title;
        this.textContent = textContent;
        this.dateOfCreation = LocalDate.now();
        this.dateToDisplay = LocalDate.now();
    }

    public Article(UUID personId, UUID articleId, String title, String textContent,LocalDate dateOfCreation, LocalDate dateToDisplay) {
        this.personId = personId;
        this.articleId = articleId;
        this.title = title;
        this.textContent = textContent;
        this.dateOfCreation = dateOfCreation;
        this.dateToDisplay = dateToDisplay;
    }
}
