package com.anjun.eskokado.apideliveryesk.resources.dto;

import lombok.*;

import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class User {

    public String username;
    public String password;
    public Set<Role> roles;

    public static User findByUsername(String username) {
        String userUsername = "user";
        String userPassword = "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=";

        String adminUsername = "admin";
        String adminPassword = "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=";

        if (username.equals(userUsername)) {
            return new User(userUsername, userPassword, Collections.singleton(Role.USER));
        } else if (username.equals(adminUsername)) {
            return new User(adminUsername, adminPassword, Collections.singleton(Role.ADMIN));
        } else {
            return null;
        }
    }

}