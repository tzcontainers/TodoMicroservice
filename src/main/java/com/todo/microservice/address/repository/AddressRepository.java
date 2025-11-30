package com.todo.microservice.address.repository;

import com.todo.microservice.address.model.Address;
import com.todo.microservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    List<Address> findByUser(User user);

    public List<Address> findByUserId(String userId);

    public Optional<Address> findByUserIdAndCurrent(String userId, boolean current);

    List<Address> findByCity(String city);

    List<Address> findByCountry(String country);
}