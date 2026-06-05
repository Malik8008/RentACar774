package az.rentcar.dto.carDTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PutCar {
    String brand;
    String model;
    Integer year;
    BigDecimal rentalPricePerDay;
    Long categoryId;
}
