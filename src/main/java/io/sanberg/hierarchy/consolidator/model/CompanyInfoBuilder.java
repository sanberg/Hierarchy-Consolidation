package io.sanberg.hierarchy.consolidator.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The builder for Company info.
 */
public class CompanyInfoBuilder implements CSVInfoBuilder {
    /**
     * Build company info from csv line
     *
     * @param row the row
     * @return the company info
     */
    public HierarchyNodeInfo build(String[] row) {
        if (row.length != 5) {
            throw new IllegalArgumentException("row columns count is incorrect");
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DecimalFormat format = new DecimalFormat("#,##");
        format.setParseBigDecimal(true);

        try {
            return new CompanyInfo(Integer.parseInt(row[0]),
                    LocalDate.parse(row[1], dtf),
                    LocalDate.parse(row[2], dtf),
                    (BigDecimal) format.parse(row[3]),
                    Integer.parseInt(row[4])
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
