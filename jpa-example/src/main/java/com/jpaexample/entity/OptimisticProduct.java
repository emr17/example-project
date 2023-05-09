package com.jpaexample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

/**
 * A very simple representation of a Product that is used for demonstration purposes.
 */
@Entity
@Table(name = "opt_products")
public class OptimisticProduct {
    @Id
    private int id;
    private String name;
    private int stock;

    @Version
    private int version;


    public OptimisticProduct(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public OptimisticProduct() {

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


}
