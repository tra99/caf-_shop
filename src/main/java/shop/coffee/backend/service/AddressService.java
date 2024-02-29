package shop.coffee.backend.service;

import java.util.*;
import org.springframework.stereotype.Service;

import shop.coffee.backend.entity.Address;
import shop.coffee.backend.repository.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository=addressRepository;
    }
    
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(long id){
        return addressRepository.findById(id);
    }

    public Address createOrUpdateAddress(Address address){
        return addressRepository.save(address);
    }

    public void deleteAddressById(long id){
        addressRepository.deleteById(id);
    }
}
