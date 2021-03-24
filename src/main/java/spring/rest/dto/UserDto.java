package spring.rest.dto;

import spring.rest.model.Role;
import spring.rest.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;
    private Set<Role> setRoles = new HashSet<>();

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.setRoles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getSetRoles() {
        return setRoles;
    }
}
