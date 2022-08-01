package com.gustavofosu.huaweilabinventory.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HLabRoleRepository extends JpaRepository<HLabRole, Long> {
}
