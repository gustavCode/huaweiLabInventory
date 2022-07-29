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
@Getter
@Setter
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
    private Long deviceTakenID;

    @Column(

            name = "dt_quantity",
            nullable = false
    )
    private int deviceTakenQuantity;

    @Column(
            name = "dt_date_taken",
            nullable = false
    )
    private LocalDate deviceTaken_DateTaken;

    @Column(
            name = "dt_date_returned"
    )
    private LocalDate deviceTaken_DateReturned;

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userID"
    )
    private HLabUsers user;

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "device_id",
            referencedColumnName = "deviceID"
    )
    private HLabDevices device;
}
