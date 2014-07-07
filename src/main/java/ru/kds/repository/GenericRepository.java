package ru.kds.repository;

import ru.kds.domain.Domain;
import ru.kds.util.PagedList;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public interface GenericRepository<T extends Domain, ID extends Serializable> {

    T get(ID id);

    T load(ID id);

    List<T> findAll();

    PagedList<T> findAll(int page, int perPage);

    T save(T entity);

    void delete(T entity);
}
