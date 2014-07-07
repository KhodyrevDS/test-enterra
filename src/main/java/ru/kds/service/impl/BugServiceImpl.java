package ru.kds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kds.domain.Bug;
import ru.kds.repository.BugRepository;
import ru.kds.service.BugService;
import ru.kds.util.PagedList;

import java.util.Date;

/**
 *
 */
@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;

    @Override
    @Transactional
    public PagedList<Bug> findAll(int page, int perPage) {
        return bugRepository.findAll(page, perPage);
    }

    @Override
    @Transactional
    public Bug findOne(Long id) {
        return bugRepository.get(id);
    }

    @Override
    @Transactional
    public void save(Bug bug) {
        if (bug.getId() == null) {
            bug.setCreated(new Date());
        }
        bug.setModified(new Date());

        bugRepository.save(bug);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Bug bug = bugRepository.get(id);
        if (bug != null) {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            boolean isAdmin = false;
            for (GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                    isAdmin = true;
                    break;
                }
            }
            if (user.getUsername().equals(bug.getReporter().getUsername()) || isAdmin) {
                bugRepository.delete(bug);
            } else {
                throw new RuntimeException("Недостаточно прав");
            }
        }
    }
}
