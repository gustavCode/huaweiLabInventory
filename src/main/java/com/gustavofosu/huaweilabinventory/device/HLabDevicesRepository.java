package com.gustavofosu.huaweilabinventory.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HLabDevicesRepository extends JpaRepository<HLabDevices, Long> {

    List<HLabDevices> findByDeviceTypeIgnoreCase(String deviceType);

    HLabDevices findHLabDevicesByDeviceName(String deviceName);
}
