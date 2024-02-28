package shop.coffee.backend.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import shop.coffee.backend.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser,Long>{
    Optional<MyUser> findByUsername(String username);
    void deleteByUsername(String username);
    List<MyUser> findAll();
} 