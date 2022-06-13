package com.example.newspaperbackend.Module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter

public class Person {

    private UUID id;
    private String email;
    private String username;
    private String password;
    private String role;

    public Person(@JsonProperty("email") String email,@JsonProperty("username") String username,@JsonProperty("password") String password){
        this.id = UUID.randomUUID();
        this.email=email;
        this.username=username;
        this.password=password;
        this.role="USER";
    }


    public Person(UUID id,String username, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.username=username;
        this.password = password;
        this.role=role;
    }
}
