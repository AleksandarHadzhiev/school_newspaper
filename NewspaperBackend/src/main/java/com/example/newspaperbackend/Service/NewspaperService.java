package com.example.newspaperbackend.Service;

import com.example.newspaperbackend.Interfaces.INewspaperRepository;
import com.example.newspaperbackend.Interfaces.INewspaperService;
import com.example.newspaperbackend.Module.Newspaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NewspaperService implements INewspaperService {

    @Autowired
    INewspaperRepository iNewspaperRepository;

    @Override
    public Newspaper getNewspaper(Date date) {
        return iNewspaperRepository.getNewspaper(date);
    }
}
