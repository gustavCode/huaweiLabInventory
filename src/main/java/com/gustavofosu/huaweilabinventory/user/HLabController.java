package com.gustavofosu.huaweilabinventory.user;

import com.gustavofosu.huaweilabinventory.device.DeviceService;
import com.gustavofosu.huaweilabinventory.device.HLabDevices;
import com.gustavofosu.huaweilabinventory.role.HLabRole;
import com.gustavofosu.huaweilabinventory.role.HLabRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class HLabController {

    private final HLabUsersRepository hLabUsersRepository;
    private final HLabRoleRepository hLabRoleRepository;
    private final DeviceService deviceService;

    public HLabController(HLabUsersRepository hLabUsersRepository, HLabRoleRepository hLabRoleRepository, DeviceService deviceService) {
        this.hLabUsersRepository = hLabUsersRepository;
        this.hLabRoleRepository = hLabRoleRepository;
        this.deviceService = deviceService;
    }

    @GetMapping("")
    public String showHomePage(Model model) {
        List<HLabDevices> listAllDevices = deviceService.listAllLabDevices();
        model.addAttribute("listAllDevices", listAllDevices);
        log.info("index called");
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        log.info("login called");
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        List<HLabRole> userRoleList = hLabRoleRepository.findAll();

        model.addAttribute("labUser", new HLabUsers());
        model.addAttribute("roleList", userRoleList);

        log.info("registration called");
        return "register";
    }

    @PostMapping("/registration_process")
    public String registerUserProcess(HLabUsers hLabUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(hLabUser.getPassword());
        hLabUser.setPassword(encodedPassword);

        hLabUsersRepository.save(hLabUser);

        log.info("registration_process called");
        return "redirect:/login";
    }
}
