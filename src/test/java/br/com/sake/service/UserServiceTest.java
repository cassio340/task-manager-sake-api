package br.com.sake.service;

import br.com.sake.dto.UserRequest;
import br.com.sake.dto.UserResponse;
import br.com.sake.entity.User;
import br.com.sake.exceptions.custom.NotFoundException;
import br.com.sake.mapper.UserMapper;
import br.com.sake.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    private User user;
    private UserResponse response;
    private UserRequest request;

    @BeforeEach
    public void setup (){
        user = new User();
        user.setId(1L);
        user.setName("user");
        user.setEmail("user23@email.com");
        user.setPassword("1234");
        response = new UserResponse("user","user23@email.com",null);
        request = new UserRequest("newUser","userNew@email.com","new1234");
    }

    @Nested
    @DisplayName("FindById tests")
    class FindByIdTests {
        @Test
        public void findByIdSuccessful() {
            Long id = 1L;

            when(repository.findById(id)).thenReturn(Optional.of(user));
            when(mapper.toResponse(user)).thenReturn(response);

            UserResponse result = service.findById(id);

            assertEquals(response,result);
            verify(repository).findById(id);
            verify(mapper).toResponse(user);

        }
        @Test
        public void findByIdNotFound (){
            Long id = 1L;

            when(repository.findById(id)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class,()-> service.findById(id));

            verify(repository).findById(id);
            verifyNoInteractions(mapper);

        }
    }

    @Nested
    @DisplayName("Insert tests")
    class InsertTests{
        @Test
        public void insertSuccessful(){

            when(mapper.toEntity(request)).thenReturn(user);
            when(repository.save(user)).thenReturn(user);
            when(mapper.toResponse(user)).thenReturn(response);

            UserResponse result = service.insert(request);

            assertEquals(response.name(), result.name());
            assertEquals(response.email(), result.email());
            assertEquals(response.appointment(), result.appointment());

            verify(mapper).toEntity(request);
            verify(repository).save(user);
            verify(mapper).toResponse(user);
        }

    }

    @Nested
    @DisplayName("Update tests")
    class UpdateTests{
        @Test
        public void updateSuccessful(){
            Long id = 1L;

            UserResponse updatedResponse = new UserResponse(
                    request.name(),
                    request.email(),
                    response.appointment()
            );
            when(repository.findById(id)).thenReturn(Optional.of(user));
            when(mapper.toResponse(user)).thenReturn(updatedResponse);

            UserResponse result = service.update(request,id);

            assertEquals(request.name(), user.getName());
            assertEquals(request.email(), user.getEmail());
            assertEquals(request.password(), user.getPassword());

            assertEquals(request.name(), result.name());
            assertEquals(request.email(), result.email());

            verify(repository).findById(id);
            verify(mapper).toResponse(user);

        }

        @Test
        public void updateUserNotFound (){
            Long id = 1L;
            when(repository.findById(id)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class,()-> service.update(request,id));

            verify(repository).findById(id);
            verifyNoInteractions(mapper);
        }
    }

    @Nested
    @DisplayName("Delete tests")
    class DeleteTests{
        @Test
        public void deleteSuccessful(){
            Long id = 1L;

            when(repository.findById(id)).thenReturn(Optional.of(user));

            service.delete(id);
            verify(repository).findById(id);
            verify(repository).deleteById(id);

        }
        @Test
        public void deleteUserNotFound(){
            Long id = 1L;

            when(repository.findById(id)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class,()-> service.delete(id));
            verify(repository).findById(id);
            verifyNoMoreInteractions(repository);

        }
    }
}
