package com.bits.pieces.other.interviews;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/25/2019
 */
public class BoA {

    private static Map<String, Long> stockAssets = new ConcurrentHashMap<>();
    private static Map<String, Double> companyValue = new ConcurrentHashMap<>();

    public static void main (String... args) throws IOException {
        // Get list of paths to data files
        List<String> files = getRecordFilePaths("C:/Users/NV/Desktop/Data");

        // Print out list of file names
        System.out.println(files);

        // Process each data file
        processFile(files.get(0));

        // Print maps
        System.out.println(stockAssets);
        System.out.println(companyValue);

        // Sort things
        stockAssets.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(20)
                .forEach(System.out::println);
    }


    private static List<String> getRecordFilePaths(String dir) throws IOException {
        return (Files.walk(Paths.get(dir))).map(Path::toString)
                .filter(fileName -> fileName.contains("sample"))
                .collect(Collectors.toList());
    }

    private static void processFile(String s) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(s));
        lines.forEach(line -> {
            String[] values = line.split("\t");

            System.out.println("Date = " + values[0]);
            System.out.println("Company = " + values[1]);
            System.out.println("Stock = " + values[2]);
            System.out.println("Indicator = " + values[3]);
            System.out.println("Shares = " + values[4]);
            System.out.println("Price = " + values[5] + "\n\n");

            Long stockCount = stockAssets.getOrDefault(values[2], 0L);
            Double companyTrades = companyValue.getOrDefault(values[1], 0.00);

            if(values[3].equals("SELL"))
                stockAssets.put(values[2], stockCount-Long.parseLong(values[4]));
            else
                stockAssets.put(values[2], stockCount+Long.parseLong(values[4]));

            companyValue.put(values[1], companyTrades+Double.parseDouble(values[5]));
        });
    }
}
