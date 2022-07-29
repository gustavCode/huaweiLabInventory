package com.gustavofosu.huaweilabinventory.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HLabUsersRepository extends JpaRepository<HLabUsers, Long> {

    HLabUsers findByUsername(String username);
}
