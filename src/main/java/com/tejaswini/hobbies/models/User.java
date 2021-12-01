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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(float latitude, float longitude) {
        this.location = new Location(latitude, longitude);
    }
}
