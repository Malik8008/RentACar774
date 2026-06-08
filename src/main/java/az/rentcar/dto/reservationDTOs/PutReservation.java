package az.rentcar.dto.reservationDTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PutReservation {
    LocalDate startReservationDate;
    LocalDate endReservationDate;
    Long carId;
    Long customerId;
    BigDecimal totalPrice;
}
