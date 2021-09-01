package com.kodilla.ebooklibrary.service;

import com.kodilla.ebooklibrary.domain.entity.Title;
import com.kodilla.ebooklibrary.domain.entity.User;
import com.kodilla.ebooklibrary.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class TitleService {
    @Autowired
    private TitleRepository titleRepository;

    public Title createTitle(Title title) {
        return titleRepository.save(title);
    }

    public List<Title> getTitles(User user) {
        return titleRepository.findAllByUser(user);
    }

    public boolean deleteTitle(User user, long id) {
        return titleRepository.deleteByIdAndUser(id, user) > 0;
    }

    public Title findByIdAndUser(long id, User user) throws Exception {
        List<Title> titles = titleRepository.findByIdAndUser(id, user);
        if (titles.size() != 1)
            throw new
                    Exception("There is no book with id=" + id + " created by user with id=" + user.getId());
        return titles.get(0);
    }

    public Title updateTitle(Title title) {
        return titleRepository.save(title);
    }

    public Title findByIdAndUserNullable(User user, int titleId) {
        List<Title> titles = titleRepository.findByIdAndUser(titleId, user);
        if (titles.size() == 1)
            return titles.get(0);
        return null;
    }
}