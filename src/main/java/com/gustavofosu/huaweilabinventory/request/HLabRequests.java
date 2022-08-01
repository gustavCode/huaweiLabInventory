package com.gustavofosu.huaweilabinventory.request;

import com.gustavofosu.huaweilabinventory.device.HLabDevices;
import com.gustavofosu.huaweilabinventory.user.HLabUsers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter                 // Annotation to add getters for the fields
@Setter                 // Annotation to add setters for the fields
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
public class HLabRequests {

    // == private fields ==
    @Id
    @SequenceGenerator(
            name = "request_sequence",
            sequenceName = "request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "request_sequence"
    )
    private Long requestID;                 // id for device request
    private int requestQuantity;            // quantity of device requested
    private String requestStatus;           // status of device requested

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userID"
    )
    private HLabUsers user;                 // user who requested for the device

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "device_id",
            referencedColumnName = "deviceID"
    )
    private HLabDevices device;             // device that was requested
}
