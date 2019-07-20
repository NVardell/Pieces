package com.bits.pieces.model;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/29/2019
 */
public class PersonTest {

    Log log = LogFactory.getLog(PersonTest.class);
    Stream<Employee> employeeStream;
    List<Employee> employeeList;
    List<Person> people = asList(new Person(118, "Joe"), new Person(10, "Jim"), new Person(18, "John"));

    @Test
    public void setLastNames(){
        people.forEach(p -> p.setName("Doe"));
    }

    @Test
    public void mapOperations() {

        // The map operations allows us to apply a function (http://javadocs.techempower.com/jdk18/api/java/util/function/Function.html)
        // that takes in a parameter of one type, and returns something else.

        // One way....
        employeeStream = people.stream()
                .filter(p -> p.getDigits() > 18)
                .map(person -> new Employee(person));

        // Another way...
        // Since the lambda that is passed to the map method just consumes the parameter without doing anything else with it,
        // then we can transform it further to a method reference:
        employeeStream = people.stream()
                .filter(p -> p.getDigits() > 18)
                .map(Employee::new);
        System.out.println(employeeStream.toString());
    }

    @Test
    public void convertStreamToList() {
        employeeList = people.stream()
                .filter(p -> p.getDigits() > 18)
                .map(Employee::new)
                .collect(Collectors.toCollection(ArrayList::new));
        assertThat(employeeList.size(), is(1));
    }

    @Test
    public void testRandomFilters() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        Optional<String> stream = list.stream().filter(element -> {
            log.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            log.info("map() was called");
            return element.toUpperCase();
        }).findFirst();
        assertThat(stream.get(), is("ABC2"));
    }
}
