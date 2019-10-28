package com.stackroute.usermanagement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.usermanagement.exceptions.*;
import com.stackroute.usermanagement.model.User;
import com.stackroute.usermanagement.service.RabbitMQSender;
import com.stackroute.usermanagement.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private User user;
    @MockBean
    private UserService userService;
    @MockBean
    private RabbitMQSender rabbitMQSender;
    @InjectMocks
    private UserController userController;

    private List<User> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new GlobalExceptionHandler()).build();
        user = new User(5L,"Shivani",BCrypt.hashpw("shivani", BCrypt.gensalt()),"Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        list = new ArrayList();
        list.add(user);
    }

    @Test
    public void userRegistry() throws Exception {
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
      @Test
     public void userRegistryFailure() throws Exception {
        when(userService.saveUser(any())).thenThrow(UserAlreadyExistsExceptions.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void userRegistryFailure1() throws Exception {
        when(userService.saveUser(any())).thenThrow(NullValueFieldException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void userLogin() throws Exception {
        when(userService.findByUsername(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void userLoginFailure() throws Exception {
        when(userService.findByUsername(any())).thenThrow(InternalServerErrorException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void userLoginFailure1() throws Exception {
        when(userService.findByUsername(any())).thenThrow(InvalidCredentialException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateUser() throws Exception {
        when(userService.updateUser( any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/update")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void updateUserFailure() throws Exception {
        when(userService.updateUser(any())).thenThrow(UserDoesNotExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/update")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteUser() throws Exception {
        when(userService.deleteUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/delete/shivani")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteUserFailure() throws Exception {
        when(userService.deleteUser(any())).thenThrow(UserDoesNotExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/delete/shivani")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }










}
