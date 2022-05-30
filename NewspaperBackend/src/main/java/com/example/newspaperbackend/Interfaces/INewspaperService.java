package com.example.newspaperbackend.Interfaces;

import com.example.newspaperbackend.Module.Newspaper;

import java.util.Date;

public interface INewspaperService {

    Newspaper getNewspaper(Date date);
}
