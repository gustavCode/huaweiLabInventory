package com.gustavofosu.huaweilabinventory.request;

import com.gustavofosu.huaweilabinventory.device.DeviceService;
import com.gustavofosu.huaweilabinventory.device.HLabDevices;
import com.gustavofosu.huaweilabinventory.user.HLabUsers;
import com.gustavofosu.huaweilabinventory.user.HLabUsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class RequestService {

    private final HLabRequestsRepository hLabRequestsRepository;
    private final HLabUsersRepository hLabUsersRepository;
    private final DeviceService deviceService;

    public RequestService(HLabRequestsRepository hLabRequestsRepository,
                          HLabUsersRepository hLabUsersRepository,
                          DeviceService deviceService) {
        this.hLabRequestsRepository = hLabRequestsRepository;
        this.hLabUsersRepository = hLabUsersRepository;
        this.deviceService = deviceService;
    }

    public void saveRequest(HLabRequests labRequest, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);
        HLabDevices labDevice = deviceService.getDeviceById(id).get();

        labRequest.setUser(labUser);
        labRequest.setDevice(labDevice);
        labRequest.setRequestStatus("Pending Approval");

        hLabRequestsRepository.save(labRequest);
    }

    public List<HLabRequests> listOfRequestsByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUser(labUser);
    }

    public List<HLabRequests> listAllRequestedDevices() {
        return hLabRequestsRepository.findAll();
    }

    public List<HLabRequests> listAllRequestsPending() {
        return hLabRequestsRepository.findAllByRequestStatus("Pending Approval");
    }

    public List<HLabRequests> listAllRequestsPendingByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUserAndRequestStatus(labUser, "Pending Approval");
    }

    public List<HLabRequests> listAllRequestsApprovedByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUserAndRequestStatus(labUser, "Request Approved");
    }

    public List<HLabRequests> listAllRequestsDeclinedByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUserAndRequestStatus(labUser, "Request Declined");
    }

    public List<HLabRequests> listAllRequestsApproved() {
        return hLabRequestsRepository.findAllByRequestStatus("Request Approved");
    }

    public List<HLabRequests> listAllRequestsDeclined() {
        return hLabRequestsRepository.findAllByRequestStatus("Request Declined");
    }

    public void deleteRequest(Long id) {
        hLabRequestsRepository.deleteById(id);
    }

    public void acceptRequest(Long id) {
//        HLabRequests requests = hLabRequestsRepository.findById(id).get();
//        requests.setRequestStatus("Request Approved");
//        hLabRequestsRepository.save(requests);
        deleteRequest(id);
    }


    public void declineRequest(Long id) {
        HLabRequests requests = hLabRequestsRepository.findById(id).get();
        requests.setRequestStatus("Request Declined");
        hLabRequestsRepository.save(requests);
    }
}
