package com.example.newspaperbackend.Repository;

import com.example.newspaperbackend.Interfaces.IPersonRepository;
import com.example.newspaperbackend.Module.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository implements IPersonRepository {

    HashMap<UUID,Person> profiles;

    public UserRepository(){
        this.profiles = new HashMap();
    }

    private Person getUser(Person person){
        for (Map.Entry<UUID, Person> set: profiles.entrySet())
        {
            if(set.getValue().getEmail().equals(person.getEmail()) || set.getValue().getUsername().equals(person.getUsername())){
                return  set.getValue();
            }
        }
        return  null;
    }

    private Person searchForUser(Person profile){
        Person receivedPerson = getUser(profile);
        if(receivedPerson!=null){
            return receivedPerson;
        }
        return null;
    }

    @Override
    public String editInformation(Person person) {
        if(profiles.get(person.getId())!=null){
            if(getUser(person)!=null){
                return "User with such email or username already exists";
            }
            profiles.replace(person.getId(),person);
            return "User was updated";
        }
        return "User with such ID does not exist";
    }

    @Override
    public String createProfile(Person person) {
        if(profiles.get((person.getId()))==null){
            if(getUser(person)!=null){
                return "User with such email already exists";
            }
            profiles.put(person.getId(),person);
            return "User was created";
        }
        return "Such user already exists";
    }

    @Override
    public Person signIn(Person person) {
        return getUser(person);
    }

    @Override
    public boolean deleteProfile(UUID id) {
        Person person = profiles.get(id);
        if(person!=null){
            profiles.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Person findByUsername(String username) {
        Person profile = new Person(UUID.randomUUID(),username,"","","");
        return searchForUser(profile);
    }

    @Override
    public Person findByEmail(String email) {
        Person profile = new Person(UUID.randomUUID(),"",email,"","");
        return searchForUser(profile);
    }
}
