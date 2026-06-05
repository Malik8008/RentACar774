package az.rentcar.controller;

import az.rentcar.dto.reservationDTOs.GetReservation;
import az.rentcar.dto.reservationDTOs.PostReservation;
import az.rentcar.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationServiceImpl reservationService;

    @GetMapping
    public ResponseEntity<List<GetReservation>> getAll(){
        return ResponseEntity.ok(reservationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReservation> getById(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GetReservation> create(@RequestBody PostReservation postDto){
        return ResponseEntity.ok(reservationService.save(postDto));
    }
}
