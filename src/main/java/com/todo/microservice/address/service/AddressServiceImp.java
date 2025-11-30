package com.todo.microservice.address.service;

import com.todo.microservice.address.dto.AddressCreateDTO;
import com.todo.microservice.address.dto.AddressResponseDTO;
import com.todo.microservice.address.dto.AddressUpdateDTO;
import com.todo.microservice.address.mapper.AddressMapper;
import com.todo.microservice.address.model.Address;
import com.todo.microservice.address.repository.AddressRepository;
import com.todo.microservice.exceptions.UnauthorizedAccessException;
import com.todo.microservice.exceptions.addressexeption.AddressNotFoundException;
import com.todo.microservice.exceptions.todexception.InvalidInputException;
import com.todo.microservice.exceptions.userexeption.UserNotFoundException;
import com.todo.microservice.user.model.User;
import com.todo.microservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImp implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Transactional
    public AddressResponseDTO createAddress(AddressCreateDTO dto, String userId) {
        // Validation
        if (dto.getStreet() == null || dto.getStreet().trim().isEmpty()) {
            throw new InvalidInputException("Street is required");
        }
        if (dto.getCity() == null || dto.getCity().trim().isEmpty()) {
            throw new InvalidInputException("City is required");
        }
        if (dto.getCountry() == null || dto.getCountry().trim().isEmpty()) {
            throw new InvalidInputException("Country is required");
        }

        // Get the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // If this address is marked as current, unset all other current addresses
        if (dto.isCurrent()) {
            List<Address> userAddresses = addressRepository.findByUserId(userId);
            userAddresses.forEach(addr -> addr.setCurrent(false));
            addressRepository.saveAll(userAddresses);
        }

        // Convert DTO to entity
        Address address = addressMapper.toEntity(dto);
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);

        // Convert entity to response DTO
        return addressMapper.toResponseDTO(savedAddress);
    }

    public AddressResponseDTO getAddressById(String id, String userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id: " + id));

        // Check if the address belongs to the user
        if (!address.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException(
                    String.format("User %s is not authorized to access address with id %s", userId, id)
            );
        }

        return addressMapper.toResponseDTO(address);
    }

    public List<AddressResponseDTO> getAllAddressesByUserId(String userId) {
        // Verify user exists
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        List<Address> addresses = addressRepository.findByUserId(userId);

        return addresses.stream()
                .map(addressMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AddressResponseDTO getCurrentAddress(String userId) {
        // Verify user exists
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        Address currentAddress = addressRepository.findByUserIdAndCurrent(userId, true)
                .orElseThrow(() -> new AddressNotFoundException("No current address found for user: " + userId));

        return addressMapper.toResponseDTO(currentAddress);
    }

    @Transactional
    public AddressResponseDTO updateAddress(String id, String userId, AddressUpdateDTO dto) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id: " + id));

        // Verify user owns this address
        if (!existingAddress.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException(
                    String.format("User %s is not authorized to update address with id %s", userId, id)
            );
        }

        // If updating to current, unset all other current addresses
        if (dto.getCurrent() != null && dto.getCurrent()) {
            List<Address> userAddresses = addressRepository.findByUserId(userId);
            userAddresses.forEach(addr -> {
                if (!addr.getId().equals(id)) {
                    addr.setCurrent(false);
                }
            });
            addressRepository.saveAll(userAddresses);
        }

        // Update entity from DTO
        addressMapper.updateEntityFromDTO(dto, existingAddress);

        Address updatedAddress = addressRepository.save(existingAddress);
        return addressMapper.toResponseDTO(updatedAddress);
    }

    @Override
    public void deleteAddress(String id, String userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Cannot delete. Address not found with id: " + id));

        // Verify user owns this address
        if (!address.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException(
                    String.format("User %s is not authorized to delete address with id %s", userId, id)
            );
        }

        addressRepository.deleteById(id);
    }

    @Transactional
    public AddressResponseDTO setCurrentAddress(String id, String userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id: " + id));

        // Verify user owns this address
        if (!address.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException(
                    String.format("User %s is not authorized to modify address with id %s", userId, id)
            );
        }

        // Unset all other current addresses for this user
        List<Address> userAddresses = addressRepository.findByUserId(userId);
        userAddresses.forEach(addr -> addr.setCurrent(false));
        addressRepository.saveAll(userAddresses);

        // Set this address as current
        address.setCurrent(true);
        Address updatedAddress = addressRepository.save(address);

        return addressMapper.toResponseDTO(updatedAddress);
    }

}
