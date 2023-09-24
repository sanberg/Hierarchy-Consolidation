package io.sanberg.hierarchy.consolidator.controller;

import io.sanberg.hierarchy.consolidator.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * The controller class for processing the file.
 */
public class CompaniesFileController {
    /**
     * Read file hash map.
     *
     * @param reportDate the report date
     * @param path       the path to file
     * @return hashmap containing map of companies (id, company)
     * @throws FileNotFoundException the file not found exception
     */
    public HashMap<Integer, Company> readFile(LocalDate reportDate, Path path) throws FileNotFoundException {
        HashMap<Integer, Company> companiesMap = new HashMap<>();
        List<HierarchyNodeInfo> companyInfoList;
        CSVInfoBuilder builder = new CompanyInfoBuilder();
        try (Stream<String> stream = Files.lines(path)) {
            companyInfoList = stream.filter(s -> !(s.startsWith("ID")))
                    .map(s -> s.split(";"))
                    .map(builder::build)
                    .filter(companyInfo -> companyInfo.getBeginDate().isBefore(reportDate)
                            && companyInfo.getEndDate().isAfter(reportDate))
                    .sorted(Comparator.comparingInt(HierarchyNodeInfo::getId))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        processCompanyInfoToMap(companyInfoList, companiesMap);
        return companiesMap;
    }

    /**
     *  converting company info to map of companies.
     *
     * @param companyInfoList   list of metadata about the companies
     * @param companiesMap       hashmap containing map of companies (id, company)
     */
    private static void processCompanyInfoToMap(List<HierarchyNodeInfo> companyInfoList, HashMap<Integer, Company> companiesMap) {
        companyInfoList.forEach(
            companyInfo -> {
                Company company = companiesMap.get(companyInfo.getId());
                if (company != null) {
                    HashMap<Integer, BigDecimal> ownershipMap = company.getOwnershipMap();
                    ownershipMap.put(companyInfo.getParentId(), companyInfo.getPercentage());
                    company.setOwnerId(Collections.max(ownershipMap.entrySet(), Map.Entry.comparingByValue()).getKey());
                } else {
                    HashMap<Integer, BigDecimal> ownershipMap = new HashMap<>();
                    ownershipMap.put(companyInfo.getParentId(), companyInfo.getPercentage());
                    companiesMap.put(companyInfo.getId(), new Company(companyInfo.getId(), ownershipMap, companyInfo.getParentId()));
                }
            }
        );
    }
}
