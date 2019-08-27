package com.bits.pieces.other.streams;

import com.bits.pieces.app.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/29/2019
 */
public class Streams {

    @Test
    public void testStreamReduction() {
        List<String> items = Arrays.asList("orange", "on", "OMG");
        String reduced = items.stream()
                .filter( item -> item.startsWith("o"))
                .reduce("", (acc, item) -> acc + " " + item);
        System.out.println("Reduced = " + reduced); // Reduced =  orange on
    }

    @Test
    public void streamLists(){
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        List<String> result = lines.stream()                // convert list to stream
                .filter(line -> !"mkyong".equals(line))     // we dont like mkyong
                .collect(Collectors.toList());              // collect the output and convert streams to a List
        result.forEach(System.out::println);                // output : spring, node
    }

    @Test
    public void streamFilterAndFind() {
        List<Person> persons = Arrays.asList(new Person(30, "mkyong"), new Person(20, "jack"), new Person(40, "lawrence"));

        Person result1 = persons.stream()                 // Convert to steam
                .filter(x -> "jack".equals(x.getName()))  // we want "jack" only
                .findAny()                                // If 'findAny' then return found
                .orElse(null);                      // If not found, return null
        assertEquals(persons.get(1), result1);

        Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);
        assertNull(result2);

        Person result3 = persons.stream()
                .filter((p) -> "jack".equals(p.getName()) && 20 == p.getDigits())
                .findAny()
                .orElse(null);
        System.out.println("result 3 :" + result3);
        assertEquals(persons.get(1), result3);

        //or like this
        Person result4 = persons.stream()
                .filter(p -> {
                    if ("jack".equals(p.getName()) && 20 == p.getDigits()) {
                        return true;
                    }
                    return false;
                }).findAny()
                .orElse(null);
        System.out.println("result 4 :" + result4);

        String name = persons.stream()
                .filter(x -> "jack".equals(x.getName()))
                .map(Person::getName)                        // convert stream to String
                .findAny()
                .orElse("");
        System.out.println("name : " + name);

        System.out.println("Hopefully winning.....");
        List<String> collect = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void filterIsALazyStream() {
        List<Integer> list = Arrays.asList(1, 10, 3, 7, 5);
        int a = list.stream()
                .peek(num -> System.out.println("will filter " + num))
                .filter(x -> x > 5)
                .findFirst()
                .get();
        System.out.println(a);
        // OUTPUT
        // will filter 1
        // will filter 10
        // 10
    }

    @Test
    public void mapAndFilter(){
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        System.out.println("original list: " + numbers);
        List<Integer> even = numbers.stream()
                .map(s -> Integer.valueOf(s))
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("processed list, only even numbers: " + even);
        // OUTPUT
        // original list: [1, 2, 3, 4, 5, 6]
        // the processed list, only even numbers: [2, 4, 6]
    }

    @Test
    public void filterFindFirstAndForEach() {
        List<Person> list = getPeople();
        System.out.println("Using findFirst() ---");
        Person person = list.stream().filter(u -> u.getName().endsWith("sh"))
                .findFirst().orElse(null);
        System.out.println(person.getName());
        System.out.println("Using forEach() ---");
        list.stream().filter(u -> u.getName().endsWith("sh"))
                .forEach(u -> System.out.println(u.getName()));
    }

    @Test
    public void filterAndCollect() {
        List<Person> list = getPeople();
        long count = list.stream()
                .filter(u -> u.getName().endsWith("sh"))
                .count();
        System.out.println("Number of persons ending name with 'sh': "+ count); // OUTPUT: Number of persons ending name with 'sh': 3
    }

    @Test
    public void filterListOfInteger() {
        List<Integer> list  = Arrays.asList(3, 12, 23, 44, 20, 10, 17, 8);

        System.out.println("---List with even Numbers---");
        List<Integer> evenList = list.stream().filter(i -> i%2 == 0).collect(Collectors.toList());
        evenList.forEach(s -> System.out.print(s+" ")); // OUTPUT: 12 44 20 10 8

        System.out.println("\n---List with odd Numbers---");
        List<Integer> oddList = list.stream().filter(i -> i%2 == 1).collect(Collectors.toList());
        oddList.forEach(s -> System.out.print(s+" ")); // OUTPUT: 3 23 17
    }

    @Test
    public void filterLongNames() {
        List<String> names = Arrays.asList("Melisandre","Sansa","Jon","Daenerys","Joffery");

        //Creating the stream of all names
        Stream<String> allNames = names.stream();

        //Creating another stream by filtering long names using filter()
        Stream<String> longNames = allNames.filter(str -> str.length() > 6);

        //displaying the long names
        longNames.forEach(str->System.out.print(str+", ")); // OUTPUT: Melisandre, Daenerys, Joffery


        List<String> longnames = names.stream()   // converting the list to stream
                .filter(str -> str.length() > 6)  // filter the stream to create a new stream
                .collect(Collectors.toList());    // collect the final stream and convert it to a List
        longnames.forEach(System.out::println);   // OUTPUT Melisandre Daenerys Joffery


        longnames = names.stream()
                .filter(str -> str.length() > 6 && str.length() < 8) //Multiple conditions
                .collect(Collectors.toList());
        longnames.forEach(System.out::println);   // OUTPUT Joffery

        longnames = names.stream()
                .filter(str -> str.length() > 6) //Multiple conditions
                .filter(str -> str.length() < 8) //Multiple conditions
                .collect(Collectors.toList());
        longnames.forEach(System.out::println);   // OUTPUT Joffery
    }

    @Test
    public void mapCollections() {
        List<Integer> num = Arrays.asList(1,2,3,4,5,6);
        List<Integer> squares = num.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(squares); // OUTPUT: [1, 4, 9, 16, 25, 36]
    }

    @Test
    public void mapSomeKeysAndFilterBySomeValues() {
        Map<Integer, String> hmap = new HashMap<>();
        hmap.put(11, "Apple"); hmap.put(22, "Orange");
        hmap.put(33, "Kiwi");  hmap.put(44, "Banana");

        // Filter by keys
        Map<Integer, String> result = hmap.entrySet()
                .stream()
                .filter(map -> map.getKey().intValue() <= 22)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        System.out.println("Result: " + result); // OUTPUT: Result: {22=Orange, 11=Apple}

        // Filter by values
        result = hmap.entrySet()
                .stream()
                .filter(map -> "Orange".equals(map.getValue()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        System.out.println("Result: " + result); // OUTPUT: Result: {22=Orange}

        // Filter by key and values
        result = hmap.entrySet()
                .stream()
                .filter(p -> p.getKey().intValue() <= 2) //filter by key
                .filter(map -> map.getValue().startsWith("A")) //filter by value
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        System.out.println("Result: " + result); // OUTPUT: Result: {1=ABC}
    }

    @Test
    public void findingSpecificElementInList() {
        List<Person> personList = getPeople();
        Person person = personList.stream().filter(u ->
                u.getName().contains("Vi"))
                .findFirst()
                .orElse(null);
        System.out.println("Person with specific name - " + person);
    }

    private static List<Person> getPeople() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(20, "Dinesh"));
        list.add(new Person(15, "Kamal"));
        list.add(new Person(25, "Vijay"));
        list.add(new Person(30, "Ramesh"));
        list.add(new Person(18, "Mahesh"));
        return list;
    }
}
