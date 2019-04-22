package io.codelex.quiz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.bouncycastle.est.LimitedSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//to check if tests are working u have to make methods non-static and public ....bumer

class GeneratePdfReportTest {
    GeneratePdfReport generatePdfReport = new GeneratePdfReport();

    @Test
    void should_return_the_correct_number_of_columns() {
        //given
        List<String> tableList = new ArrayList<>();
        tableList.add("```\n" +
                "+---------+-------+\n" +
                "| Andorra | 64000 |\n" +
                "+---------+-------+\n" +
                "| Nauru | 9900 |\n" +
                "+---------+-------+\n" +
                "```");
        tableList.add("| name        | region      | area    | population | gdp         |\n" +
                "|-------------|-------------|---------|------------|-------------|\n" +
                "| Afghanistan | South Asia  | 652225  | 26000000   |             |\n" +
                "| Albania     | Europe      | 28728   | 3200000    | 6656000000  |\n" +
                "| Algeria     | Middle East | 2400000 | 32900000   | 75012000000 |\n" +
                "| Andorra     | Europe      | 468     | 64000      |             |\n" +
                "| ...         |             |         |            |             |");
        tableList.add("```\n" +
                "+-------------+\n" +
                "| Afghanistan |\n" +
                "+-------------+\n" +
                "| Brazil\n" +
                "|\n" +
                "+-------------+\n" +
                "| Colombia |\n" +
                "+-------------+\n" +
                "```");
        tableList.add("| name        | population |\n" +
                "|-------------|------------|\n" +
                "| Bahrain     | 1234571    |\n" +
                "| Swaziland   | 1220000    |\n" +
                "| Timor-Leste | 1066409    |\n" +
                "| ...         |            |");
        
        //when
   //     int countForFirstTable = generatePdfReport.extractingColumnCount(tableList.get(0));
   //     int countForSecondTable = generatePdfReport.extractingColumnCount(tableList.get(1));
   //     int countForThirdTable = generatePdfReport.extractingColumnCount(tableList.get(2));
   //     int countForFourthTable = generatePdfReport.extractingColumnCount(tableList.get(3));
   //     //then
   //     Assertions.assertEquals(2,countForFirstTable);
   //     Assertions.assertEquals(5,countForSecondTable);
   //     Assertions.assertEquals(1,countForThirdTable);
   //     Assertions.assertEquals(2,countForFourthTable);
    }
}