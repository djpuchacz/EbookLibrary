package com.kodilla.ebooklibrary.service;

import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Title;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public long createItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    public List<Item> getItems(User user, Title title) {
        return itemRepository.findAllByTitleAndUser(title, user);
    }

    public Item getItem(User user, long id) throws Exception {
        Optional<Item> item = itemRepository.findByIdAndUser(id, user);
        if (!item.isPresent())
            throw new Exception("There is no item id=" + id + " for user id=" + user.getId());
        return item.get();
    }

    public Item updateItem(Item item, LocalDate purchaseDate) {
        item.setPurchaseDate(purchaseDate);
        itemRepository.save(item);
        return item;
    }

    public boolean deleteItem(Item item) {
        itemRepository.delete(item);
        return true;
    }

    public Item findByUserAndId(User user, int itemId) {
        return itemRepository.findByIdAndUser(itemId, user).orElse(null);
    }
}
