package br.com.sake.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponse(Long id,String name, String description, LocalDate date, LocalTime time) {}

