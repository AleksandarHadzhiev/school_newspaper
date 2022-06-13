package com.example.newspaperbackend.Service;

import com.example.newspaperbackend.Interfaces.IPersonRepository;
import com.example.newspaperbackend.Interfaces.IPersonService;
import com.example.newspaperbackend.Module.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServie implements IPersonService, UserDetailsService {

    // FIELD
    @Autowired
    IPersonRepository iPersonRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    // MAIN METHODS
    @Override
    public String editInformation(Person person)
    {
        String message = crediantialsChecker(person);
        if(message == null){
            return iPersonRepository.editInformation(person);
        }
        return message;
    }

    @Override
    public String createProfile(Person person) {
        String message = crediantialsChecker(person);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        if(message == null){
            return iPersonRepository.createProfile(person);
        }
        return message;
    }

    @Override
    public Person signIn(Person person) {
        String message = crediantialsChecker(person);
        if(message == null){
            return iPersonRepository.signIn(person);
        }
        return null;
    }

    @Override
    public boolean deleteProfile(UUID id) {
        return iPersonRepository.deleteProfile(id);
    }

    @Override
    public Person findByUsername(String username) {
        return iPersonRepository.findByEmail(username);
    }

    // HELPING METHOD
    private String crediantialsChecker(Person person){
        if(!person.getEmail().contains("@")){
            return "Not a valid email";
        }
        else if(person.getEmail().isBlank()){
            return "Missing email";
        }
        else if(person.getPassword().length()<6){
            return "Weak Password";
        }
        else if(person.getPassword().isBlank()){
            return "Missing password";
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = Optional.ofNullable(iPersonRepository.findByUsername(username));
        if(user==null){
            System.out.println("User not found");
            throw  new UsernameNotFoundException("User not found in the database");
        }
        else{
            System.out.println("Existing user");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                user.get().getPassword(),getAuthorities(user.get().getRole()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
