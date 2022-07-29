package com.gustavofosu.huaweilabinventory.device_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HLabDeviceHistoryRepository extends JpaRepository<HLabDeviceHistory, Long> {
}
