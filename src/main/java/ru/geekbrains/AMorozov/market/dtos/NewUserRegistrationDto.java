package ru.geekbrains.AMorozov.market.dtos;

import lombok.Data;
import ru.geekbrains.AMorozov.market.models.Role;

@Data
public class NewUserRegistrationDto {
    private String username;
    private String password;
    private String email;
    private String role;
}
