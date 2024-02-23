package shop.coffee.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.coffee.backend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

    
} 
