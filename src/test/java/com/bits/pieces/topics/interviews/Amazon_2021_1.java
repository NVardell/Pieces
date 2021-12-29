package com.bits.pieces.topics.interviews;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 2021 ~ Q2
 *
 * @author Nate Vardell
 * @since 8/24/2019
 */
public class Amazon_2021_1 {

    public static List<List<String>> searchSuggestions(List<String> repository, String customerQuery) {

        // List of Search Suggestions to return
        List<List<String>> searchSuggestions = new ArrayList<>();

        // Iterate over characters in customer search query
        for(int i = 0; i < customerQuery.length(); i++) {
            // Get character from query string
            String customerQueryChar = customerQuery.substring(0, i+1).toLowerCase();

            // Create a list of possible terms
            List<String> repoSuggestionsList = repository.stream()
                    .map(String::toLowerCase)
                    .filter(suggestion -> suggestion.startsWith(customerQueryChar))
                    .sorted()
                    .limit(3)
                    .collect(toList());

            // Add new list of repo search suggestions to primary list
            searchSuggestions.add(repoSuggestionsList);
        }

        // Remove initial duplicate entry - NTS: Fixed all test cases after adding. Don't ask.
        searchSuggestions.remove(0);

        // Return List of Search Suggestions
        return searchSuggestions;
    }

    @Test
    public void testSearch() {
        List<String> input = Arrays.asList("bags", "baggage", "banner", "box", "cloths");
        assertThat(searchSuggestions(input, "customer").size(), is(4));

    }

//    public class Solution {
//        public static void main(String[] args) throws IOException {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//            int repositoryCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//            List<String> repository = IntStream.range(0, repositoryCount).mapToObj(i -> {
//                        try {
//                            return bufferedReader.readLine();
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    })
//                    .collect(toList());
//
//            String customerQuery = bufferedReader.readLine();
//
//            List<List<String>> result = Result.searchSuggestions(repository, customerQuery);
//
//            result.stream()
//                    .map(
//                            r -> r.stream()
//                                    .collect(joining(" "))
//                    )
//                    .map(r -> r + "\n")
//                    .collect(toList())
//                    .forEach(e -> {
//                        try {
//                            bufferedWriter.write(e);
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    });
//
//            bufferedReader.close();
//            bufferedWriter.close();
//        }
//    }
}
