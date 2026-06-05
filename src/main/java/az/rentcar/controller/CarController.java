package az.rentcar.controller;

import az.rentcar.dto.carDTOs.GetCar;
import az.rentcar.dto.carDTOs.PostCar;
import az.rentcar.dto.carDTOs.PutCar;
import az.rentcar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/{id}")
    public ResponseEntity<GetCar> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<GetCar>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }

    @PostMapping
    public ResponseEntity<GetCar> create(@RequestBody PostCar postDto) {
        return ResponseEntity.ok(carService.create(postDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCar> update(@PathVariable Long id,
                                         @RequestBody PutCar putDto) {
        return ResponseEntity.ok(carService.update(id, putDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
