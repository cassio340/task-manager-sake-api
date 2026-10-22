package br.com.sake.service;

import br.com.sake.dto.UserRequest;
import br.com.sake.dto.UserResponse;

import br.com.sake.entity.User;
import br.com.sake.mapper.AppointmentMapper;
import br.com.sake.mapper.UserMapper;
import br.com.sake.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponse findById (Long id){

        User user = repository.findById(id).orElseThrow();
        return  mapper.toResponse(user);

    }

    public void insert (UserRequest request){
        repository.save(mapper.toEntity(request));

    }
    @Transactional
    public void update (UserRequest request,Long id){
        User user = repository.findById(id).orElseThrow();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

    }
    public void delete (Long id){
        repository.deleteById(id);
    }
}
