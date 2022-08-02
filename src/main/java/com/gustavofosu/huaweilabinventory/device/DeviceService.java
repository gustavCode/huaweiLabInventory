package com.gustavofosu.huaweilabinventory.device;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    //private fields
    private final HLabDevicesRepository hLabDevicesRepository;

    // constructor injection with field as parameter
    public DeviceService(HLabDevicesRepository hLabDevicesRepository) {
        this.hLabDevicesRepository = hLabDevicesRepository;
    }

    //public methods
    // method to find and return a list of all device
    public List<HLabDevices> listAllLabDevices() {
        return hLabDevicesRepository.findAll();
    }

    // method to find and return a list of all device of type microcontroller
    public List<HLabDevices> listAllDeviceByMicrocontroller() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("microcontroller");
    }

    // method to find and return a list of all device of type actuator
    public List<HLabDevices> listAllDeviceByActuator() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("actuator");
    }

    // method to find and return a list of all device of type sensor
    public List<HLabDevices> listAllDeviceBySensor() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("sensor");
    }

    // method to find and return a list of all device of type other
    public List<HLabDevices> listAllDeviceByOther() {
        return hLabDevicesRepository.findByDeviceTypeIgnoreCase("other");
    }

    // method to add a new device to the system
    public void addNewDevice(HLabDevices device) {
        LocalDateTime dateTime = LocalDateTime.now();
        device.setDateModified(dateTime);
        device.setNumberTaken(device.getNumberTaken());
        hLabDevicesRepository.save(device);
    }

    // method to find and return a device by its id
    public Optional<HLabDevices> getDeviceById(Long id) {
        return hLabDevicesRepository.findById(id);
    }

    // method to update device details
    public void updateDevice(HLabDevices labDevice) {
        LocalDateTime dateTime = LocalDateTime.now();
        labDevice.setDateModified(dateTime);
        labDevice.setNumberTaken(labDevice.getNumberTaken());
        hLabDevicesRepository.save(labDevice);
    }

    public Long getDeviceFromHLabTable(HLabDevices labDevice) {
        return labDevice.getDeviceID();
    }

    // method delete a device by its id
    public void deleteDevice(Long id) {
        hLabDevicesRepository.deleteById(id);
    }
}
