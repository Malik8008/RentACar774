package az.rentcar.dto.customerDTOs;

import lombok.Data;

@Data
public class PutCustomer {
    String Name;
    String phone;
    String driverLicenseNumber;
}
