package com.dany.cloud.security.service;

import com.dany.cloud.security.util.JWTUtil;
import com.dany.cloud.security.model.LoginRequest;
import com.dany.cloud.security.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserServices {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServices userDetailsServices;

    public ResponseEntity<LoginResponse> authenticate(LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new Exception("Invalid username or/and password", e);
        }

        final UserDetails userDetails = userDetailsServices.loadUserByUsername(loginRequest.getUserName());
        final String token = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);

    }
}
