package br.com.sake.dto;

import br.com.sake.entity.Appointment;

import java.util.List;

public record UserResponse(String name, String email, List<AppointmentResponse> appointment) {}

