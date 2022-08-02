package com.gustavofosu.huaweilabinventory.device_taken;

import com.gustavofosu.huaweilabinventory.user.HLabUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HLabDeviceTakenRepository extends JpaRepository<HLabDeviceTaken, Long> {

    // method to find and return all users that have taken a device
    List<HLabDeviceTaken> findAllByUser(HLabUsers user);
}
