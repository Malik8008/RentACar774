package az.rentcar.dto.reservationDTOs;

import az.rentcar.dto.carDTOs.GetCar;
import az.rentcar.dto.customerDTOs.GetCustomer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class GetReservation {
    Long id;
    LocalDate startReservationDate;
    LocalDate endReservationDate;
    BigDecimal totalPrice;
    GetCar car;
    GetCustomer customer;
}
