package com.example.newspaperbackend.Controller;


import com.example.newspaperbackend.Interfaces.INewspaperService;
import com.example.newspaperbackend.Module.Newspaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class NewspaperController {

    @Autowired
    INewspaperService iNewspaperService;

    @GetMapping("/newspaper/get/{date}")
    public Newspaper getNewspaper(@PathVariable("date")Date date){
        return iNewspaperService.getNewspaper(date);
    }

}
