package az.rentcar.dto.carDTOs;

import az.rentcar.dto.categoryDTOs.GetCategory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetCar {
    Long id;
    String brand;
    String model;
    Integer year;
    BigDecimal rentalPricePerDay;
    GetCategory category;
}
