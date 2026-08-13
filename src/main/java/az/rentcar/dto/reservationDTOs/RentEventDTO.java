package az.rentcar.dto.reservationDTOs;

import java.math.BigDecimal;

public record RentEventDTO(
        long id,
        long carId,
        long customerId,
        BigDecimal totalPrice) {
}

