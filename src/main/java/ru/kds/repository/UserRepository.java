package ru.kds.repository;

import ru.kds.domain.User;
import ru.kds.util.PagedList;

/**
 *
 */
public interface UserRepository extends GenericRepository<User, Long> {
    User findByEmail(String email);

     PagedList<User> findByEmail(int page, int perPage, String email);
}
