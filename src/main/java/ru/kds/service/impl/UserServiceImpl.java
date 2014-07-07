package ru.kds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kds.domain.Role;
import ru.kds.domain.User;
import ru.kds.repository.RoleRepository;
import ru.kds.repository.UserRepository;
import ru.kds.service.UserService;
import ru.kds.util.PagedList;

import java.util.Collections;
import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static boolean initialize = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (!initialize) {

            Role role = new Role();
            role.setName("ROLE_USER");
            role.setHumanName("Пользователь");
            roleRepository.save(role);

            User user = new User();
            user.setFirstName("Михаил");
            user.setLastName("Михайлов");
            user.setEmail("user@example.com");
            user.setPassword("user");
            user.setRoles(Collections.singleton(role));
            user.setEnabled(true);
            userRepository.save(user);

            role = new Role();
            role.setName("ROLE_MANAGER");
            role.setHumanName("Менеджер");
            roleRepository.save(role);

            user = new User();
            user.setFirstName("Степан");
            user.setLastName("Степанов");
            user.setEmail("manager@example.com");
            user.setPassword("manager");
            user.setRoles(Collections.singleton(role));
            user.setEnabled(true);
            userRepository.save(user);

            role = new Role();
            role.setName("ROLE_ADMIN");
            role.setHumanName("Администратор");
            roleRepository.save(role);

            user = new User();
            user.setFirstName("Иван");
            user.setLastName("Иванов");
            user.setEmail("admin@example.com");
            user.setPassword("admin");
            user.setRoles(Collections.singleton(role));
            user.setEnabled(true);
            userRepository.save(user);

            initialize = true;
        }

        if (email == null || email.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        User user = userRepository.findByEmail(email);

        if (user != null) {
            user.getRoles();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
        }
        throw new UsernameNotFoundException("Пользователь не найден");
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public PagedList<User> findAll(int page, int perPage) {
        return userRepository.findAll(page, perPage);
    }

    @Override
    @Transactional
    public User findOne(Long userId) {
        return userRepository.get(userId);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void save(User u) {
        User user = null;
        if (u.getId() != null) {
            user = userRepository.get(u.getId());
        }

        if (user == null) {
            user = new User();
        }

        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setEmail(u.getEmail());
        user.setEnabled(u.isEnabled());
        user.setRoles(u.getRoles());

        if (u.getPassword() != null && !u.getPassword().isEmpty()) {
            user.setPassword(u.getPassword());
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.load(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
