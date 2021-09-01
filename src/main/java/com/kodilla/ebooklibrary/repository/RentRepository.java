package com.kodilla.ebooklibrary.repository;

import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Rent;
import com.kodilla.ebooklibrary.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    Rent save(Rent rent);

    List<Rent> findAllByItemAndUser(Item item, User user);

    Optional<Rent> findByIdAndUser(long id, User user);
}
