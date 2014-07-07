package ru.kds.repository.impl;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.kds.domain.User;
import ru.kds.repository.UserRepository;
import ru.kds.util.PagedList;

import java.util.List;

/**
 *
 */
@Repository
public class UserRepositoryImpl extends AbstractRepository<User, Long> implements UserRepository {
    @Override
    public User findByEmail(final String email) {
        return (User) sessionFactory.getCurrentSession().createCriteria(getPersistentClass()).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public PagedList<User> findByEmail(final int page, final int perPage, final String email) {
        List<User> list = sessionFactory.getCurrentSession().createCriteria(getPersistentClass()).add(Restrictions.ilike("email", email, MatchMode.ANYWHERE))
                .setFirstResult((page-1)*perPage).setMaxResults(perPage).list();

        int count = ((Long)sessionFactory.getCurrentSession().createCriteria(getPersistentClass()).add(Restrictions.ilike("email", email, MatchMode.ANYWHERE))
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();

        return new PagedList<User>(list, count);
    }
}
