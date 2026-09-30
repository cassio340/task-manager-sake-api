package br.com.sake.service;

import br.com.sake.dto.AppointmentResponse;
import br.com.sake.entity.Appointment;
import br.com.sake.mapper.AppointmentMapper;
import br.com.sake.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    public AppointmentResponse findById (Long id){


        return mapper.toResponse(repository.findById(id).orElseThrow());
    }

}
