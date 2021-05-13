package com.bits.pieces.topics.interviews;

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
 * @since 8/26/2019
 */
public class BoA_Clean {
    private static Map<String, Long> stockAssets = new ConcurrentHashMap<>();
    private static Map<String, Float> companyValue = new ConcurrentHashMap<>();

    public static void main (String... args) throws IOException {

        // Get list of paths to data files
        List<String> files = Files.walk(Paths.get("C:/Users/NV/Desktop/Data"))
                .map(Path::toString)
                .filter(path -> path.contains("sample"))
                .collect(Collectors.toList());

        // Process each data file
        for(String file:files) {
            Stream<String> lines = Files.lines(Paths.get(file));
            lines.forEach(line -> {
                String[] values = line.split("\t");

                Long stockCount = stockAssets.getOrDefault(values[2], 0L);
                Float companyTrades = companyValue.getOrDefault(values[1], 0F);

                Long recordStock = Long.parseLong(values[4]);
                Float recordTrade = Float.parseFloat(values[5]);

                if(values[3].equals("SELL"))
                    stockAssets.put(values[2], stockCount - recordStock);
                else
                    stockAssets.put(values[2], stockCount + recordStock);

                companyValue.put(values[1], companyTrades + recordTrade);
            });
        }

        // Sort & Print Top 20 Lists
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

}
