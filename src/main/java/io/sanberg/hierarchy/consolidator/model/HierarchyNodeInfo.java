package io.sanberg.hierarchy.consolidator.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class HierarchyNodeInfo implements Comparable<HierarchyNodeInfo> {
    int id;
    LocalDate beginDate;
    LocalDate endDate;
    BigDecimal percentage;
    int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
