package com.jap.sales;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesDataAnalyzer {
     int countLines = 0;
    // Read the data from the file and store in a List
    public List<SalesRecord> readFile(String fileName) {
        List<SalesRecord> salesRecordList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String header = bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                countLines++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("count - " + countLines);
        //SalesRecord[] salesRecords = new SalesRecord[countLines];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String date = data[0].trim();
                int customer_id = Integer.parseInt(data[1].trim());
                int product_category = Integer.parseInt(data[2].trim());
                String payment_method = data[3].trim();
                double amount = Double.parseDouble(data[4].trim());
                double time_on_site = Double.parseDouble(data[5].trim());
                int clicks_in_site = Integer.parseInt(data[6].trim());
                SalesRecord salesRecord = new SalesRecord(date, customer_id, product_category, payment_method, amount, time_on_site, clicks_in_site);
                salesRecordList.add(salesRecord);
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        return salesRecordList;
    }



    // Sort the customers based on purchase amount
    public List<SalesRecord> getAllCustomersSortedByPurchaseAmount(List<SalesRecord> salesData, AmountComparator amountComparator) {
        List<SalesRecord> salesRecordList = new ArrayList<>();
        Comparator<SalesRecord> salesRecordComparator = Comparator.comparingDouble(SalesRecord::getAmount).reversed();
        salesData.sort(salesRecordComparator);
        return salesData;
    }


    // Find the top customer who spent the maximum time on the site
    public SalesRecord getTopCustomerWhoSpentMaxTimeOnSite(List<SalesRecord> salesData, TimeOnSiteComparator timeOnSiteComparator) {
        SalesRecord customerWhoSpentMaxTime = new SalesRecord();
        Comparator<SalesRecord> time = Comparator.comparingDouble(SalesRecord::getTime_on_site).reversed();
        salesData.sort((time));
        Iterator<SalesRecord> iterator = salesData.iterator();
        return iterator.next();
    }


}
