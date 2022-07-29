package com.gustavofosu.huaweilabinventory.device_history;

import com.gustavofosu.huaweilabinventory.device.HLabDevices;

import javax.persistence.*;

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
    private Long deviceHistoryID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_name"
    )
    private HLabDevices deviceName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_type"
    )
    private HLabDevices deviceType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_description"
    )
    private HLabDevices deviceDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "device_quantity"
    )
    private HLabDevices deviceQuantity;

    // == public methods ==
    public Long getDeviceHistoryID() {
        return deviceHistoryID;
    }

    public void setDeviceHistoryID(Long deviceHistoryID) {
        this.deviceHistoryID = deviceHistoryID;
    }

    public HLabDevices getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(HLabDevices deviceName) {
        this.deviceName = deviceName;
    }

    public HLabDevices getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(HLabDevices deviceType) {
        this.deviceType = deviceType;
    }

    public HLabDevices getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(HLabDevices deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public HLabDevices getDeviceQuantity() {
        return deviceQuantity;
    }

    public void setDeviceQuantity(HLabDevices deviceQuantity) {
        this.deviceQuantity = deviceQuantity;
    }
}
