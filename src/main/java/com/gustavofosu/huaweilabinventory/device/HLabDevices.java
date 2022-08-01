package com.gustavofosu.huaweilabinventory.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter             // Annotation to add getters for all fields
@Setter            // Annotation to add setters for all fields
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
    private Long deviceID;             // device id
    private String deviceName;         // name of device
    private String deviceType;         // type of device

    @Column(
            name = "device_description",
            columnDefinition = "TEXT"
    )
    private String deviceDescription;   // description of the device
    private int deviceQuantity;         // quantity of device
    private int numberTaken;            // number of device taken
    private LocalDateTime dateModified; // date the device was modified
}
