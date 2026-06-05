package az.rentcar.service;

import az.rentcar.dto.customerDTOs.GetCustomer;
import az.rentcar.dto.customerDTOs.PostCustomer;
import az.rentcar.dto.customerDTOs.PutCustomer;

import java.util.List;

public interface CustomerService {
    GetCustomer findById(Long id);

    List<GetCustomer> getAll();

    GetCustomer create(PostCustomer postDto);

    GetCustomer update(Long id, PutCustomer putDto);

    void delete(Long id);
}
