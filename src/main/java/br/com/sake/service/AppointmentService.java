package br.com.sake.service;

import br.com.sake.dto.AppointmentRequest;
import br.com.sake.dto.AppointmentResponse;
import br.com.sake.entity.Appointment;
import br.com.sake.entity.User;
import br.com.sake.mapper.AppointmentMapper;
import br.com.sake.repository.AppointmentRepository;
import br.com.sake.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppointmentService {

    private final UserRepository userRepository;
    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    public AppointmentResponse findById (Long id){


        return mapper.toResponse(repository.findById(id).orElseThrow());
    }

    public void insert (AppointmentRequest request){
        User user = userRepository.findById(request.userId())
                .orElseThrow();
        Appointment appointment = mapper.toEntity(request);
        appointment.setUser(user);
        repository.save(appointment);

    }

    public void update (Long id,AppointmentRequest request){

        Appointment appointment = repository.findById(id).orElseThrow();

        appointment.setName(request.name());
        appointment.setDescription(request.description());
        appointment.setDate(request.date());
        appointment.setTime(request.time());
        repository.save(appointment);
    }

    public void delete (Long id){
        repository.deleteById(id);
    }

}
