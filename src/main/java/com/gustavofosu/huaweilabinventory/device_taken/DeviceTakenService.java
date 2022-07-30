package com.gustavofosu.huaweilabinventory.device_taken;

import com.gustavofosu.huaweilabinventory.device.DeviceService;
import com.gustavofosu.huaweilabinventory.device.HLabDevices;
import com.gustavofosu.huaweilabinventory.device.HLabDevicesRepository;
import com.gustavofosu.huaweilabinventory.request.HLabRequests;
import com.gustavofosu.huaweilabinventory.request.HLabRequestsRepository;
import com.gustavofosu.huaweilabinventory.user.HLabUsers;
import com.gustavofosu.huaweilabinventory.user.HLabUsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceTakenService {

    private final HLabDeviceTakenRepository deviceTakenRepository;
    private final HLabUsersRepository hLabUsersRepository;
    private final DeviceService deviceService;
    private final HLabRequestsRepository hLabRequestsRepository;
    private final HLabDevicesRepository hLabDevicesRepository;
    private final HLabDeviceTakenRepository hLabDeviceTakenRepository;

    public DeviceTakenService(HLabDeviceTakenRepository deviceTakenRepository, HLabUsersRepository hLabUsersRepository,
                              DeviceService deviceService, HLabRequestsRepository hLabRequestsRepository,
                              HLabDevicesRepository hLabDevicesRepository, HLabDeviceTakenRepository hLabDeviceTakenRepository) {
        this.deviceTakenRepository = deviceTakenRepository;
        this.hLabUsersRepository = hLabUsersRepository;
        this.deviceService = deviceService;
        this.hLabRequestsRepository = hLabRequestsRepository;
        this.hLabDevicesRepository = hLabDevicesRepository;
        this.hLabDeviceTakenRepository = hLabDeviceTakenRepository;
    }

    public List<HLabDeviceTaken> listOfAllDeviceTaken() {
        return deviceTakenRepository.findAll();
    }

    public List<HLabDeviceTaken> listOfDeviceTakenByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(loggedInUser);

        return deviceTakenRepository.findAllByUser(labUser);
    }

    public void saveDeviceTaken(HLabDeviceTaken deviceTaken, Long id) {
        HLabRequests request = hLabRequestsRepository.findById(id).get();
        deviceTaken.setDeviceTakenQuantity(request.getRequestQuantity());

        LocalDate dateTaken = LocalDate.now();
        deviceTaken.setDeviceTaken_DateTaken(dateTaken);
        deviceTaken.setDeviceTaken_DateReturned(null);

        String userName = request.getUser().getUsername();
        HLabUsers labUser = hLabUsersRepository.findByUsername(userName);
        deviceTaken.setUser(labUser);

        Long deviceId = request.getDevice().getDeviceID();
        HLabDevices labDevice = deviceService.getDeviceById(deviceId).get();
        labDevice.setNumberTaken(labDevice.getNumberTaken() + request.getRequestQuantity());
        labDevice.setDeviceQuantity(labDevice.getDeviceQuantity() - request.getRequestQuantity());
        labDevice.setDateModified(LocalDateTime.now());
        hLabDevicesRepository.save(labDevice);
        deviceTaken.setDevice(labDevice);

        hLabDeviceTakenRepository.save(deviceTaken);
    }
}
