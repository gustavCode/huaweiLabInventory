package com.gustavofosu.huaweilabinventory.device_taken;

import com.gustavofosu.huaweilabinventory.device.HLabDevices;
import com.gustavofosu.huaweilabinventory.user.HLabUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter             // Annotation to add getters for the fields
@Setter             // Annotation to add setters for the fields
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device_taken")
public class HLabDeviceTaken {

    // == private fields ==
    @Id
    @SequenceGenerator(
            name = "device_taken_sequence",
            sequenceName = "device_taken_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_taken_sequence"
    )
    @Column(
            name = "dt_id",
            nullable = false
    )
    private Long deviceTakenID;             // id of the device taken

    @Column(

            name = "dt_quantity",
            nullable = false
    )
    private int deviceTakenQuantity;      // quantity of the device taken

    @Column(
            name = "dt_date_taken",
            nullable = false
    )
    private LocalDate deviceTaken_DateTaken;    //date the device was taken

    @Column(
            name = "dt_date_returned"
    )
    private LocalDate deviceTaken_DateReturned; // date the device was returned

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userID"
    )
    private HLabUsers user;                     // user who took device

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "device_id",
            referencedColumnName = "deviceID"
    )
    private HLabDevices device;               // device that was taken
}
