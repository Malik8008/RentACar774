package az.rentcar.service;

import az.rentcar.dto.reservationDTOs.GetReservation;
import az.rentcar.dto.reservationDTOs.PostReservation;
import az.rentcar.dto.reservationDTOs.PutReservation;

import java.util.List;

public interface ReservationService {
    GetReservation getById(Long id);
    List<GetReservation> getAll();
    GetReservation save(PostReservation postDto);
    GetReservation update(Long id, PutReservation putDto);
    void delete(Long id);
}
