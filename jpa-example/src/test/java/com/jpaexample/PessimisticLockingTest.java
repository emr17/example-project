package com.jpaexample;


import com.jpaexample.entity.OptimisticProduct;
import com.jpaexample.entity.PessimisticProduct;
import com.jpaexample.support.TransactionalRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import jakarta.persistence.LockModeType;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PessimisticLockingTest {
    @Autowired
    private TransactionalRunner txRunner;

    @Test
    void testExplicitPessimisticLockingForceIncrement(){
        txRunner.doInTransaction(em ->{
            PessimisticProduct pessimisticProduct = em.find(PessimisticProduct.class,1, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
        });

    }

    @Test
    void testExplicitPessimisticLockingRead(){
        int pId = 1;
        txRunner.doInTransaction(em -> {
            em.persist(new OptimisticProduct(pId, "T-Shirt", 10));
        });
        txRunner.doInTransaction(em -> {
            PessimisticProduct pessimisticProduct = em.find(PessimisticProduct.class,1, LockModeType.PESSIMISTIC_READ);
            txRunner.doInTransaction(em2 ->{
                PessimisticProduct insideProduct = em.find(PessimisticProduct.class,1, LockModeType.PESSIMISTIC_READ);
                insideProduct.setStock(2);

            });
        });
    }

    @Test
    void testExplicitPessimisticLockingWrite(){
        int pId = 1;
        txRunner.doInTransaction(em -> {
            em.persist(new OptimisticProduct(pId, "T-Shirt", 10));
        });
        txRunner.doInTransaction(em -> {
            PessimisticProduct pessimisticProduct = em.find(PessimisticProduct.class,1, LockModeType.PESSIMISTIC_WRITE);
            txRunner.doInTransaction(em2 ->{
                PessimisticProduct insideProduct = em.find(PessimisticProduct.class,1, LockModeType.PESSIMISTIC_WRITE);
                insideProduct.setStock(2);

            });
        });
    }



}
