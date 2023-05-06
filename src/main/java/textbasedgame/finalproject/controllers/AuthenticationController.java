package textbasedgame.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import textbasedgame.finalproject.request.LoginRequest;
import textbasedgame.finalproject.responses.JwtResponse;
import textbasedgame.finalproject.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.jwtUtils.generateToken(authentication);

        User user = (User) authentication.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse(user.getUsername(), jwt);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser() {

        return null;

    }

}
