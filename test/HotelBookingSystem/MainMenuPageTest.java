/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package HotelBookingSystem;

import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alanbokchoy
 */
public class MainMenuPageTest {

    private MainMenuPage mainMenuPage;

    public MainMenuPageTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            mainMenuPage = new MainMenuPage();
        });
    }

    @After
    public void tearDown() {
        SwingUtilities.invokeLater(() -> {
            if (mainMenuPage != null) {
                mainMenuPage.frame.dispose();
            }
        });
    }

    @Test
    public void testCreateBookingButton() {

        SwingUtilities.invokeLater(() -> {
            mainMenuPage.createBookingButton.doClick();
        });
    }

    @Test
    public void testCancelBookingButton() {
        SwingUtilities.invokeLater(() -> {
            mainMenuPage.cancelBookingButton.doClick();
        });
    }

    @Test
    public void testCheckBookingButton() {
        SwingUtilities.invokeLater(() -> {
            mainMenuPage.checkBookingButton.doClick();
        });
    }

    @Test
    public void testInformationButton() {
        SwingUtilities.invokeLater(() -> {
            mainMenuPage.informationButton.doClick();
        });
    }

    @Test
    public void testSignOutButton() {
        SwingUtilities.invokeLater(() -> {
            mainMenuPage.signoutButton.doClick();
        });
    }
}
