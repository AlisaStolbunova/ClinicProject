package com.clinic.dto;


public class UserDto {

    private Long id;
    private String name;
    private String password;
    private String firstName;
    private String lastName;

    public UserDto(Long id, String name, String password, String firstName, String lastName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
