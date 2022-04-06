package com.AcadianaPower.User;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AcadianaUserService implements UserDetailsService {


    private final static String USER_NOT_FOUND = "No User With That Email Exists.";
    private final AcadianaUserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    public String signUp (AcadianaUser user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email is in use.");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setLocked(false);
        userRepository.save(user);

        return "Account created";
    }

    public AcadianaUser currentUser(){
        AcadianaUser user = null;
        Object principal = SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            user = (AcadianaUser) principal;
        }
        return user;
    }

}
