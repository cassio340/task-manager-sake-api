package br.com.sake.service;

import br.com.sake.dto.UserResponse;

import br.com.sake.entity.User;
import br.com.sake.mapper.AppointmentMapper;
import br.com.sake.mapper.UserMapper;
import br.com.sake.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    private final AppointmentMapper appointmentMapper;
    public UserResponse findById (Long id){

        User user = repository.findById(id).orElseThrow();


        return  mapper.toResponse(user);
    }

}
