package io.sanberg.hierarchy.consolidator.model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Company {
    int id;
    HashMap<Integer, BigDecimal> ownershipMap;
    int ownerId;

    public Company(int id, HashMap<Integer, BigDecimal> ownershipMap) {
        this.id = id;
        this.ownershipMap = ownershipMap;
    }

    public Company(int id, HashMap<Integer, BigDecimal> ownershipMap, int ownerId) {
        this.id = id;
        this.ownershipMap = ownershipMap;
        this.ownerId = ownerId;
    }

    public HashMap<Integer, BigDecimal> getOwnershipMap() {
        return ownershipMap;
    }

    public void setOwnershipMap(HashMap<Integer, BigDecimal> ownershipMap) {
        this.ownershipMap = ownershipMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
