package br.com.sake.service;

import br.com.sake.dto.UserResponse;

import br.com.sake.mapper.UserMapper;
import br.com.sake.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponse findById (Long id){

        return  mapper.toResponse(repository.findById(id).orElseThrow());
    }

}
