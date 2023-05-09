package com.jpaexample.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A very simple representation of a Product that is used for demonstration purposes.
 */
@Entity
@Table(name = "opt_dist_products")
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.DIRTY)
public class OptimisticDistinctProduct {
    @Id
    private int id;
    private String name;
    private int stock;

    public OptimisticDistinctProduct() {
    }

    public OptimisticDistinctProduct(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
