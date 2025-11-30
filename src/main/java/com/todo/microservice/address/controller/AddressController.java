package com.todo.microservice.address.controller;

import com.todo.microservice.address.dto.AddressCreateDTO;
import com.todo.microservice.address.dto.AddressResponseDTO;
import com.todo.microservice.address.dto.AddressUpdateDTO;
import com.todo.microservice.address.service.AddressServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressServiceImp addressService;

    // Create a new address for a user
    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressCreateDTO dto, @RequestHeader("X-User-Id") String userId) {
        AddressResponseDTO createdAddress = addressService.createAddress(dto, userId);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable String id, @RequestHeader("X-User-Id") String userId) {
        AddressResponseDTO address = addressService.getAddressById(id, userId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    // Get all addresses for the logged-in user
    @GetMapping("/user")
    public ResponseEntity<List<AddressResponseDTO>> getAllUserAddresses(@RequestHeader("X-User-Id") String userId) {
        List<AddressResponseDTO> addresses = addressService.getAllAddressesByUserId(userId);
        return new ResponseEntity<>(addresses, addresses.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    // Get current address for the logged-in user
    @GetMapping("/current")
    public ResponseEntity<AddressResponseDTO> getCurrentAddress(@RequestHeader("X-User-Id") String userId) {
        AddressResponseDTO address = addressService.getCurrentAddress(userId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    // Update an address
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable String id, @RequestHeader("X-User-Id") String userId, @RequestBody AddressUpdateDTO dto) {
        AddressResponseDTO updatedAddress = addressService.updateAddress(id, userId, dto);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    // Delete an address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id, @RequestHeader("X-User-Id") String userId) {
        addressService.deleteAddress(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Set an address as current
    @PatchMapping("/{id}/set-current")
    public ResponseEntity<AddressResponseDTO> setCurrentAddress(@PathVariable String id, @RequestHeader("X-User-Id") String userId) {
        AddressResponseDTO address = addressService.setCurrentAddress(id, userId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
