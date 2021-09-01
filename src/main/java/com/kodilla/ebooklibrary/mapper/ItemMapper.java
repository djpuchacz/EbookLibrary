package com.kodilla.ebooklibrary.mapper;

import com.kodilla.ebooklibrary.domain.dto.CreateItemDto;
import com.kodilla.ebooklibrary.domain.dto.ItemDto;
import com.kodilla.ebooklibrary.domain.dto.UpdatedItemDto;
import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Title;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.service.ItemService;
import com.kodilla.ebooklibrary.service.TitleService;
import com.kodilla.ebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private TitleService titleService;
    @Autowired
    private ItemService itemService;

    public Item mapToItem(CreateItemDto createItemDto) throws Exception {
        Optional<User> user = userService.getUserById(createItemDto.getUserId());
        if (!user.isPresent())
            throw new Exception("User " + createItemDto.getUserId() + " doesn't exist.");
        Title title = titleService.findByIdAndUser(createItemDto.getTitleId(), user.get());
        return new Item(user.get(), title, createItemDto.getPurchaseDate());
    }

    public List<ItemDto> mapToItemDtoList(List<Item> items) {
        return items.stream()
                .map(i -> new ItemDto(i.getId(), i.getPurchaseDate(), i.getStatus()))
                .collect(Collectors.toList());
    }

    public Item mapToItem(long id, long userId) throws Exception {
        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent())
            throw new Exception("User " + userId + " doesn't exist.");
        return itemService.getItem(user.get(), id);
    }

    public UpdatedItemDto mapToUpdatedItemDto(Item item) {
        return new UpdatedItemDto(item.getId(), item.getPurchaseDate());
    }

    public Item mapToItemNullable(User user, int itemId) {
        return itemService.findByUserAndId(user, itemId);
    }
}
