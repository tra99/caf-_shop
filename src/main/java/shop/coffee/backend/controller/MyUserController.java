package shop.coffee.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import shop.coffee.backend.entity.MyUser;
import shop.coffee.backend.service.MyUserService;

@RestController
@RequestMapping("/api/user")
public class MyUserController {
    private final MyUserService myUserService;

    @Autowired
    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MyUser myUser) {
        try {
            myUserService.signUp(myUser);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> users = myUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<MyUser> getUser(@PathVariable String username) {
        try {
            MyUser myUser = myUserService.findByUsername(username);
            return ResponseEntity.ok(myUser);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/update-password")
    public ResponseEntity<String> updatePassword(
            @PathVariable String username,
            @RequestParam(name = "newPassword") String newPassword
    ) {
        try {
            myUserService.updatePassword(username, newPassword);
            return ResponseEntity.ok("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            myUserService.deleteByUsername(username);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestParam String username, @RequestParam String password) {
        if (myUserService.signIn(username, password)) {
            return ResponseEntity.ok("Sign-in successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }
}
