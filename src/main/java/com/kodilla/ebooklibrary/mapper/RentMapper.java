package com.kodilla.ebooklibrary.mapper;

import com.kodilla.ebooklibrary.domain.dto.CreateRentDto;
import com.kodilla.ebooklibrary.domain.dto.RentDto;
import com.kodilla.ebooklibrary.domain.dto.UpdateRentDto;
import com.kodilla.ebooklibrary.domain.entity.Item;
import com.kodilla.ebooklibrary.domain.entity.Rent;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.service.ItemService;
import com.kodilla.ebooklibrary.service.RentService;
import com.kodilla.ebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private RentService rentService;

    public Rent mapToRent(CreateRentDto createRentDto) throws Exception {
        Optional<User> user = userService.getUserById(createRentDto.getUserId());
        if (!user.isPresent())
            throw new Exception("User " + createRentDto.getUserId() + " doesn't exist");
        Item item = itemService.getItem(user.get(), createRentDto.getItemId());
        return new Rent(
                user.get(),
                item,
                createRentDto.getCustomerName(),
                createRentDto.getRentDate(),
                createRentDto.getRentDate().plusDays(7));
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rents) {
        return rents.stream()
                .map(r -> new RentDto(r.getId(), r.getCustomerName(), r.getRentDate(), r.getExpirationDate()))
                .collect(Collectors.toList());
    }

    public Rent mapToRent(UpdateRentDto updateRentDto) throws Exception {
        Optional<User> user = userService.getUserById(updateRentDto.getUserId());
        if (!user.isPresent())
            throw new Exception("User " + updateRentDto.getUserId() + " doesn't exist.");
        Rent rent = rentService.getRent(updateRentDto.getId(), user.get());
        rent.setCustomerName(updateRentDto.getCustomerName());
        rent.setRentDate(updateRentDto.getRentDate());
        rent.setExpirationDate(updateRentDto.getExpirationDate());
        return rent;
    }

    public RentDto mapToRentDto(Rent rent) {
        return new RentDto(
                rent.getId(), rent.getCustomerName(), rent.getRentDate(), rent.getExpirationDate());
    }
}
