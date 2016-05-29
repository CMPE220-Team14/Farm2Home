package com.android.cmpe220.farm2home.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.android.cmpe220.farm2home.model.Saleable;

public class ConsumerProduct implements Saleable, Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    private int pId;
    private String pName;
    private BigDecimal pPrice;
    private String pDescription;

    public ConsumerProduct() {
        super();
    }

    public ConsumerProduct(int pId, String pName, BigDecimal pPrice, String pDescription) {
        setId(pId);
        setName(pName);
        setPrice(pPrice);
        setDescription(pDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ConsumerProduct)) return false;

        return (this.pId == ((ConsumerProduct) o).getId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + pId;
        hash = hash * prime + (pName == null ? 0 : pName.hashCode());
        hash = hash * prime + (pPrice == null ? 0 : pPrice.hashCode());
        hash = hash * prime + (pDescription == null ? 0 : pDescription.hashCode());

        return hash;
    }


    public int getId() {
        return pId;
    }

    public void setId(int id) {
        this.pId = id;
    }

    @Override
    public BigDecimal getPrice() {
        return pPrice;
    }

    @Override
    public String getName() {
        return pName;
    }

    public void setPrice(BigDecimal price) {
        this.pPrice = price;
    }

    public void setName(String name) {
        this.pName = name;
    }

    public String getDescription() {
        return pDescription;
    }

    public void setDescription(String pDescription) {
        this.pDescription = pDescription;
    }
}