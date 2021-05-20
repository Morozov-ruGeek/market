package ru.geekbrains.AMorozov.market.dtos;

import lombok.Data;

@Data
public class NewUserRegistrationDto {
    private String username;
    private String password;
    private String email;

}
