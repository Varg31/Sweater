package com.example.service;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private UserRepo userRepo;
    private MailSender mailSender;

    @Value("${activation.domain}")
    private String activationDomain;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void createUser(User user) {
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public void deleteUser(Long userId) throws NoSuchElementException {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty())
            throw new NoSuchElementException("No user with id: " + userId);

        userRepo.delete(user.get());
    }

    @Transactional
    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s! \n" + "Welcome to Sweater. " +
                            "Please, visit next link to activate your account: %s%s",
                    user.getUsername(), activationDomain, user.getActivationCode());

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null)
            return false;

        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }

}
