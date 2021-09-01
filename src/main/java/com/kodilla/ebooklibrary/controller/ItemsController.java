package com.kodilla.ebooklibrary.controller;

import com.kodilla.ebooklibrary.domain.dto.CreateItemDto;
import com.kodilla.ebooklibrary.domain.dto.ItemDto;
import com.kodilla.ebooklibrary.domain.dto.ModifyItemDto;
import com.kodilla.ebooklibrary.domain.dto.UpdatedItemDto;
import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Title;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.mapper.ItemMapper;
import com.kodilla.ebooklibrary.mapper.TitleMapper;
import com.kodilla.ebooklibrary.mapper.UserMapper;
import com.kodilla.ebooklibrary.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items/")
class ItemsController {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TitleMapper titleMapper;

    @GetMapping(path = "/")
    public List<ItemDto> getItems(@RequestParam int userId, @RequestParam int titleId) {
        User user = userMapper.mapToUserNullable(userId);
        Title title = titleMapper.mapToTitleNullable(user, titleId);
        return itemMapper.mapToItemDtoList(itemService.getItems(user, title));
    }

    @PostMapping(path = "/")
    public long createItem(@RequestBody CreateItemDto createItemDto) throws Exception {
        return itemService.createItem(itemMapper.mapToItem(createItemDto));
    }

    @PutMapping(path = "/")
    public UpdatedItemDto updateItem(@RequestBody ModifyItemDto modifyItemDto) throws Exception {
        return itemMapper.mapToUpdatedItemDto(
                itemService.updateItem(
                        itemMapper.mapToItem(
                                modifyItemDto.getId(), modifyItemDto.getUserId()),
                        modifyItemDto.getPurchaseDate()));
    }

    @DeleteMapping(path = "/")
    public boolean deleteItem(@RequestParam int userId, @RequestParam int id) throws Exception {
        Item item = itemMapper.mapToItem(id, userId);
        return itemService.deleteItem(item);
    }
}