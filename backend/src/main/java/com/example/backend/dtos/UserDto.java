package com.example.backend.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String career;
    private String password;
    private boolean gender;
    private String username;
    private String imageLink;
    private String role;
}
