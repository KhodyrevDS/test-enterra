package ru.kds.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import ru.kds.domain.Domain;
import ru.kds.repository.GenericRepository;
import ru.kds.util.PagedList;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public abstract class AbstractRepository<T extends Domain, ID extends Serializable>  implements GenericRepository<T, ID> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> persistentClass;


    public AbstractRepository() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public T get(ID id) {
        return (T) sessionFactory.getCurrentSession().get(getPersistentClass(), id);
    }

    public T load(ID id) {
        return (T) sessionFactory.getCurrentSession().load(getPersistentClass(), id);
    }

    public List<T> findAll() {
        return (List<T>) sessionFactory.getCurrentSession().createCriteria(getPersistentClass()).list();
    }

    public PagedList<T> findAll(final int page, final int perPage) {
        List<T> list = sessionFactory.getCurrentSession().createCriteria(getPersistentClass())
                .setFirstResult((page - 1) * perPage).setMaxResults(perPage).addOrder(Order.asc("id")).list();
        int count = ((Long)sessionFactory.getCurrentSession().createCriteria(getPersistentClass())
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();

        return new PagedList<T>(list, count);
    }

    public T save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
