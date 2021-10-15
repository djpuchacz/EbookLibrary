package com.kodilla.ebooklibrary.mapper;

import com.kodilla.ebooklibrary.domain.dto.CreateTitleDto;
import com.kodilla.ebooklibrary.domain.dto.TitleDto;
import com.kodilla.ebooklibrary.domain.dto.UpdateTitleDto;
import com.kodilla.ebooklibrary.domain.entity.Title;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.service.TitleService;
import com.kodilla.ebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private TitleService titleService;


    public Title mapToTitle(CreateTitleDto createTitleDto) throws Exception {
        Optional<User> user = userService.getUserById(createTitleDto.getUserId());
        if (!user.isPresent())
            throw new Exception("User " + createTitleDto.getUserId() + " doesn't exist.");
        return new Title(
                user.get(),
                createTitleDto.getAuthor(),
                createTitleDto.getTitle(),
                createTitleDto.getYear());
    }


    public List<TitleDto> mapToTitleDtoList(List<Title> titles) {
        return titles.stream()
                .map(t -> new TitleDto(t.getId(), t.getAuthor(), t.getTitle(), t.getYear()))
                .collect(Collectors.toList());
    }

    public Title mapToTitle(UpdateTitleDto updateTitleDto) throws Exception {
        Optional<User> user = userService.getUserById(updateTitleDto.getUserId());
        if (!user.isPresent())
            throw new Exception("User " + updateTitleDto.getUserId() + " doesn't exist.");
        Title title = titleService.findByIdAndUser(updateTitleDto.getId(), user.get());
        title.setAuthor(updateTitleDto.getAuthor());
        title.setTitle(updateTitleDto.getTitle());
        title.setYear(updateTitleDto.getYear());
        return title;
    }

    public TitleDto mapToTitleDto(Title title) {
        return new TitleDto(title.getId(), title.getAuthor(), title.getTitle(), title.getYear());
    }

    public Title mapToTitleNullable(User user, int titleId) {
        return titleService.findByIdAndUserNullable(user, titleId);
    }
}
