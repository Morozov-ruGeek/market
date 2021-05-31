package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.market.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService{
    private final RoleRepository repository;

    public String findRoleByName(String roleName){
        return findRoleByName(roleName);
    }
}
