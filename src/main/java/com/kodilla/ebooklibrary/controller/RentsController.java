package com.kodilla.ebooklibrary.controller;

import com.kodilla.ebooklibrary.domain.dto.CreateRentDto;
import com.kodilla.ebooklibrary.domain.dto.RentDto;
import com.kodilla.ebooklibrary.domain.dto.UpdateRentDto;
import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Rent;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.mapper.ItemMapper;
import com.kodilla.ebooklibrary.mapper.RentMapper;
import com.kodilla.ebooklibrary.mapper.UserMapper;
import com.kodilla.ebooklibrary.service.RentService;
import com.kodilla.ebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rents/")
class RentsController {
    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private RentService rentService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public List<RentDto> getRents(@RequestParam int userId, @RequestParam int itemId) {
        User user = userMapper.mapToUserNullable(userId);
        Item item = itemMapper.mapToItemNullable(user, itemId);
        return rentMapper.mapToRentDtoList(rentService.getRents(user, item));
    }

    @PostMapping(path = "/")
    public long createRent(@RequestBody CreateRentDto createRentDto) throws Exception {
        return rentService.createRent(rentMapper.mapToRent(createRentDto));
    }

    @PutMapping(path = "/")
    public RentDto updateRent(@RequestBody UpdateRentDto updateRentDto) throws Exception {
        Rent rent = rentMapper.mapToRent(updateRentDto);
        return rentMapper.mapToRentDto(rentService.updateRent(rent));
    }

    @DeleteMapping(path = "/")
    public boolean deleteRent(@RequestParam int userId, @RequestParam int id) throws Exception {
        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent())
            throw new Exception("User " + userId + " doesn't exist.");
        return rentService.deleteRent(user.get(), id);
    }
}