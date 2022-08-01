package com.gustavofosu.huaweilabinventory.device_history;

import com.gustavofosu.huaweilabinventory.device.HLabDevices;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter                     // Annotation to add getters for the fields
@Setter                     // Annotation to add setters for the fields
@Entity
@Table(name = "device_history")
public class HLabDeviceHistory {

    // == private fields ==
    @Id
    @SequenceGenerator(
            name = "device_history_sequence",
            sequenceName = "device_history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_history_sequence"
    )
    @Column(
            name = "dh_id",
            nullable = false
    )
    private Long deviceHistoryID;               // id of the device history

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_name"
    )
    private HLabDevices deviceName;             // name of device

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_type"
    )
    private HLabDevices deviceType;             // type of device

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_description"
    )
    private HLabDevices deviceDescription;      // description of device

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_quantity"
    )
    private HLabDevices deviceQuantity;         // quantity of device

}
