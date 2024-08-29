package com.SchoolDepartmentManagement;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KeywordSearchScript {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();


        List<String> keywords = readKeywordsFromExcel("4BeatsQ1.xlsx");


        if (dayOfWeek == DayOfWeek.MONDAY) {
            for (String keyword : keywords) {

                String[] searchResults = performGoogleSearch(keyword);


                String longest = findLongestOption(searchResults);
                String shortest = findShortestOption(searchResults);


                System.out.println("Keyword: " + keyword);
                System.out.println("Longest Option: " + longest);
                System.out.println("Shortest Option: " + shortest);
            }
        }
    }

    private static List<String> readKeywordsFromExcel(String filePath) {
        List<String> keywords = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String keyword = row.getCell(0).getStringCellValue();
                keywords.add(keyword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keywords;
    }

    private static String[] performGoogleSearch(String keyword) {
    return new String[]{
                "Short option for " + keyword,
                "A much longer option for " + keyword + " that extends quite a bit"
        };
    }

    private static String findLongestOption(String[] options) {
        String longest = "";
        for (String option : options) {
            if (option.length() > longest.length()) {
                longest = option;
            }
        }
        return longest;
    }

    private static String findShortestOption(String[] options) {
        String shortest = options[0];
        for (String option : options) {
            if (option.length() < shortest.length()) {
                shortest = option;
            }
        }
        return shortest;
    }
}
