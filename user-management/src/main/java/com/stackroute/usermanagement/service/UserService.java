package com.stackroute.usermanagement.service;

import com.stackroute.usermanagement.exceptions.*;
import com.stackroute.usermanagement.model.User;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsExceptions, NullValueFieldException,InternalServerErrorException;

    User findByUsername(User user) throws InternalServerErrorException, InvalidCredentialException;

    User deleteUser(String username) throws UserDoesNotExistException,InternalServerErrorException;

    User updateUser(User user) throws UserDoesNotExistException,InternalServerErrorException;
}
