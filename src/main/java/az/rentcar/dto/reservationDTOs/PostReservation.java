package az.rentcar.dto.reservationDTOs;

import az.rentcar.dto.carDTOs.GetCar;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PostReservation {
    LocalDate startReservationDate;
    LocalDate endReservationDate;
    Long carId;
    Long customerId;
}
