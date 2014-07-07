package ru.kds.service;

import ru.kds.domain.Role;

/**
 *
 */
public interface RoleService {

    public Iterable<Role> findAll();

    Role findOne(Long id);
}
