package br.com.sake.service;

import br.com.sake.dto.AppointmentResponse;
import br.com.sake.entity.Appointment;
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

    @BeforeEach
    public void setup(){
        appointment = new Appointment();
        appointment.setId(1L);
    }

    @Nested
    @DisplayName("FindByID tests")
    class FindByIdTests {
        @Test
        @DisplayName("appointment successfully found")
        public void findByIdSuccessful() {

            AppointmentResponse response = new AppointmentResponse(1L, "Task", "description",
                    LocalDate.parse("2026-12-10"), LocalTime.parse("12:32:12"));

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


}
