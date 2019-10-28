package com.stackroute.usermanagement.controller;


import com.stackroute.usermanagement.exceptions.*;
import com.stackroute.usermanagement.model.AuthenticationResponse;
import com.stackroute.usermanagement.model.DTOUser;
import com.stackroute.usermanagement.model.User;
import com.stackroute.usermanagement.service.RabbitMQSender;
import com.stackroute.usermanagement.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;
    private RabbitMQSender rabbitMQSender;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService, RabbitMQSender rabbitMQSender) {
        this.userService = userService;
        this.rabbitMQSender = rabbitMQSender;
    }



    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UserAlreadyExistsExceptions, InternalServerErrorException, NullValueFieldException {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));//field checking
        DTOUser dtouser=new DTOUser();
        dtouser.setUsername(user.getUsername());
        dtouser.setRole(user.getRole());
        dtouser.setPhoneNumber(user.getPhoneNumber());
        dtouser.setNationality(user.getNationality());
        dtouser.setLastName(user.getLastName());
        dtouser.setGender(user.getGender());
        dtouser.setFirstName(user.getFirstName());
        dtouser.setEmail(user.getEmail());
        dtouser.setDob(user.getDob());
        dtouser.setAddressLine1(user.getAddressLine1());
        dtouser.setAddressLine2(user.getAddressLine2());
        dtouser.setAddressLine3(user.getAddressLine3());
        rabbitMQSender.sendRegistry(dtouser);
        return new ResponseEntity<User> (userService.saveUser(user), HttpStatus.CREATED);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InternalServerErrorException, InvalidCredentialException {
        String jwtToken = "";
        userService.findByUsername(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", new ArrayList<>());

        jwtToken = Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, "secretkey").compact();


//         = Jwts.builder().setSubject(user.getUsername()).setClaims(claims).setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS512, "secretkey").compact();
        return new ResponseEntity<AuthenticationResponse> (new AuthenticationResponse(jwtToken), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<?> delete(@PathVariable String username) throws InternalServerErrorException, InvalidCredentialException, UserDoesNotExistException {
        userService.deleteUser(username);
        return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse("Deleted Successfully"), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody User user) throws InternalServerErrorException, InvalidCredentialException, UserDoesNotExistException {
        userService.updateUser(user);
        return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse("Deleted Successfully"), HttpStatus.OK);
    }
}
