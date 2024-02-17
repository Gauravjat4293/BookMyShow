package com.bookmyshow.controller;

import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.model.CustomerUserDetails;
import com.bookmyshow.model.JwtAuthRequest;
import com.bookmyshow.model.JwtResponse;
import com.bookmyshow.security.JwtHelper;
import com.bookmyshow.serviceimp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/login")
public class AuthController {

    @Autowired
    private JwtHelper jwtTokenHelper;
@Autowired
 private CustomerUserDetails customerUserDetails;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping()
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

        authenticate(request.getUsername(),request.getPassword());

        UserDetails userDetails = customerUserDetails.loadUserByUsername(request.getUsername());
        String generatedToken = jwtTokenHelper.generateToken(userDetails);


        JwtResponse jwtAuthResponse = new JwtResponse();

        jwtAuthResponse.setToken(generatedToken);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);


    }

    private void authenticate(String userName, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException ex){

            throw new ResourceNotFoundException("Invalid username and password");
        }

    }

}

