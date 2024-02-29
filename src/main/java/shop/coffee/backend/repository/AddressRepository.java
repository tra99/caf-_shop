package shop.coffee.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.coffee.backend.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

    
} 
