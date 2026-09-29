package br.com.sake.service;

import br.com.sake.entity.Appointment;
import br.com.sake.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public Appointment findById (Long id){
        return repository.findById(id).orElseThrow();
    }

}
