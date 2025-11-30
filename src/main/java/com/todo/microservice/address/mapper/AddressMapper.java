package com.todo.microservice.address.mapper;

import com.todo.microservice.address.dto.AddressCreateDTO;
import com.todo.microservice.address.dto.AddressResponseDTO;
import com.todo.microservice.address.dto.AddressUpdateDTO;
import com.todo.microservice.address.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    // Convert AddressCreateDTO to Address entity
    public Address toEntity(AddressCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setStateOrProvince(dto.getStateOrProvince());
        address.setZipCode(dto.getZipCode());
        address.setCountry(dto.getCountry());
        address.setCurrent(dto.isCurrent());

        return address;
    }

    // Convert Address entity to AddressResponseDTO
    public AddressResponseDTO toResponseDTO(Address address) {
        if (address == null) {
            return null;
        }

        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setId(address.getId());
        dto.setUserId(address.getUserId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setStateOrProvince(address.getStateOrProvince());
        dto.setZipCode(address.getZipCode());
        dto.setCountry(address.getCountry());
        dto.setCurrent(address.isCurrent());

        return dto;
    }

    // Update Address entity from AddressUpdateDTO
    public void updateEntityFromDTO(AddressUpdateDTO dto, Address address) {
        if (dto == null || address == null) {
            return;
        }

        if (dto.getStreet() != null) {
            address.setStreet(dto.getStreet());
        }
        if (dto.getCity() != null) {
            address.setCity(dto.getCity());
        }
        if (dto.getStateOrProvince() != null) {
            address.setStateOrProvince(dto.getStateOrProvince());
        }
        if (dto.getZipCode() != null) {
            address.setZipCode(dto.getZipCode());
        }
        if (dto.getCountry() != null) {
            address.setCountry(dto.getCountry());
        }
        if (dto.getCurrent() != null) {
            address.setCurrent(dto.getCurrent());
        }
    }

}
