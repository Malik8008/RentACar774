package az.rentcar.service.impl;

import az.rentcar.dto.customerDTOs.GetCustomer;
import az.rentcar.dto.customerDTOs.PostCustomer;
import az.rentcar.dto.customerDTOs.PutCustomer;
import az.rentcar.entity.Customer;
import az.rentcar.exception.IdNotFoundException;
import az.rentcar.repository.CustomerRepository;
import az.rentcar.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetCustomer findById(Long id) {
        Customer customer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Customer not found with id: " + id));
        return modelMapper.map(customer, GetCustomer.class);
    }

    @Override
    public List<GetCustomer> getAll() {
        return customerRepository.findAllByIsDeletedFalse()
                .stream().map(cs -> modelMapper.map(cs, GetCustomer.class)).toList();
    }

    @Override
    public GetCustomer create(PostCustomer postDto) {
        Customer customer = new Customer();
        customer.setName(postDto.getName());
        customer.setPhone(postDto.getPhone());
        customer.setDriverLicenseNumber(postDto.getDriverLicenseNumber());

        Customer newCustomer = customerRepository.save(customer);
        return modelMapper.map(newCustomer, GetCustomer.class);
    }

    @Override
    public GetCustomer update(Long id, PutCustomer putDto) {
        Customer existCustomer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Customer not found with id: " + id));
        existCustomer.setName(putDto.getName());
        existCustomer.setPhone(putDto.getPhone());
        existCustomer.setDriverLicenseNumber(putDto.getDriverLicenseNumber());

        Customer updateCustomer = customerRepository.save(existCustomer);
        return modelMapper.map(updateCustomer, GetCustomer.class);
    }

    @Override
    public void delete(Long id) {
        Customer existCustomer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Customer not found with id: " + id));
        existCustomer.setDeleted(true);
        customerRepository.save(existCustomer);
    }

}

