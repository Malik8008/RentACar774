package az.rentcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue
    Long id;
    String Name;
    String phone;
    String driverLicenseNumber;
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL)
    @JsonIgnore
    List<Reservation> reservationList;
}
