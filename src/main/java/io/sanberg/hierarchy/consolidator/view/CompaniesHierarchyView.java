package io.sanberg.hierarchy.consolidator.view;

import io.sanberg.hierarchy.consolidator.model.Company;

import java.util.HashMap;

/**
 * The class for CompaniesHierarchy view.
 */
public class CompaniesHierarchyView {
    HashMap<Integer, Company> companies;

    public CompaniesHierarchyView(HashMap<Integer, Company> companies) {
        this.companies = companies;
    }

    /**
     * Print hierarchy to cli.
     *
     * @param rootId the root company id
     */
    public void printHierarchy(int rootId) {
        printHierarchyRecursive(rootId, 1);
    }

    /**
     * Print hierarchy to cli, recursive method for each company in loop.
     *
     * @param id the company id
     * @param level hierarchy depth level from root company
     */
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

    public HashMap<Integer, Company> getCompanies() {
        return this.companies;
    }

    public void setCompanies(HashMap<Integer, Company> companies) {
        this.companies = companies;
    }
}
