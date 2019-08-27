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
        Instant start = Instant.now();

        // Get list of paths to data files
        List<String> files = getRecordFilePaths("C:/Users/NV/Desktop/Data");

        // Process each data file
//        for(String file:files) {
//            processFile(file);
//        }
        files.parallelStream().forEach(file -> {
//            Stream<String> lines = null;
//            try {
//                lines = Files.lines(Paths.get(file));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            lines.forEach(line -> {
//                lines.parallel().forEach(line -> {
//                String[] values = line.split("\t");
//
//                Long stockCount = stockAssets.getOrDefault(values[2], 0L);
//                Float companyTrades = companyValue.getOrDefault(values[1], 0F);
//
//                if(values[3].equals("SELL"))
//                    stockAssets.put(values[2], stockCount-Long.parseLong(values[4]));
//                else
//                    stockAssets.put(values[2], stockCount+Long.parseLong(values[4]));
//
//                companyValue.put(values[1], companyTrades+Float.parseFloat(values[5]));
//            });
            try {
                processFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Instant finish = Instant.now();
        System.out.println("\n\nMILLI-SECONDS ELAPSED = " + Duration.between(start, finish).toMillis());
        System.out.println("NANO-SECONDS ELAPSED = " + Duration.between(start, finish).toNanos());
        System.out.println("Millis Elapsed = " + ChronoUnit.MILLIS.between(start, finish));
        System.out.println("Macros Elapsed = " + ChronoUnit.MICROS.between(start, finish));
        System.out.println("Nanos Elapsed = " + ChronoUnit.NANOS.between(start, finish) + "\n\n");
        System.out.println("SORTING NOW");

        // Sort & Print Top 20 ListS
        System.out.println("\nTop Stocks:");
        stockAssets.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(20)
                .forEach(System.out::println);

        System.out.println("\nTop Companies:");

        companyValue.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(20)
                .forEach(System.out::println);

        finish = Instant.now();
        System.out.println("\n\nMILLI-SECONDS ELAPSED = " + Duration.between(start, finish).toMillis());
        System.out.println("NANO-SECONDS ELAPSED = " + Duration.between(start, finish).toNanos());
        System.out.println("Millis Elapsed = " + ChronoUnit.MILLIS.between(start, finish));
        System.out.println("Macros Elapsed = " + ChronoUnit.MICROS.between(start, finish));
        System.out.println("Nanos Elapsed = " + ChronoUnit.NANOS.between(start, finish) + "\n\n");
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
