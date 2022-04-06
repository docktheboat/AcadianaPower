package com.AcadianaPower.Registration;

import com.AcadianaPower.User.AcadianaUser;
import com.AcadianaPower.User.AcadianaUserService;
import com.AcadianaPower.User.UserRoles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final AcadianaUserService userService;

    public String register(RegistrationRequest request) {
        return userService.signUp(
                new AcadianaUser(
                        request.getEmail(),
                        request.getPassword(),
                        UserRoles.CUSTOMER
                )
        );
    }

}