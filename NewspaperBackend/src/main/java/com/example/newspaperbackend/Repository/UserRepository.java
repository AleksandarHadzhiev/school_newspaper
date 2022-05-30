package com.example.newspaperbackend.Repository;

import com.example.newspaperbackend.Interfaces.IPersonRepository;
import com.example.newspaperbackend.Module.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository implements IPersonRepository {

    List<Person> profiles;

    public UserRepository(){
        this.profiles = new ArrayList<>();
    }

    @Override
    public String editInformation(Person person) {
        for (Person p:profiles
             ) {
            if(person.getId().equals(p.getId())){
                if(getUser(person)!=null){
                    return "User with such crediantials already exists";
                }
                int indexOfAccount = profiles.indexOf(p);
                profiles.remove(indexOfAccount);
                profiles.add(person);
                return "Updated user information";
            }
        }
        return "No such user";
    }

    @Override
    public String createProfile(Person person) {
        if(getUser(person)!=null){
            return "User with such email already exists";
        }
        profiles.add(person);
        return "User was created";
    }

    private Person getUser(Person person){
        for (Person p: profiles
             ) {
            if(person.getEmail().equals(p.getEmail()) && person.getPassword().equals(p.getPassword())){
                return p;
            }
        }

        return null;
    }

    @Override
    public Person signIn(Person person) {
        return getUser(person);
    }

    @Override
    public boolean deleteProfile(UUID id) {
        for (Person wantedPerson: profiles
             ) {
            if(wantedPerson.getId().equals(id)){
                int indexOfAccount = profiles.indexOf(wantedPerson);
                profiles.remove(indexOfAccount);
                return true;
            }
        }
        return false;
    }

    @Override
    public Person findByUsername(String username) {
        for (Person p: profiles
        ) {
            if(p.getUsername().equals(username)){
                return p;
            }
        }

        return null;
    }

    @Override
    public Person findByEmaik(String email) {
        for (Person p: profiles
        ) {
            if(p.getEmail().equals(email)){
                return p;
            }
        }

        return null;
    }
}
