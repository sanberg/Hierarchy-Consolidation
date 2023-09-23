package io.sanberg.hierarchy.consolidator;

import io.sanberg.hierarchy.consolidator.model.Company;

import java.util.HashMap;

public class CompaniesHierarchyView {
    HashMap<Integer, Company> companies;

    public CompaniesHierarchyView(HashMap<Integer, Company> companies) {
        this.companies = companies;
    }

    public HashMap<Integer, Company> getCompanies() {
        return this.companies;
    }

    public void setCompanies(HashMap<Integer, Company> companies) {
        this.companies = companies;
    }

    public void printHierarchy(int rootId) {
        printHierarchyRecursive(rootId, 1);
    }

    private int printHierarchyRecursive(int id, int level) {
        if (this.companies.get(id) == null || level == 1) {
            System.out.print(id + "\n");
        }
        for (Company comp : this.companies.values()) {
            if (comp.getOwnerId() == id) {
                System.out.printf(">".repeat(level) + "%d\n", comp.getId());
                level = printHierarchyRecursive(comp.getId(), ++level);
            }
        }
        return --level;
    }
}
