package ru.kds.service;

import ru.kds.domain.User;
import ru.kds.util.PagedList;

import java.util.List;

/**
 *
 */
public interface UserService {

    List<User> findAll();

    PagedList<User> findAll(int page, int perPage);

    User findOne(Long id);

    void save(User user);

    User findByEmail(String email);

    void delete(Long id);
}
