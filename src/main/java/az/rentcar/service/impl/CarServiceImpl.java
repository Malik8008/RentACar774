package az.rentcar.service.impl;

import az.rentcar.dto.carDTOs.GetCar;
import az.rentcar.dto.carDTOs.PostCar;
import az.rentcar.dto.carDTOs.PutCar;
import az.rentcar.entity.Car;
import az.rentcar.entity.Category;
import az.rentcar.exception.IdNotFoundException;
import az.rentcar.repository.CarRepository;
import az.rentcar.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements az.rentcar.service.CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public GetCar getById(Long id) {
        Car car = carRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new IdNotFoundException("Car not found with id: " + id));
        return modelMapper.map(car, GetCar.class);
    }

    @Override
    public List<GetCar> getAll() {
        return carRepository.findAllByIsDeletedFalse().stream()
                .map(car -> modelMapper.map(car, GetCar.class)).toList();
    }

    @Override
    public GetCar create(PostCar postDto) {
        Category category = findCategory(postDto.getCategoryId());
        Car newCar = new Car();
        newCar.setBrand(postDto.getBrand());
        newCar.setModel(postDto.getModel());
        newCar.setYear(postDto.getYear());
        newCar.setRentalPricePerDay(postDto.getRentalPricePerDay());
        newCar.setDeleted(false);
        newCar.setCategory(category);

        Car savedCar = carRepository.save(newCar);
        return modelMapper.map(savedCar, GetCar.class);
    }

    @Override
    public GetCar update(Long id, PutCar putDto) {
        Car existCar = carRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new IdNotFoundException(
                        "Car not found with id: " + id));

        Category category = findCategory(putDto.getCategoryId());

        existCar.setBrand(putDto.getBrand());
        existCar.setModel(putDto.getModel());
        existCar.setYear(putDto.getYear());
        existCar.setRentalPricePerDay(putDto.getRentalPricePerDay());
        existCar.setCategory(category);

        Car updatedCar = carRepository.save(existCar);
        return modelMapper.map(updatedCar, GetCar.class);
    }

    @Override
    public void delete(Long id) {
        Car existCar = carRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()->new IdNotFoundException(
                        "Car not found with id: " + id));
        existCar.setDeleted(true);
        carRepository.save(existCar);
    }

    private Category findCategory(Long categoryId) {
        return categoryRepository
                .findByIdAndIsDeletedFalse(categoryId)
                .orElseThrow(() ->
                        new IdNotFoundException(
                                "Category not found with id: " + categoryId));
    }
}
