package br.com.sake.mapper;

import br.com.sake.dto.AppointmentRequest;
import br.com.sake.dto.AppointmentResponse;
import br.com.sake.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "id" , ignore = true)
    Appointment toEntity(AppointmentRequest request);

    AppointmentResponse toResponse(Appointment appointment);

    List<AppointmentResponse> toResponseList(List <Appointment> appointmentList );
}
