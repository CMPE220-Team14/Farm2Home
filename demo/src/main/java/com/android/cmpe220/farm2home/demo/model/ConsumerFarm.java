package com.android.cmpe220.farm2home.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConsumerFarm implements Serializable {
    private static final long serialVersionUID = -4073256626483275668L;

    private int fId;
    private String fName;
    private BigDecimal fDistance;

    public ConsumerFarm() {
        super();
    }

    public ConsumerFarm(int fId, String fName, BigDecimal fDistance) {
        setId(fId);
        setName(fName);
        setDistance(fDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ConsumerFarm)) return false;

        return (this.fId == ((ConsumerFarm) o).getId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + fId;
        hash = hash * prime + (fName == null ? 0 : fName.hashCode());
        hash = hash * prime + (fDistance == null ? 0 : fDistance.hashCode());

        return hash;
    }


    public int getId() { return fId; }

    public void setId(int id) {
        this.fId = id;
    }

    public String getName() { return fName; }

    public void setName(String name) {
        this.fName = name;
    }

    public BigDecimal getDistance() {
        return fDistance;
    }

    public void setDistance(BigDecimal fDistance) {
        this.fDistance = fDistance;
    }
}
