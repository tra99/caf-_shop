package shop.coffee.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.*;

import shop.coffee.backend.entity.MyUser;
import shop.coffee.backend.repository.MyUserRepository;

@Service
public class MyUserService {

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserService(MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<MyUser> getAllUsers() {
        return myUserRepository.findAll();
    }

    public MyUser signUp(MyUser myUser) {
        if (myUserRepository.findByUsername(myUser.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return myUserRepository.save(myUser);
    }

    public MyUser findByUsername(String username) {
        return myUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updatePassword(String username, String newPassword) {
        MyUser myUser = myUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        myUser.setPassword(passwordEncoder.encode(newPassword));
        myUserRepository.save(myUser);
    }

    @Transactional
    public void deleteByUsername(String username) {
        myUserRepository.deleteByUsername(username);
    }

    public boolean signIn(String username, String password) {
        return myUserRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
