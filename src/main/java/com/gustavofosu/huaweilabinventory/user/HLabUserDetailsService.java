package com.gustavofosu.huaweilabinventory.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HLabUserDetailsService implements UserDetailsService {

    // private fields
    private final HLabUsersRepository hLabUsersRepository;

    // constructor injection with HLabRepository as parameter
    public HLabUserDetailsService(HLabUsersRepository hLabUsersRepository) {
        this.hLabUsersRepository = hLabUsersRepository;
    }

    // method to find and return user details by the username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HLabUsers hLabUser = hLabUsersRepository.findByUsername(username);

        if (hLabUser == null) {
            throw new UsernameNotFoundException(String.format("%s not found", username));
        }

        return new LabUserDetails(hLabUser);
    }
}
