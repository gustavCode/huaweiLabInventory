package com.gustavofosu.huaweilabinventory;

import com.gustavofosu.huaweilabinventory.user.LabUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {

        LabUserDetails userDetails = (LabUserDetails) authentication.getPrincipal();
        String redirectUrl = request.getContextPath();

        if (userDetails.hasRole("ADMIN")) {
            redirectUrl += "/admin/dashboard";
        } else if (userDetails.hasRole("USER")) {
            redirectUrl += "/user/dashboard";
        }

        response.sendRedirect(redirectUrl);
    }
}
