package com.gustavofosu.huaweilabinventory.user;

import com.gustavofosu.huaweilabinventory.device.DeviceService;
import com.gustavofosu.huaweilabinventory.device.HLabDevices;
import com.gustavofosu.huaweilabinventory.device_taken.DeviceTakenService;
import com.gustavofosu.huaweilabinventory.device_taken.HLabDeviceTaken;
import com.gustavofosu.huaweilabinventory.request.HLabRequests;
import com.gustavofosu.huaweilabinventory.request.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/admin")
public class HLabAdminController {

    private final DeviceService deviceService;
    private final RequestService requestService;
    private final DeviceTakenService deviceTakenService;

    public HLabAdminController(DeviceService deviceService, RequestService requestService, DeviceTakenService deviceTakenService) {
        this.deviceService = deviceService;
        this.requestService = requestService;
        this.deviceTakenService = deviceTakenService;
    }

    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        List<HLabDevices> labDevicesList = deviceService.listAllLabDevices();
        List<HLabDevices> listDeviceByMicroController = deviceService.listAllDeviceByMicrocontroller();
        List<HLabDevices> listDeviceByActuator= deviceService.listAllDeviceByActuator();
        List<HLabDevices> listDeviceBySensor = deviceService.listAllDeviceBySensor();
        List<HLabDevices> listDeviceByOther = deviceService.listAllDeviceByOther();
        List<HLabRequests> listOfRequestedDevices = requestService.listAllRequestedDevices();
        List<HLabRequests> requestedDevicePending = requestService.listAllRequestsPending();
        List<HLabDeviceTaken> listOfAllDevicesTaken = deviceTakenService.listOfAllDeviceTaken();

        model.addAttribute("listAllDevices", labDevicesList);
        model.addAttribute("listDeviceMicrocontroller", listDeviceByMicroController);
        model.addAttribute("listDeviceActuator", listDeviceByActuator);
        model.addAttribute("listDeviceSensor", listDeviceBySensor);
        model.addAttribute("listDeviceByOther", listDeviceByOther);
        model.addAttribute("listOfRequestedDevices", listOfRequestedDevices);
        model.addAttribute("requestsPending", requestedDevicePending);
        model.addAttribute("allDeviceTakenList", listOfAllDevicesTaken);



        log.info("admin called");
        return "admin";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Optional<HLabDevices> getOne(Long id) {
        return deviceService.getDeviceById(id);
    }

    @PostMapping("/add_device")
    public String showAddDeviceModal(HLabDevices device) {
        deviceService.addNewDevice(device);

        log.info("Add device modal called");
        return "redirect:/admin/dashboard";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String processEditUser(HLabDevices hLabDevice) {
        deviceService.updateDevice(hLabDevice);

        log.info("Edit Modal called");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String processDeleteDevice(@PathVariable(value = "id") Long id) {
        deviceService.deleteDevice(id);

        log.info("Delete Device called");
        return "redirect:/admin/dashboard";
    }

    @RequestMapping(value = "/accept_request/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String acceptRequestProcess(HLabDeviceTaken labDeviceTaken, @PathVariable(value = "id") Long id) {
        deviceTakenService.saveDeviceTaken(labDeviceTaken, id);
        requestService.acceptRequest(id);

        log.info("Accept request processed");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/decline_request/{id}")
    public String declineRequestProcess(@PathVariable(value = "id") Long id) {
        requestService.declineRequest(id);

        log.info("Decline request processed");
        return "redirect:/admin/dashboard";
    }
}
