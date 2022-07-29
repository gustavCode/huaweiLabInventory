package com.gustavofosu.huaweilabinventory.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "devices")
public class HLabDevices {

    // == private fields ==
    @Id
    @SequenceGenerator(
            name = "device_sequence",
            sequenceName = "device_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_sequence"
    )
    private Long deviceID;
    private String deviceName;
    private String deviceType;

    @Column(
            name = "device_description",
            columnDefinition = "TEXT"
    )
    private String deviceDescription;
    private int deviceQuantity;
    private int numberTaken;
    private LocalDateTime dateModified;
}
