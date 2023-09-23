package io.sanberg;

import io.sanberg.hierarchy.consolidator.model.Company;
import io.sanberg.hierarchy.consolidator.model.CompanyInfo;
import io.sanberg.hierarchy.consolidator.model.CompanyInfoBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class CompaniesFileController {
    public HashMap<Integer, Company> readFile(LocalDate reportDate) throws FileNotFoundException {
        HashMap<Integer, Company> companiesMap = new HashMap<>();
        Path path = Paths.get("src/main/resources/input.csv");
        List<CompanyInfo> companyInfoList;
        try (Stream<String> stream = Files.lines(path)) {
            companyInfoList = stream.filter(s -> !(s.startsWith("ID")))
                    .map(s -> s.split(";"))
                    .map(CompanyInfoBuilder::build)
                    .filter(companyInfo -> companyInfo.getBeginDate().isBefore(reportDate)
                            && companyInfo.getEndDate().isAfter(reportDate))
                    .sorted(Comparator.comparingInt(CompanyInfo::getId))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        return companiesMap;
    }

//    private Company updateCompany(Company company, String[] row) {
//        company.getOwnership().put()
//    }
}
