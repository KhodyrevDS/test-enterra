package ru.kds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kds.domain.Role;
import ru.kds.repository.RoleRepository;
import ru.kds.service.RoleService;

/**
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role findOne(Long id) {
        return roleRepository.get(id);
    }
}
