package br.com.sake.mapper;

import br.com.sake.dto.UserRequest;
import br.com.sake.dto.UserResponse;
import br.com.sake.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity (UserRequest request);


    UserResponse toResponse (User entity);


    List <UserResponse> toResponseList (List<User> userList);

}
