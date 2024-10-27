/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBookingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alanbokchoy
 */
public class InMemoryDBManager extends DBManager {

    private List<User> users = new ArrayList<>();

    public static class User {

        String username;
        String password;
        String name;
        String email;
        String phone;

        public User(String username, String password, String name, String email, String phone) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }
    }

    @Override
    public void registerGuest(String username, String password, String name, String email, String phone) {
        users.add(new User(username, password, name, email, phone));
    }

    public List<User> getUsers() {
        return users;
    }
}
