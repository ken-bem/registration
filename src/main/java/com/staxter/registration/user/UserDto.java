package com.staxter.registration.user;

import com.staxter.registration.models.User;

/*
* A Dto class to pass around the user object without exposing the password.
*
* */
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;

    public UserDto() {
    }

    public UserDto(User user){
        this.firstName  = user.getFirstName();
        this.lastName = user.getLastName();
        this.id = user.getId();
        this.userName = user.getUserName();
    }

    public UserDto(String id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
