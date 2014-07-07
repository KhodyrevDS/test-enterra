package ru.kds.service;

import ru.kds.domain.Bug;
import ru.kds.domain.User;
import ru.kds.util.PagedList;

/**
 *
 */
public interface BugService {
    PagedList<Bug> findAll(int page, int perPage);

    Bug findOne(Long id);

    void save(Bug bug);

    void delete(Long id);
}
