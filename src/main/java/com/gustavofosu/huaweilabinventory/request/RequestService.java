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

    // private fields
    private final HLabRequestsRepository hLabRequestsRepository;
    private final HLabUsersRepository hLabUsersRepository;
    private final DeviceService deviceService;

    // constructor injection with all fields as parameters
    public RequestService(HLabRequestsRepository hLabRequestsRepository,
                          HLabUsersRepository hLabUsersRepository,
                          DeviceService deviceService) {
        this.hLabRequestsRepository = hLabRequestsRepository;
        this.hLabUsersRepository = hLabUsersRepository;
        this.deviceService = deviceService;
    }

    // method to save request made by a user
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

    // method to find and return a list of all requests made by logged-in user
    public List<HLabRequests> listOfRequestsByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUser(labUser);
    }

    // method to find and return a list of all requested devices
    public List<HLabRequests> listAllRequestedDevices() {
        return hLabRequestsRepository.findAll();
    }

    // method to find and return a list of all requests that are pending
    public List<HLabRequests> listAllRequestsPending() {
        return hLabRequestsRepository.findAllByRequestStatus("Pending Approval");
    }

    // method to find and return a list of all request pending by current user
    public List<HLabRequests> listAllRequestsPendingByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUserAndRequestStatus(labUser, "Pending Approval");
    }

    // method to find and return a list of requests approved by the current user
    public List<HLabRequests> listAllRequestsApprovedByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUserAndRequestStatus(labUser, "Request Approved");
    }

    // method to find and return a list of requests declined by the current user
    public List<HLabRequests> listAllRequestsDeclinedByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nameOfLoggedInUser = authentication.getName();
        HLabUsers labUser = hLabUsersRepository.findByUsername(nameOfLoggedInUser);

        return hLabRequestsRepository.findAllByUserAndRequestStatus(labUser, "Request Declined");
    }

    // method to find and return a list of all requests approved
    public List<HLabRequests> listAllRequestsApproved() {
        return hLabRequestsRepository.findAllByRequestStatus("Request Approved");
    }

    // method to find and return a list of all requests declined
    public List<HLabRequests> listAllRequestsDeclined() {
        return hLabRequestsRepository.findAllByRequestStatus("Request Declined");
    }

    // method to delete a request by their id
    public void deleteRequest(Long id) {
        hLabRequestsRepository.deleteById(id);
    }

    // method to accept a request by their id
    public void acceptRequest(Long id) {
//        HLabRequests requests = hLabRequestsRepository.findById(id).get();
//        requests.setRequestStatus("Request Approved");
//        hLabRequestsRepository.save(requests);
        deleteRequest(id);
    }

    // method to decline a request by their id
    public void declineRequest(Long id) {
        HLabRequests requests = hLabRequestsRepository.findById(id).get();
        requests.setRequestStatus("Request Declined");
        hLabRequestsRepository.save(requests);
    }
}
