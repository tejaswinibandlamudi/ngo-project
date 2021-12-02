package com.tejaswini.hobbies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {

    @Id
    private String email;

    private String firstName;
    private String lastName;
    private String password;

    private Location location;

    private static class Location {
        float latitude;
        float longitude;

        public Location(float latitude, float longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    public static final User EMPTY = new User().setFirstName("").setLastName("").setEmail("");


    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public User setLocation(float latitude, float longitude) {
        this.location = new Location(latitude, longitude);
        return this;
    }
}
