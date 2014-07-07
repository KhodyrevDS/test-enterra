package ru.kds.repository.impl;

import org.springframework.stereotype.Repository;
import ru.kds.domain.Bug;
import ru.kds.repository.BugRepository;

/**
 *
 */
@Repository
public class BugRepositoryImpl extends AbstractRepository<Bug, Long> implements BugRepository {
}
