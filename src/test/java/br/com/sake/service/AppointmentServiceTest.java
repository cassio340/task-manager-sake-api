package br.com.sake.service;

import br.com.sake.dto.AppointmentRequest;
import br.com.sake.dto.AppointmentResponse;
import br.com.sake.entity.Appointment;
import br.com.sake.entity.User;
import br.com.sake.exceptions.custom.NotFoundException;
import br.com.sake.mapper.AppointmentMapper;
import br.com.sake.repository.AppointmentRepository;
import br.com.sake.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AppointmentRepository repository;

    @Mock
    private AppointmentMapper mapper;


    @InjectMocks
    private AppointmentService service;

    private Appointment appointment;
    private AppointmentResponse response;
    private AppointmentRequest request;

    @BeforeEach
    public void setup(){
        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setName("task");
        appointment.setDescription("description");
        appointment.setDate(LocalDate.parse("2026-12-10"));
        appointment.setTime(LocalTime.parse("12:32:12"));

        response = new AppointmentResponse(1L, "task", "description",
                LocalDate.parse("2026-12-10"), LocalTime.parse("12:32:12"));

        request = new AppointmentRequest("task","description",
                LocalDate.parse("2026-12-10"), LocalTime.parse("12:32:12"),1L);
    }

    @Nested
    @DisplayName("FindByID tests")
    class FindByIdTests {
        @Test
        @DisplayName("appointment successfully found")
        public void findByIdSuccessful() {


            when(repository.findById(1L)).thenReturn(Optional.of(appointment));
            when(mapper.toResponse(appointment)).thenReturn(response);



            AppointmentResponse result = service.findById(1L);


            assertSame(response, result);
            verify(repository).findById(1L);
            verify(mapper).toResponse(appointment);
        }

        @Test
        @DisplayName("appointment not found")
        public void findByIdNotFound() {
            when(repository.findById(2L)).thenReturn(Optional.empty());



            assertThrows(NotFoundException.class,()-> service.findById(2L));

            verify(repository).findById(2L);
            verifyNoInteractions(mapper);

        }

    }

    @Nested
    @DisplayName("Insert tests")
    class InsertTests{

        @Test
        public void insertSuccessful(){
            User user = mock(User.class );


            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(mapper.toEntity(request)).thenReturn(appointment);
            when(repository.save(appointment)).thenReturn(appointment);
            when(mapper.toResponse(appointment)).thenReturn(response);

            AppointmentResponse savedResponse = service.insert(request);

            assertSame(savedResponse,response);
            assertSame(appointment.getUser(),user);
            verify(userRepository).findById(1L);
            verify(mapper).toEntity(request);
            verify(repository).save(appointment);
            verify(mapper).toResponse(appointment);

        }
        @Test
        public void insertUserNotFound (){

            when(userRepository.findById(1L)).thenReturn(Optional.empty());


            assertThrows(NotFoundException.class,()-> service.insert(request));
            verify(userRepository).findById(1L);
            verifyNoInteractions(mapper,repository);

        }

    }

}
