package com.gustavofosu.huaweilabinventory.device;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    //private fields
    private final HLabDevicesRepository hLabDevicesRepository;

    public DeviceService(HLabDevicesRepository hLabDevicesRepository) {
        this.hLabDevicesRepository = hLabDevicesRepository;
    }

    //public methods
    public List<HLabDevices> listAllLabDevices() {
        return hLabDevicesRepository.findAll();
    }

    public List<HLabDevices> listAllDeviceByMicrocontroller() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("Microcontroller");
    }

    public List<HLabDevices> listAllDeviceByActuator() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("actuator");
    }

    public List<HLabDevices> listAllDeviceBySensor() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("sensor");
    }

    public List<HLabDevices> listAllDeviceByOther() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("other");
    }

    public void addNewDevice(HLabDevices device) {
        LocalDateTime dateTime = LocalDateTime.now();
        device.setDateModified(dateTime);
        device.setNumberTaken(device.getNumberTaken());
        hLabDevicesRepository.save(device);
    }

    public Optional<HLabDevices> getDeviceById(Long id) {
        return hLabDevicesRepository.findById(id);
    }

    public void updateDevice(HLabDevices labDevice) {
        LocalDateTime dateTime = LocalDateTime.now();
        labDevice.setDateModified(dateTime);
        labDevice.setNumberTaken(labDevice.getNumberTaken());
        hLabDevicesRepository.save(labDevice);
    }

    public Long getDeviceFromHLabTable(HLabDevices labDevice) {
        return labDevice.getDeviceID();
    }

    public void deleteDevice(Long id) {
        hLabDevicesRepository.deleteById(id);
    }
}
