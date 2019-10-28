package com.stackroute.usermanagement.repository;

import com.stackroute.usermanagement.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    User user;

    @Before
    public void setUp()
    {
        user = new User();
        user.setId(10L);
        user.setUsername("Manikarnika");
    }

    @After
    public void tearDown(){

        userRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
        System.out.println("hii");
     userRepository.save(user);
        System.out.println("hello");
     User fetchUser = userRepository.findById(user.getId()).get();
     Assert.assertEquals(java.util.Optional.of(10),java.util.Optional.of(fetchUser.getId()));
    }

    @Test
    public void testSaveUserFailure(){
        User u = new User(5L,"Shivani","shivani123","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(user);
        Assert.assertNotSame(u,user);
    }

    @Test
    public void testGetAllUser(){
        User u = new User(5L,"Shivani","shivani123","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        User u1 = new User(6L,"Poorvi","poorvi123","Poorvi","bhat","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(u);
        userRepository.save(u1);
        List<User> list = userRepository.findAll();
        Assert.assertEquals("Shivani",list.get(0).getUsername());
    }
    @Test
    public void testDeleteById(){
        User u = new User(5L,"Shivani","shivani123","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(u);
        userRepository.deleteById(5L);
        Boolean b = userRepository.findAll().isEmpty();
        Assert.assertEquals(true,b);
    }
    @Test
    public void testExistById(){
        User u = new User(5L,"Shivani","shivani123","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        User u1 = new User(6L,"Poorvi","poorvi123","Poorvi","bhat","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(u);
        userRepository.save(u1);
        Boolean b= userRepository.existsById(5L);
        Assert.assertEquals(true,b);
    }
    @Test
    public void testExistByIdFailure(){
        User u = new User(5L,"Shivani","shivani123","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        User u1 = new User(6L,"Poorvi","poorvi123","Poorvi","bhat","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(u);
        userRepository.save(u1);
        Boolean b= userRepository.existsById(6L);
        Assert.assertNotEquals(false,b);
    }
    @Test
    public void testDeleteByIdFailure(){
        User u = new User(5L,"Shivani","shivani","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(u);
        userRepository.deleteById(5L);
        Boolean b = userRepository.findAll().isEmpty();
        Assert.assertNotEquals(false,b);
    }
    @Test
    public void testFindByUsername(){
        User u = new User(5L,"Shivani","shivani123","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        User u1 = new User(6L,"Poorvi","poorvi123","Poorvi","bhat","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(u1);
        userRepository.save(u);
        User user=userRepository.findByUsername("Shivani");
        Assert.assertEquals(u,user);
    }
    @Test
    public void testGetByTitleFailure(){
        User u = new User(5L,"Shivani","shivani123","Shivani","sajjan","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        User u1 = new User(6L,"Poorvi","poorvi123","Poorvi","bhat","26-08-1998","reader","shivanisajjan@gmail.com",9145533692L,"female","Indian","vallabh nagar","wanaparthy","Mahabubnagar");
        userRepository.save(u1);
        userRepository.save(u);
        User user=userRepository.findByUsername("Shivani1");
        Assert.assertNotEquals(u,user);
    }
}
