package com.todo.microservice.address.service;

import com.todo.microservice.address.dto.AddressCreateDTO;
import com.todo.microservice.address.dto.AddressResponseDTO;
import com.todo.microservice.address.dto.AddressUpdateDTO;

import java.util.List;

public interface IAddressService {
    AddressResponseDTO createAddress(AddressCreateDTO dto, String userId);

    AddressResponseDTO getAddressById(String id, String userId);

    List<AddressResponseDTO> getAllAddressesByUserId(String userId);

    AddressResponseDTO getCurrentAddress(String userId);

    AddressResponseDTO updateAddress(String id, String userId, AddressUpdateDTO dto);

    void deleteAddress(String id, String userId);

    AddressResponseDTO setCurrentAddress(String id, String userId);
}
