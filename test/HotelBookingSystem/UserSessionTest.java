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
public class UserSessionTest {
    
    public UserSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        UserSession.setUsername(null);
        UserSession.setGuestId(0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expected = null;
        String actual = UserSession.getUsername();
        assertEquals(expected, actual);

        String testUsername = "testUser";
        UserSession.setUsername(testUsername);
        actual = UserSession.getUsername();
        assertEquals(testUsername, actual);
    }

    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String testUsername = "newUser";
        UserSession.setUsername(testUsername);
        String result = UserSession.getUsername();
        assertEquals(testUsername, result);
    }

    @Test
    public void testGetGuestId() {
        System.out.println("getGuestId");
        int expected = 0; 
        int actual = UserSession.getGuestId();
        assertEquals(expected, actual);

        int testGuestId = 42;
        UserSession.setGuestId(testGuestId);
        actual = UserSession.getGuestId();
        assertEquals(testGuestId, actual);
    }

    @Test
    public void testSetGuestId() {
        System.out.println("setGuestId");
        int testGuestId = 99;
        UserSession.setGuestId(testGuestId);
        int result = UserSession.getGuestId();
        assertEquals(testGuestId, result);
    }
}
