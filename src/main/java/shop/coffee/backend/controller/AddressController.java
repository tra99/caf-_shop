package shop.coffee.backend.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.coffee.backend.entity.Address;
import shop.coffee.backend.service.AddressService;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService=addressService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Address>> getAllAddresses(){
        List<Address> addresses=addressService.getAllAddress();
        return new ResponseEntity<>(addresses,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable long id){
        Optional<Address> addresses=addressService.getAddressById(id);
        return addresses.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        Address createdAddress = addressService.createOrUpdateAddress(address);
        return new ResponseEntity<>(createdAddress,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable long id, @RequestBody Address updatedAddress){
        Optional<Address> existingAddressOptional=addressService.getAddressById(id);

        if(existingAddressOptional.isPresent()){
            Address existingAdress=existingAddressOptional.get();
            existingAdress.setCity(updatedAddress.getCity());
            existingAdress.setPostalCode(updatedAddress.getPostalCode());
            existingAdress.setState(updatedAddress.getState());
            existingAdress.setStreet(updatedAddress.getStreet());

            Address saveAddress=addressService.createOrUpdateAddress(updatedAddress);
            return new ResponseEntity<>(saveAddress,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable long id) {
        Optional<Address> address = addressService.getAddressById(id);
    
        if (address.isPresent()) {
            addressService.deleteAddressById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
