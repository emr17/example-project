package com.jpaexample.support;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionalRunner {
    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doInTransaction(final Consumer<EntityManager> c) {
        c.accept(em);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T doInTransaction(final Function<EntityManager, T> f) {
        return f.apply(em);
    }
}
