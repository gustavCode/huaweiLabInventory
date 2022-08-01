package com.gustavofosu.huaweilabinventory.request;

import com.gustavofosu.huaweilabinventory.user.HLabUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HLabRequestsRepository extends JpaRepository<HLabRequests, Long> {

    // method to find and return a list of device requests
    // by the user who requested it
    List<HLabRequests> findAllByUser(HLabUsers user);

    // method to find and return a list of device requests by their status
    List<HLabRequests> findAllByRequestStatus(String requestStatus);

    // method to find and return a list of device requests
    // by their user who requested it and the status of the request
    List<HLabRequests> findAllByUserAndRequestStatus(HLabUsers user, String requestStatus);
}
