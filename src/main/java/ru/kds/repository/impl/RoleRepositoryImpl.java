package ru.kds.repository.impl;

import org.springframework.stereotype.Repository;
import ru.kds.domain.Role;
import ru.kds.repository.RoleRepository;

/**
 *
 */
@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role, Long> implements RoleRepository {

}
