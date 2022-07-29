package com.gustavofosu.huaweilabinventory.request;

import com.gustavofosu.huaweilabinventory.user.HLabUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HLabRequestsRepository extends JpaRepository<HLabRequests, Long> {

    List<HLabRequests> findAllByUser(HLabUsers user);

    List<HLabRequests> findAllByRequestStatus(String requestStatus);

    List<HLabRequests> findAllByUserAndRequestStatus(HLabUsers user, String requestStatus);
}
