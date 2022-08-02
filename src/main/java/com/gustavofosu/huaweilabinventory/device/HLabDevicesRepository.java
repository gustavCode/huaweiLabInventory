package com.gustavofosu.huaweilabinventory.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HLabDevicesRepository extends JpaRepository<HLabDevices, Long> {

    // method to find and return device by its type ignoring case sensitivity
    List<HLabDevices> findByDeviceTypeIgnoreCase(String deviceType);
}
