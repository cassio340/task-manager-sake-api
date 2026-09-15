package br.com.sake.service;

import br.com.sake.entity.User;
import br.com.sake.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findById (Long id){
        return repository.findById(id).orElseThrow();
    }

}
