package br.com.sake.dto;

import br.com.sake.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest(String name, String description, LocalDate date, LocalTime time, Long userId) {
}
