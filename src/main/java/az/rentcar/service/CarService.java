package az.rentcar.service;

import az.rentcar.dto.carDTOs.GetCar;
import az.rentcar.dto.carDTOs.PostCar;
import az.rentcar.dto.carDTOs.PutCar;

import java.util.List;

public interface CarService {
    GetCar getById(Long id);
    List<GetCar> getAll();
    GetCar create(PostCar postDto);
    GetCar update(Long id, PutCar putDto);
    void delete(Long id);
}
