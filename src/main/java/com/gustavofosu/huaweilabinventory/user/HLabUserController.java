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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class HLabUserController {

    private final DeviceService deviceService;
    private final RequestService requestService;
    private final DeviceTakenService deviceTakenService;

    public HLabUserController(DeviceService deviceService, RequestService requestService, DeviceTakenService deviceTakenService) {
        this.deviceService = deviceService;
        this.requestService = requestService;
        this.deviceTakenService = deviceTakenService;
    }

    @GetMapping("/dashboard")
    public String showUserDashboard(Model model) {
        List<HLabDevices> listAllDevices = deviceService.listAllLabDevices();
        List<HLabDevices> listDeviceByMicroController = deviceService.listAllDeviceByMicrocontroller();
        List<HLabDevices> listDeviceByActuator= deviceService.listAllDeviceByActuator();
        List<HLabDevices> listDeviceBySensor = deviceService.listAllDeviceBySensor();
        List<HLabDevices> listDeviceByOther = deviceService.listAllDeviceByOther();

        List<HLabRequests> listDeviceRequests = requestService.listOfRequestsByCurrentUser();
        List<HLabRequests> requestsPendingByUser = requestService.listAllRequestsPendingByCurrentUser();
        List<HLabRequests> requestsApprovedByUser = requestService.listAllRequestsApprovedByCurrentUser();
        List<HLabRequests> requestsDeclinedByUser = requestService.listAllRequestsDeclinedByCurrentUser();

        List<HLabRequests> requestedDevicePending = requestService.listAllRequestsPending();
        List<HLabRequests> requestedDeviceApproved = requestService.listAllRequestsApproved();
        List<HLabRequests> requestedDeviceDeclined = requestService.listAllRequestsDeclined();

        List<HLabDeviceTaken> deviceTakenList = deviceTakenService.listOfDeviceTakenByUser();

        model.addAttribute("listAllDevices", listAllDevices);
        model.addAttribute("listDeviceMicrocontroller", listDeviceByMicroController);
        model.addAttribute("listDeviceActuator", listDeviceByActuator);
        model.addAttribute("listDeviceSensor", listDeviceBySensor);
        model.addAttribute("listDeviceByOther", listDeviceByOther);

        model.addAttribute("listDeviceRequests", listDeviceRequests);
        model.addAttribute("requestsPendingByCurrentUser", requestsPendingByUser);
        model.addAttribute("requestsApprovedByCurrentUser", requestsApprovedByUser);
        model.addAttribute("requestsDeclinedByCurrentUser", requestsDeclinedByUser);

        model.addAttribute("requestsPending", requestedDevicePending);
        model.addAttribute("requestsApproved", requestedDeviceApproved);
        model.addAttribute("requestsDeclined", requestedDeviceDeclined);

        model.addAttribute("listDeviceTaken", deviceTakenList);

        log.info("user called");
        return "user";
    }

    @PostMapping("/send_request/{id}")
    public String processSendRequest(HLabRequests hLabRequests, @PathVariable(value = "id") Long id) {
        requestService.saveRequest(hLabRequests, id);

        log.info("Send request processed");
        return "redirect:/user/dashboard";
    }

    @GetMapping("/delete_request/{id}")
    public String processDeleteRequest(@PathVariable(value = "id") Long id) {
        requestService.deleteRequest(id);

        log.info("Delete request processed");
        return "redirect:/user/dashboard";
    }


}
