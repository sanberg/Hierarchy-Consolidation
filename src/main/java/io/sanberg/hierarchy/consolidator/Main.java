package io.sanberg;

import io.sanberg.hierarchy.consolidator.controller.CLIController;
import io.sanberg.hierarchy.consolidator.controller.CompaniesFileController;
import io.sanberg.hierarchy.consolidator.view.CompaniesHierarchyView;
import io.sanberg.hierarchy.consolidator.model.Company;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("add input filename (should be added to src/main/resources)");
        CLIController cliController = new CLIController();
        cliController.readFilename();
        try {
            CompaniesFileController fileController = new CompaniesFileController();

            HashMap<Integer, Company> companies = fileController.readFile(LocalDate.now());
            CompaniesHierarchyView companiesHierarchyView = new CompaniesHierarchyView(companies);
            companiesHierarchyView.printHierarchy(2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}