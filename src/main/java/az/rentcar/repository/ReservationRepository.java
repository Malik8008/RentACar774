package az.rentcar.repository;

import az.rentcar.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByIdAndIsDeletedFalse(Long id);

    List<Reservation> findAllByIsDeletedFalse();

    List<Reservation> findAllByCarIdAndIsDeletedFalse(Long carId);
}
