package io.sanberg.hierarchy.consolidator.controller;

import io.sanberg.hierarchy.consolidator.model.Company;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The controller for command line interface.
 */
public class CLIController {
    Scanner scanner = new Scanner(System.in);

    /**
     * Gets hierarchy file path from cli input.
     * At first, tries to open file from swing dialog
     * If cancelled, get filename from cli input in src/main/resources/ directory
     * @return the hierarchy file path
     */
    public Path getHierarchyFilePath() {
        CompaniesFileController fileController = new CompaniesFileController();
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(Paths.get(".").toFile());
        int returnVal = fc.showOpenDialog(null);
        HashMap<Integer, Company> companies;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return Path.of(fc.getSelectedFile().getAbsolutePath());
        } else {
            System.out.println("Enter filename from src/main/resources/:");
            String filename = this.scanner.nextLine();
            return Path.of("src/main/resources/" + filename);
        }
    }

    /**
     * Gets root company id from cli input.
     *
     * @return the root company id
     */
    public int getRootCompanyId() {
        System.out.println("Enter root company id:");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Gets report date from cli input.
     *
     * @return the report date
     */
    public LocalDate getReportDate() {
        System.out.println("Enter report date in dd.MM.yyyy:");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String str = scanner.nextLine();
        return LocalDate.parse(str, dateTimeFormatter);
    }
}
