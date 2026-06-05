package az.rentcar.dto.carDTOs;

import az.rentcar.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostCar {
    String brand;
    String model;
    Integer year;
    BigDecimal rentalPricePerDay;
    Long categoryId;
}
