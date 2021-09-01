package com.kodilla.ebooklibrary.service;

import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Rent;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Transactional
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    public long createRent(Rent rent) {
        rentRepository.save(rent);
        return rent.getId();
    }

    public List<Rent> getRents(User user, Item item) {
        return rentRepository.findAllByItemAndUser(item, user);
    }

    public Rent getRent(long id, User user) throws Exception {
        Optional<Rent> rent = rentRepository.findByIdAndUser(id, user);
        if (!rent.isPresent())
            throw new Exception("Rent id=" + id + " for user id=" + user.getId() + " doesn't exist.");
        return rent.get();
    }

    public Rent updateRent(Rent rent) {
        return rentRepository.save(rent);
    }

    public boolean deleteRent(User user, int id) {
        Optional<Rent> rent = rentRepository.findByIdAndUser(id, user);
        if (rent.isPresent()) {
            rentRepository.delete(rent.get());
            return true;
        } else {
            return false;
        }
    }
}