package az.rentcar.controller;

import az.rentcar.dto.customerDTOs.GetCustomer;
import az.rentcar.dto.customerDTOs.PostCustomer;
import az.rentcar.dto.customerDTOs.PutCustomer;
import az.rentcar.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @GetMapping
    public ResponseEntity<List<GetCustomer>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GetCustomer> create(@RequestBody PostCustomer postDto) {
        return ResponseEntity.ok(customerService.create(postDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCustomer> update(@PathVariable Long id,
                                              @RequestBody PutCustomer putDto) {
        return ResponseEntity.ok(customerService.update(id, putDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
