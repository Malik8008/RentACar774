package az.rentcar.repository;

import az.rentcar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findByIdAndIsDeletedFalse(Long id);
    List<Car> findAllByIsDeletedFalse();
}
