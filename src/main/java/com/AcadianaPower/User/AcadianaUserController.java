package com.AcadianaPower.User;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AcadianaUserController {

    private final AcadianaUserService userService;

    @GetMapping("/user")
    public ResponseEntity<String> currentUser(){
        return new ResponseEntity<>(userService.currentUser().getEmail(), HttpStatus.OK);
    }
}
