package ru.geekbrains.AMorozov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.AMorozov.market.dtos.NewUserRegistrationDto;
import ru.geekbrains.AMorozov.market.error_handling.MarketError;
import ru.geekbrains.AMorozov.market.models.Role;
import ru.geekbrains.AMorozov.market.models.User;
import ru.geekbrains.AMorozov.market.repositories.UserRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public void registrationNewUser(NewUserRegistrationDto newUserDto) {
        if (userRepository.findByUsername(newUserDto.getUsername()).isPresent()) {
            new ResponseEntity<>(new MarketError(HttpStatus.NOT_ACCEPTABLE.value(), "Пользователь с таким именем уже существует!"), HttpStatus.NOT_ACCEPTABLE);
        }
        newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        newUserDto.setRole(roleService.findRoleByName("ROLE_USER"));
        User user = new User(newUserDto);
        userRepository.save(user);
    }
}