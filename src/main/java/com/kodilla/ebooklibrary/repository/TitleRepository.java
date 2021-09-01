package com.kodilla.ebooklibrary.repository;

import com.kodilla.ebooklibrary.domain.entity.Title;
import com.kodilla.ebooklibrary.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TitleRepository extends CrudRepository<Title, Long> {
    @Override
    Title save(Title title);

    List<Title> findAllByUser(User user);

    int deleteByIdAndUser(long id, User user);

    List<Title> findByIdAndUser(long id, User user);
}
