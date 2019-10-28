package com.stackroute.usermanagement.service;

import com.stackroute.usermanagement.exceptions.*;
import com.stackroute.usermanagement.model.User;
import com.stackroute.usermanagement.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    User user;

    //Create a mock for UserRepository
    @Mock
    UserRepository userRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        user = new User(5L,"Shivani", BCrypt.hashpw("shivani", BCrypt.gensalt()),"Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
    }

    @Test
    public void saveUserTestSuccess() throws UserAlreadyExistsExceptions, NullValueFieldException, InternalServerErrorException {

        when(userRepository.save(any())).thenReturn(user);
        User savedUser = userService.saveUser(user);
        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findByUsername("Shivani");
    }

    @Test(expected = NullValueFieldException.class)
    public void saveUserTestFailure() throws UserAlreadyExistsExceptions, NullValueFieldException, InternalServerErrorException {
        User user1 = new User();
        user1.setUsername("shivani");
        User savedUser = userService.saveUser(user1);
    }

    @Test(expected = UserAlreadyExistsExceptions.class)
    public void saveUserTestFailure1() throws UserAlreadyExistsExceptions, NullValueFieldException, InternalServerErrorException {
       when(userRepository.findByUsername(any())).thenReturn(user);
        User savedUser = userService.saveUser(user);
    }
    @Test
    public void getUserByUsernameTestSuccess() throws InvalidCredentialException, InternalServerErrorException {
        User user1 = new User(5L,"Shivani", "shivani","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        when(userRepository.findByUsername(any())).thenReturn(user);
        User getUser = userService.findByUsername(user1);
        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).findByUsername("Shivani");
    }
    @Test(expected = InvalidCredentialException.class)
    public void getUserByUsernameTestFailure() throws InvalidCredentialException, InternalServerErrorException {
        User user1 = new User(5L,"Shivani", "shivanisajjan","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        when(userRepository.findByUsername(any())).thenReturn(user);
        User getUser = userService.findByUsername(user1);
    }
    @Test(expected = InvalidCredentialException.class)
    public void getUserByUsernameTestFailure1() throws InvalidCredentialException, InternalServerErrorException {
        when(userRepository.findByUsername(any())).thenReturn(null);
        User getUser = userService.findByUsername(user);
    }
    @Test
    public void deleteUserTestSuccess() throws UserDoesNotExistException, InternalServerErrorException {
        when(userRepository.findByUsername(any())).thenReturn(user);
        User getUser = userService.deleteUser("Shivani");
        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).findByUsername("Shivani");
        verify(userRepository,times(1)).delete(user);
    }
    @Test(expected = UserDoesNotExistException.class)
    public void deleteUserTestFailure() throws UserDoesNotExistException, InternalServerErrorException {
        when(userRepository.findByUsername(any())).thenReturn(null);
        User getUser = userService.deleteUser("Shivani");
    }
    @Test
    public void updateUserTestSuccess() throws UserDoesNotExistException, InternalServerErrorException {
        when(userRepository.findByUsername(any())).thenReturn(user);
        User getUser = userService.updateUser(user);
        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).findByUsername("Shivani");
        verify(userRepository,times(1)).save(user);
    }
    @Test(expected = UserDoesNotExistException.class)
    public void updateUserTestFailure() throws UserDoesNotExistException, InternalServerErrorException {
        when(userRepository.findByUsername(any())).thenReturn(null);
        User getUser = userService.updateUser(user);
    }
}
