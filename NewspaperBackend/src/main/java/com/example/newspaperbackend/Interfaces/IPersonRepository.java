package com.example.newspaperbackend.Interfaces;

import com.example.newspaperbackend.Module.Person;
import java.util.UUID;

public interface IPersonRepository {

    String editInformation(Person person);

    String createProfile(Person person);

    Person signIn(Person person);

    boolean deleteProfile(UUID id);

    Person findByUsername(String username);

    Person findByEmail(String email);
}
