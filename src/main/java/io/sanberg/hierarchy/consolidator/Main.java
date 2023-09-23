package io.sanberg.hierarchy.consolidator;

import io.sanberg.hierarchy.consolidator.controller.CLIController;
import io.sanberg.hierarchy.consolidator.controller.CompaniesFileController;
import io.sanberg.hierarchy.consolidator.model.Company;
import io.sanberg.hierarchy.consolidator.view.CompaniesHierarchyView;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CLIController cliController = new CLIController();
        Path hierarchyFilePath = cliController.getHierarchyFilePath();
        int rootId = cliController.getRootCompanyId();
        LocalDate reportDate = cliController.getReportDate();
        try {
            CompaniesFileController fileController = new CompaniesFileController();
            HashMap<Integer, Company> companyHashMap = fileController.readFile(reportDate, hierarchyFilePath);
            CompaniesHierarchyView hierarchyView = new CompaniesHierarchyView(companyHashMap);
            hierarchyView.printHierarchy(rootId);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}