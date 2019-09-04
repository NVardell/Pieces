package com.bits.pieces.other.interviews;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
    private static Map<String, Float> companyValue = new ConcurrentHashMap<>();

    public static void main (String... args) throws IOException {

        // Get list of paths to data files
        List<String> files = Files.walk(Paths.get("C:/Users/NV/Desktop/Data"))
            .map(Path::toString)
            .;

        // Process each data file
       for(String file:files) {
           processFile(file);
       }

        // Sort & Print Top 20 ListS
        stockAssets.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(20)
                .forEach(System.out::println);

        companyValue.entrySet()
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

            Long stockCount = stockAssets.getOrDefault(values[2], 0L);
            Float companyTrades = companyValue.getOrDefault(values[1], 0F);

            if(values[3].equals("SELL"))
                stockAssets.put(values[2], stockCount-Long.parseLong(values[4]));
            else
                stockAssets.put(values[2], stockCount+Long.parseLong(values[4]));

            companyValue.put(values[1], companyTrades+Float.parseFloat(values[5]));
        });
    }
}
