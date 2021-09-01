package com.kodilla.ebooklibrary.repository;

import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Title;
import com.kodilla.ebooklibrary.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Override
    Item save(Item item);

    List<Item> findAllByTitleAndUser(Title title, User user);

    Optional<Item> findByIdAndUser(long id, User user);
}
