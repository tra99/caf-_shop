package shop.coffee.backend.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import shop.coffee.backend.entity.Image;

public interface ImageRepository extends JpaRepository<Image,Long>{

    Optional<Image> findByName(String fileName);
    long countImageByItemId(long id);
}
