package br.com.sake.controller;

import br.com.sake.entity.User;
import br.com.sake.service.UserService;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public User findById (@PathVariable Long id){
        User user = service.findById(id);
        return user;
    }

}
