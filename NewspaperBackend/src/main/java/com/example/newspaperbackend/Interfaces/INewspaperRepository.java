package com.example.newspaperbackend.Interfaces;

import com.example.newspaperbackend.Module.Newspaper;

import java.util.Date;

public interface INewspaperRepository {

    Newspaper getNewspaper(Date date);
}
