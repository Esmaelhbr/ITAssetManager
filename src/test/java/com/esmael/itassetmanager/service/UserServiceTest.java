package com.esmael.itassetmanager.service;


import com.esmael.itassetmanager.entities.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // run tests in order
class UserServiceTest {

    @Autowired
    private UserService userService;

    private static Long testUserId;

    @Test
    @Order(1)
    void contextLoads() {
        assertNotNull(userService, "UserService should be autowired and not null");
    }

    @Test
    @Order(2)
    void createUser_shouldSaveUser() {
        User user = new User("John", "Doe", "john@example.com", "IT");

        User savedUser = userService.createUser(user);
        assertNotNull(savedUser.getId(), "Saved user should have an ID");
        assertEquals("John", savedUser.getFirstName());
        assertEquals("john@example.com", savedUser.getEmail());

        testUserId = savedUser.getId(); // save ID for other tests
    }

    @Test
    @Order(3)
    void getUserByEmail_shouldReturnUser() {
        User user = userService.getUserByEmail("john@example.com");
        assertNotNull(user, "User should be found by email");
        assertEquals("Doe", user.getLastName());
    }

    @Test
    @Order(4)
    void getUserById_shouldReturnUser() {
        User user = userService.getUserById(testUserId);
        assertNotNull(user, "User should be found by ID");
        assertEquals("John", user.getFirstName());
    }

    @Test
    @Order(5)
    void getAllUsers_shouldReturnList() {
        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty(), "User list should not be empty");
    }

    @Test
    @Order(6)
    void updateUser_shouldChangeFields() {
        User updated = new User("Jane", "Smith", "jane@example.com", "HR");
        User result = userService.updateUser(testUserId, updated);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("HR", result.getDepartment());
        assertEquals(testUserId, result.getId());
    }

    @Test
    @Order(7)
    void deleteUser_shouldRemoveUser() {
        userService.deleteUser(testUserId);

        User deleted = userService.getUserById(testUserId);
        assertNull(deleted, "User should be deleted");
    }
}
