/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package HotelBookingSystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alanbokchoy
 */
public class RegisterValidateTest {

    private InMemoryDBManager dbManager;
    private RegisterValidate registerValidate;
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dbManager = new InMemoryDBManager();
        registerValidate = new RegisterValidate(dbManager);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterUser_EmptyFields() {
        assertFalse(registerValidate.registerUser("", "password", "Name", "email@example.com", "1234567890"));
        assertFalse(registerValidate.registerUser("username", "", "Name", "email@example.com", "1234567890"));
        assertFalse(registerValidate.registerUser("username", "password", "", "email@example.com", "1234567890"));
        assertFalse(registerValidate.registerUser("username", "password", "Name", "", "1234567890"));
        assertFalse(registerValidate.registerUser("username", "password", "Name", "email@example.com", ""));
    }

    @Test
    public void testRegisterUser_InvalidEmail() {
        assertFalse(registerValidate.registerUser("username", "password", "Name", "invalidEmail", "1234567890"));
    }

    @Test
    public void testRegisterUser_InvalidPhone() {
        assertFalse(registerValidate.registerUser("username", "password", "Name", "email@example.com", "12345abc"));
    }

    @Test
    public void testRegisterUser_SuccessfulRegistration() {
        boolean result = registerValidate.registerUser("username", "password", "Name", "email@example.com", "1234567890");
        assertTrue(result);
        assertTrue(dbManager.getUsers().size() == 1);
        InMemoryDBManager.User registeredUser = dbManager.getUsers().get(0);
        assertTrue(registeredUser.username.equals("username"));
        assertTrue(registeredUser.password.equals("password"));
        assertTrue(registeredUser.name.equals("Name"));
        assertTrue(registeredUser.email.equals("email@example.com"));
        assertTrue(registeredUser.phone.equals("1234567890"));
    }
}
