package az.rentcar.service.impl;

import az.rentcar.dto.reservationDTOs.GetReservation;
import az.rentcar.dto.reservationDTOs.PostReservation;
import az.rentcar.dto.reservationDTOs.PutReservation;
import az.rentcar.entity.Car;
import az.rentcar.entity.Customer;
import az.rentcar.entity.Reservation;
import az.rentcar.exception.CarNotAvailableException;
import az.rentcar.exception.IdNotFoundException;
import az.rentcar.repository.CarRepository;
import az.rentcar.repository.CustomerRepository;
import az.rentcar.repository.ReservationRepository;
import az.rentcar.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetReservation getById(Long id) {
        Reservation reservation = reservationRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Reservation not found with id: " + id));
        return modelMapper.map(reservation, GetReservation.class);
    }

    @Override
    public List<GetReservation> getAll() {
        return reservationRepository.findAllByIsDeletedFalse().stream()
                .map(rs -> modelMapper.map(rs, GetReservation.class)).toList();
    }

    @Override
    public GetReservation save(PostReservation postDto) {
        Car car = carRepository.findById(postDto.getCarId())
                .orElseThrow(() -> new IdNotFoundException("Car not found with id: " + postDto.getCarId()));
        Customer customer = customerRepository.findById(postDto.getCustomerId())
                .orElseThrow(() -> new IdNotFoundException("Customer not found with id: " + postDto.getCustomerId()));

        if (!isAvailableCar(postDto.getCarId(),
                postDto.getStartReservationDate(),
                postDto.getEndReservationDate())) {
            throw new CarNotAvailableException("Car is not available for selected dates");
        }

        long days = ChronoUnit.DAYS.between(
                postDto.getStartReservationDate(), postDto.getEndReservationDate()
        );

        BigDecimal totalPrice = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(days));
        Reservation reservation = new Reservation();

        reservation.setStartReservationDate(postDto.getStartReservationDate());
        reservation.setEndReservationDate(postDto.getEndReservationDate());
        reservation.setTotalPrice(totalPrice);
        reservation.setCar(car);
        reservation.setCustomer(customer);
        Reservation saveReservation = reservationRepository.save(reservation);
        return modelMapper.map(saveReservation, GetReservation.class);
    }

    @Override
    public GetReservation update(Long id, PutReservation putDto) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Boolean isAvailableCar(Long carId, LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationRepository.findAllByCarIdAndIsDeletedFalse(carId);
        for (Reservation reservation : reservations) {
            if (!(endDate.isBefore(reservation.getStartReservationDate()) ||
                    startDate.isAfter(reservation.getEndReservationDate()))) {
                return false;
            }
        }
        return true;
    }
}
