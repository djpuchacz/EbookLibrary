package com.kodilla.ebooklibrary.controller;

import com.kodilla.ebooklibrary.domain.dto.CreateTitleDto;
import com.kodilla.ebooklibrary.domain.dto.TitleDto;
import com.kodilla.ebooklibrary.domain.dto.UpdateTitleDto;
import com.kodilla.ebooklibrary.mapper.TitleMapper;
import com.kodilla.ebooklibrary.mapper.UserMapper;
import com.kodilla.ebooklibrary.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/titles/") class TitlesController {
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private TitleService titleService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(path = "/")
    public List<TitleDto> getTitles(@RequestParam long userId) {
        return titleMapper.mapToTitleDtoList(
                titleService.getTitles(userMapper.mapToUserNullable(userId)));
    }

    @PostMapping(path = "/")
    public long createTitle(@RequestBody CreateTitleDto createTitleDto) throws Exception {
        return titleService.createTitle(titleMapper.mapToTitle(createTitleDto)).getId();
    }

    @PutMapping(path = "/")
    public TitleDto updateTitle(@RequestBody UpdateTitleDto updateTitleDto) throws Exception {
        return titleMapper.mapToTitleDto(titleService.updateTitle(titleMapper.mapToTitle(updateTitleDto)));
    }

    @DeleteMapping(path = "/")
    public boolean deleteTitle(@RequestParam long userId, @RequestParam long id) {
        return titleService.deleteTitle(userMapper.mapToUserNullable(userId), id);
    }
}