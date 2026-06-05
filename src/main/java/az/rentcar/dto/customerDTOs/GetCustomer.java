package az.rentcar.dto.customerDTOs;

import lombok.Data;

@Data
public class GetCustomer {
    Long id;
    String Name;
    String phone;
    String driverLicenseNumber;
}
