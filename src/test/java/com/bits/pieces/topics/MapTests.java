package com.bits.pieces.topics;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/20/2019
 */
@Slf4j
public class MapTests {

    @Test
    public void testingMaps() {
        List<String> aList = Arrays.asList("Apple", "Abacus");
        List<String> bList = Arrays.asList("Bus", "Blue");

        Map<String, List<String>> words = new HashMap<>();
        words.put("A", aList);
        words.put("B", bList);

        List<String> wordsForGivenAlphabet = words.entrySet().stream()
                .filter(x-> x.getKey().equalsIgnoreCase("B"))
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat(wordsForGivenAlphabet.size(), is(2));
        assertThat(wordsForGivenAlphabet, containsInAnyOrder("Bus", "Blue"));
    }

    @Test
    public void mapSomeKeysAndFilterBySomeValues() {
        Map<Integer, String> hmap = new HashMap<>();
        hmap.put(11, "Apple"); hmap.put(22, "Orange"); hmap.put(33, "Kiwi"); hmap.put(44, "Banana");

        // Filter by key and values
        Map<Integer, String> result = hmap.entrySet().stream()
                .filter(p -> p.getKey() <= 2) //filter by key
                .filter(map -> map.getValue().startsWith("A")) //filter by value
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Result: " + result); // OUTPUT: Result: {1=ABC}


        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple"); map.put(20, "orange"); map.put(30, "banana"); map.put(40, "watermelon"); map.put(50, "dragonfruit");

        // split a map into 2 List
        List<Integer> resultSortedKey = new ArrayList<>();
        List<String> resultValues = map.entrySet().stream()
                //sort a Map by key and stored in resultSortedKey
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .peek(e -> resultSortedKey.add(e.getKey()))
                .map(Map.Entry::getValue)
                // filter banana and return it to resultValues
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());
        resultSortedKey.forEach(System.out::println);
        resultValues.forEach(System.out::println);
    }


    @Test
    public void mappingStreams(){
        Map<String, String> books = new HashMap<>();
        books.put("978-0201633610", "Design patterns : elements of reusable object-oriented software");
        books.put("978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
        books.put("978-0134685991", "Effective Java");

        List<String> isbnCodes = books.entrySet().stream()
                .filter(e -> e.getValue().startsWith("Effective Java"))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        assertThat(isbnCodes, containsInAnyOrder("978-0134685991"));

        List<String> titles = books.entrySet().stream()
                .filter(e -> e.getKey().startsWith("978-0"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        assertThat(titles.size(), is(2));
        assertThat(titles, containsInAnyOrder("Design patterns : elements of reusable object-oriented software", "Effective Java"));
    }
}
