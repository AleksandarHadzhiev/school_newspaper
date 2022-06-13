package com.example.newspaperbackend.Controller;

import com.example.newspaperbackend.Interfaces.IPersonService;
import com.example.newspaperbackend.Module.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    IPersonService iPersonService;

    @PutMapping("/user/edit/information")
    public String editInformation(@RequestBody Person person) {
        return iPersonService.editInformation(person);
    }

    @PostMapping("/user/create/profile")
    public String createProfile(@RequestBody Person person) {
        return iPersonService.createProfile(person);
    }

    @GetMapping("/user/sign/in/{username}")
    public Person findByUsername(@PathVariable("username") String username) {
        return iPersonService.findByUsername(username);
    }

    @DeleteMapping("/user/delete/profile/{id}")
    public boolean deleteProfile(@PathVariable("id") UUID id) {
        return iPersonService.deleteProfile(id);
    }
}
