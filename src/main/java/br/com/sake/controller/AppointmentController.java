package br.com.sake.controller;

import br.com.sake.dto.AppointmentRequest;
import br.com.sake.dto.AppointmentResponse;
import br.com.sake.entity.Appointment;
import br.com.sake.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService service;

    @GetMapping("/{id}")
    public AppointmentResponse findById (@PathVariable Long id){
        return service.findById(id);

    }

    @PostMapping
    public void insert (@RequestBody AppointmentRequest request){
        service.insert(request);
    }
}
