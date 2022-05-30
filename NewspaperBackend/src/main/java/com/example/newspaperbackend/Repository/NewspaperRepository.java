package com.example.newspaperbackend.Repository;

import com.example.newspaperbackend.Interfaces.INewspaperRepository;
import com.example.newspaperbackend.Module.Newspaper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class NewspaperRepository implements INewspaperRepository {

    List<Newspaper> newspapers;

    public NewspaperRepository(){
        this.newspapers=new ArrayList<>();
    }


    @Override
    public Newspaper getNewspaper(Date date) {
        for (Newspaper newspaper:newspapers
             ) {
            if(newspaper.getDateOfDisplay().equals(date)){
                return newspaper;
            }
        }
        return null;
    }
}
