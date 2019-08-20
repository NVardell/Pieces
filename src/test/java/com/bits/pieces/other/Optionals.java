package com.bits.pieces.other;

import com.bits.pieces.app.model.Employee;
import com.bits.pieces.app.model.Person;
import com.bits.pieces.app.model.Team;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/29/2019
 */
public class Optionals {

    private static final List<Optional<String>> listOfOptionals = Arrays.asList(Optional.empty(), Optional.of("foo"), Optional.empty(), Optional.of("bar"));

    @Test
    public void whenOptionalFilterWorks_thenCorrect() {
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        assertTrue(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        assertFalse(is2017);
    }

    @Test
    public void useFilter() {
        List<String> filteredList = listOfOptionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println(filteredList);
        assertThat(filteredList.size(), is(2));
    }

    @Test
    public void useFlatMap() {
        List<String> filteredList = listOfOptionals.stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
        assertThat(filteredList.size(), is(2));
    }

    @Test
    public void orElse_whenNamePresent_ThenName(){
        Optional<String> petName = Optional.of("Bobby");
        assertEquals("Bobby", petName.orElse(""));
    }

    @Test
    public void orElse_whenNameNotPresent_ThenEmptyString(){
        Optional<String> petName = Optional.empty();
        assertEquals("", petName.orElse(""));
    }

    @Test
    public void elseOrThrow_whenNamePresent_ThenName(){
        Optional<String> petName = Optional.of("Bobby");
        assertEquals("Bobby", petName.orElse(""));
    }

    @Test(expected=IllegalArgumentException.class)
    public void elseOrThrow_whenNameNotPresent_ThenIllegalArgEx(){
        Optional<String> petName = Optional.empty();
        petName.orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void filter_whenNameNotEmpty_thenName(){
        Optional<String> petNameOpt = Optional.of("Bobby");
        String petName = petNameOpt.filter(name -> !name.trim().isEmpty()).orElseThrow(IllegalArgumentException::new);
        assertEquals("Bobby", petName);
    }


    @Test
    public void ifPresent_whenPersonPresent_thenPointsAdded(){
        Person mockedPerson = mock(Person.class);
        Optional<Person> loyaltyPerson = Optional.of(mockedPerson);
        loyaltyPerson.ifPresent(c -> c.addDigits(3));
        verify(mockedPerson, times(1)).addDigits(3); //Verify addDigits method has been called 1 time and with input=3
    }
    @Test
    public void map_whenPersonPresent_thenNumber(){
        Person mockedPerson = mock(Person.class);
        when(mockedPerson.getDigits()).thenReturn(3);
        Optional<Person> card = Optional.of(mockedPerson);
        int point = card.map(Person::getDigits).orElse(0);
        assertEquals(3, point);
    }
    @Test
    public void map_whenPersonNotPresent_thenZero(){
        Optional<Person> card = Optional.empty();
        int point = card.map(Person::getDigits).orElse(0);
        assertEquals(0, point);
    }
    @Test
    public void whenFiltersWithoutOptional_thenCorrect() {
        assertTrue(priceIsInRange1(new Employee(10.0)));
        assertFalse(priceIsInRange1(new Employee(9.9)));
        assertFalse(priceIsInRange1(new Employee(null)));
        assertFalse(priceIsInRange1(new Employee(15.5)));
        assertFalse(priceIsInRange1(null));
    }
    @Test
    public void whenFiltersWithOptional_thenCorrect() {
        assertTrue(priceIsInRange2(new Employee(10.0)));
        assertFalse(priceIsInRange2(new Employee(9.9)));
        assertFalse(priceIsInRange2(new Employee(null)));
        assertFalse(priceIsInRange2(new Employee(15.5)));
        assertFalse(priceIsInRange2(null));
    }
    @Test
    public void bikes_emptyOptionals() throws Exception {
        // Populate Team with Optional<Person>
        Team colnagoTeam = new Team("colnago", Optional.of(new Person(32, "mavic")));

        // Dont do this - use a Optional.ofNullable
        Team nullTeam = new Team("nowheels", null);

        // Use a Optional.ofNullable
        Team ofNullableTeam = new Team("nowheels", Optional.ofNullable(null));

        // Empty Optional - empty container with no object
        Optional<Team> optionalEmptyTeam = Optional.empty();

        // call get() on empty object throws NoSuchElementException
        try { Team emptyTeam = optionalEmptyTeam.get(); }
        catch (java.util.NoSuchElementException e) { System.out.println("get() on empty Optional throws java.util.NoSuchElementException " + e.getMessage()); }

        // isPresent - check if object is empty - but not much advantage over != null checks
        if (!optionalEmptyTeam.isPresent())
            System.out.println("isPresent() - ok - but not much improvement on != null");

        // Better Alternatives -
        // orElse - returns a default object if none set
        Team orElseTeam = optionalEmptyTeam.orElse(colnagoTeam);
        System.out.println("orElse - Optional is empty so return colnagoTeam " + orElseTeam.getName());

        // ifPresent(Consumer<? extends Team>) - this prints nothing as Optional is empty
        optionalEmptyTeam.ifPresent(bike -> System.out.println("ifPresent(Consumer) returns " + bike.getName()));

        // orElseThrow - Throw Exception
        try { Team orElseThrowTeam = optionalEmptyTeam.orElseThrow(Exception::new);
        } catch (Exception nbe) { System.out.println("orElseThrow NoTeamException");
        }
    }
    @Test
    public void bikes_nullObjectsInOptionals() {
        // Populate Team with Optional<Person>
        Team colnagoTeam = new Team("colnago", Optional.of(new Person(32, "mavic")));

        // Dont do this - use a Optional.ofNullable
        Team nullTeam = new Team("nowheels", null);

        // Use a Optional.ofNullable
        Team ofNullableTeam = new Team("nowheels", Optional.ofNullable(null));

        // of - Populate with null object - Throws Exception
        try { Optional<Team> optionalNullTeam = Optional.of(null); }
        catch (java.lang.NullPointerException nfe) { System.out.println("Cant call Optional.of(null) - " + nfe.getMessage()); }

        // ofNullable - allows null
        System.out.println("We can pass a null with ofNullable");
        Optional<Team> optionalOfNullableTeam = Optional.ofNullable(null);
        System.out.println("optionalOfNullableTeam.isPresent() returns " + optionalOfNullableTeam.isPresent());
    }
    @Test
    public void bikes_valuesInOptionals() {
        // Populate Team with Optional<Person>
        Team colnagoTeam = new Team("colnago", Optional.of(new Person(32, "mavic")));

        // Dont do this - use a Optional.ofNullable
        Team nullTeam = new Team("nowheels", null);

        // Use a Optional.ofNullable
        Team ofNullableTeam = new Team("nowheels", Optional.ofNullable(null));

        // Populated Optional - now begin populating with Object
        Optional<Team> optionalColnagoTeam = Optional.of(colnagoTeam);
        if (optionalColnagoTeam.isPresent()) {
            System.out.println("isPresent() - ok - but not much improvement on != null");
        }

        optionalColnagoTeam.ifPresent(bike -> System.out.println("ifPresent(Consumer) returns " + bike.getName()));

        // Filtering and mapping in combination with Lambdas
        Team orElseThrowTeam = optionalColnagoTeam.filter(b -> "colnago".equals(b.getName())).get();

        Optional<String> brand = optionalColnagoTeam.map(b -> b.getName());
        brand.ifPresent(b -> System.out.println("brand " + b));

        // flatMap to prevent Optional<Optional<Person>>
        Optional<Person> wheels = optionalColnagoTeam.flatMap(b -> b.getTeamMembers());
        wheels.ifPresent(w -> System.out.println("flatMap - Wheel Brand " + w.getName()));
    }
    @Test
    public void givenOptional_whenMapWorks_thenCorrect() {
        List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional.map(List::size).orElse(0);
        assertEquals(6, size);
    }
    @Test
    public void givenOptional_whenMapWorks_thenCorrect2() {
        String name = "baeldung";
        Optional<String> nameOptional = Optional.of(name);

        int len = nameOptional.map(String::length).orElse(0);
        assertEquals(8, len);
    }
    @Test
    public void givenOptional_whenMapWorksWithFilter_thenCorrect() {
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);

        boolean correctPassword = passOpt.filter(pass -> pass.equals("password")).isPresent();
        assertFalse(correctPassword);

        correctPassword = passOpt.map(String::trim).filter(pass -> pass.equals("password")).isPresent();
        assertTrue(correctPassword);
    }


//
//    @Test
//    public void testStreamingEmployees() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//
//        // obtain a stream from an existing array
//        Stream.of(arrayOfEmps);
//        //  We can also obtain a stream from an existing list:
//        empList.stream();
//        // Note that Java 8 added a new stream() method to the Collection interface.
//        // And we can create a stream from individual objects using Stream.of():
//        Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
//        // Or simply using Stream.builder():
//        Stream.Builder<Employee> empStreamBuilder = Stream.builder();
//        empStreamBuilder.accept(arrayOfEmps[0]);
//        empStreamBuilder.accept(arrayOfEmps[1]);
//        empStreamBuilder.accept(arrayOfEmps[2]);
//        Stream<Employee> empStream = empStreamBuilder.build();
//    }

//    @Test
//    public void whenIncrementSalaryForEachEmployee_thenApplyNewSalary() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        empList.forEach(e -> e.salaryIncrement(10.0));
//        assertThat(empList, containsInAnyOrder(
//                hasProperty("money", is(110000.0)),
//                hasProperty("money", is(220000.0)),
//                hasProperty("money", is(330000.0))
//        ));
//    }

//    @Test
//    public void whenMapIdToEmployees_thenGetEmployeeStream() {
//        Integer[] empIds = { 1, 2, 3 };
//
//        List<Employee> employees = Stream.of(empIds)
//                .map(this::findById)
//                .collect(Collectors.toList());
//
//        assertEquals(employees.size(), empIds.length);
//    }

//    @Test
//    public void whenStreamToArray_thenGetArray() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Employee[] employees = empList.toArray(new Employee[0]);
//        assertThat(empList.toArray(), equalTo(employees));
//    }

    @Test
    public void whenFlatMapEmployeeNames_thenGetNameStream() {
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg"));

        List<String> namesFlatStream = namesNested.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals(namesFlatStream.size(), namesNested.size() * 2);
    }

//    @Test
//    public void whenIncrementSalaryUsingPeek_thenApplyNewSalary() {
//        Employee[] arrayOfEmps
//
//        List<Employee> empList = Arrays.asList(arrayOfEmps);

//        empList.stream()
//                .peek(e -> e.salaryIncrement(10.0))
//                .peek(System.out::println)
//                .collect(Collectors.toList());
//
//        assertThat(empList, containsInAnyOrder(
//                hasProperty("money", is(110000.0)),
//                hasProperty("money", is(220000.0)),
//                hasProperty("money", is(330000.0))
//        ));
//    }

//    @Test
//    public void whenStreamCount_thenGetElementCount() {
////        Employee[] arrayOfEmps
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Long empCount = empList.stream()
//                .filter(e -> e.getMoney() > 200000)
//                .count();
//
//        assertEquals(empCount, new Long(1));
//    }

    @Test
    public void whenLimitInfiniteStream_thenGetFiniteElements() {
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

        List<Integer> collect = infiniteStream
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());

        assertEquals(collect, Arrays.asList(16, 32, 64, 128, 256));
    }

//    @Test
//    public void whenSortStream_thenGetSortedStream() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        List<Employee> employees = empList.stream()
//                .sorted(Comparator.comparing(Employee::getName))
//                .collect(Collectors.toList());
//
//        assertEquals(employees.get(0).getName(), "Bill Gates");
//        assertEquals(employees.get(1).getName(), "Jeff Bezos");
//        assertEquals(employees.get(2).getName(), "Mark Zuckerberg");
//    }

//    @Test
//    public void whenFindMin_thenGetMinElementFromStream() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Employee firstEmp = empList.stream()
//                .min(Comparator.comparingLong(Employee::getId))
//                .orElseThrow(NoSuchElementException::new);
//
//        assertThat(firstEmp.getId(), is(1));
//    }

//    @Test
//    public void whenFindMax_thenGetMaxElementFromStream() {
////        Employee[] arrayOfEmps
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Employee maxSalEmp = empList.stream()
//                .max(Comparator.comparing(Employee::getMoney))
//                .orElseThrow(NoSuchElementException::new);
//
//        assertThat(maxSalEmp.getMoney(), is(300000.0));
//    }

    @Test
    public void whenApplyDistinct_thenRemoveDuplicatesFromStream() {
        List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3);
        List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
        assertEquals(distinctIntList, Arrays.asList(2, 5, 3, 4));
    }

    @Test
    public void whenApplyMatch_thenReturnBoolean() {
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);

        boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
        boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
        boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);

        assertFalse(allEven);
        assertTrue(oneEven);
        assertFalse(noneMultipleOfThree);
    }

//    @Test
//    public void whenFindMaxOnIntStream_thenGetMaxInteger() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Integer latestEmpId = empList.stream()
//                .mapToInt(Employee::getId)
//                .max()
//                .orElseThrow(NoSuchElementException::new);
//
//        assertEquals(latestEmpId, new Integer(3));
//    }

//    @Test
//    public void makeSomeIntStreams() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        // We can also use IntStream.of() for creating the IntStream:
//        IntStream.of(1, 2, 3);
//        // or IntStream.range(): which creates IntStream of numbers 10 to 19.
//        IntStream.range(10, 20);
//        // One important distinction to note before we move on to the next topic:
//        // This returns a Stream<Integer> and not IntStream.
//        Stream.of(1, 2, 3);
//        // Similarly, using map() instead of mapToInt() returns a Stream<Integer> and not an IntStream.:
//        empList.stream().map(Employee::getId);
//    }

//    @Test
//    public void whenCollectByJoining_thenGetJoinedString() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        String empNames = empList.stream()
//                .map(Employee::getName)
//                .collect(Collectors.joining(", "))
//                .toString();
//
//        assertEquals(empNames, "Jeff Bezos, Bill Gates, Mark Zuckerberg");
//    }

//    @Test
//    public void whenCollectBySet_thenGetSet() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Set<String> empNames = empList.stream()
//                .map(Employee::getName)
//                .collect(Collectors.toSet());
//
//        assertEquals(empNames.size(), 3);
//    }

//    @Test
//    public void whenToVectorCollection_thenGetVector() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Vector<String> empNames = empList.stream()
//                .map(Employee::getName)
//                .collect(Collectors.toCollection(Vector::new));
//
//        assertEquals(empNames.size(), 3);
//    }

//    @Test
//    public void whenApplySummarizing_thenGetBasicStats() {
////        Employee[] arrayOfEmps
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        DoubleSummaryStatistics stats = empList.stream()
//                .collect(Collectors.summarizingDouble(Employee::getMoney));
//
//        assertEquals(stats.getCount(), 3);
//        assertEquals(stats.getSum(), 600000.0, 0);
//        assertEquals(stats.getMin(), 100000.0, 0);
//        assertEquals(stats.getMax(), 300000.0, 0);
//        assertEquals(stats.getAverage(), 200000.0, 0);
//    }

//    @Test
//    public void whenApplySummaryStatistics_thenGetBasicStats() {
////        Employee[] arrayOfEmps
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        DoubleSummaryStatistics stats = empList.stream()
//                .mapToDouble(Employee::getMoney)
//                .summaryStatistics();
//
//        assertEquals(stats.getCount(), 3);
//        assertEquals(stats.getSum(), 600000.0, 0);
//        assertEquals(stats.getMin(), 100000.0, 0);
//        assertEquals(stats.getMax(), 300000.0, 0);
//        assertEquals(stats.getAverage(), 200000.0, 0);
//    }

    @Test
    public void whenStreamPartition_thenGetMap() {
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        Map<Boolean, List<Integer>> isEven = intList.stream().collect(
                Collectors.partitioningBy(i -> i % 2 == 0));

        assertEquals(isEven.get(true).size(),4);
        assertEquals(isEven.get(false).size(),1);
    }

//    @Test
//    public void whenStreamGroupingBy_thenGetMap() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Map<Character, List<Employee>> groupByAlphabet = empList.stream().collect(
//                Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));
//
//        assertEquals(groupByAlphabet.get('B').get(0).getName(), "Bill Gates");
//        assertEquals(groupByAlphabet.get('J').get(0).getName(), "Jeff Bezos");
//        assertEquals(groupByAlphabet.get('M').get(0).getName(), "Mark Zuckerberg");
//    }
//
//    @Test
//    public void whenStreamMapping_thenGetMap() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Map<Character, List<Integer>> idGroupedByAlphabet = empList.stream().collect(
//                Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
//                        Collectors.mapping(Employee::getId, Collectors.toList())));
//
//        assertEquals(idGroupedByAlphabet.get('B').get(0), new Integer(2));
//        assertEquals(idGroupedByAlphabet.get('J').get(0), new Integer(1));
//        assertEquals(idGroupedByAlphabet.get('M').get(0), new Integer(3));
//    }

//    @Test
//    public void whenStreamReducing_thenGetValue() {
////        Employee[] arrayOfEmps
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Double percentage = 10.0;
//        Double salIncrOverhead = empList.stream().collect(Collectors.reducing(
//                0.0, e -> e.getMoney() * percentage / 100, (s1, s2) -> s1 + s2));
//
//        assertEquals(salIncrOverhead, 60000.0, 0);
//    }

//    @Test
//    public void whenStreamGroupingAndReducing_thenGetMap() {
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//        Comparator<Employee> byNameLength = Comparator.comparing(Employee::getName);
//
//        Map<Character, Optional<Employee>> longestNameByAlphabet = empList.stream().collect(
//                Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
//                        Collectors.reducing(BinaryOperator.maxBy(byNameLength))));
//
//        assertEquals(longestNameByAlphabet.get('B').get().getName(), "Bill Gates");
//        assertEquals(longestNameByAlphabet.get('J').get().getName(), "Jeff Bezos");
//        assertEquals(longestNameByAlphabet.get('M').get().getName(), "Mark Zuckerberg");
//    }

//    @Test
//    public void whenParallelStream_thenPerformOperationsInParallel() {
////        Employee[] arrayOfEmps
//
//        List<Employee> empList = Arrays.asList(arrayOfEmps);
//
//        empList.stream().parallel().forEach(e -> e.salaryIncrement(10.0));
//
//        assertThat(empList, containsInAnyOrder(
//                hasProperty("money", equalTo(110000.0)),
//                hasProperty("money", equalTo(220000.0)),
//                hasProperty("money", equalTo(330000.0))
//        ));
//
//
//    }

    @Test
    public void whenGenerateStream_thenGetInfiniteStream() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void whenIterateStream_thenGetInfiniteStream() {
        Stream<Integer> evenNumStream = Stream.iterate(2, i -> i * 2);

        List<Integer> collect = evenNumStream
                .limit(5)
                .collect(Collectors.toList());

        assertEquals(collect, Arrays.asList(2, 4, 8, 16, 32));
    }




    @Test
    public void givenOptional_whenIsPresentWorks_thenCorrect() {
        Optional<String> opt = Optional.of("Baeldung");
        assertTrue(opt.isPresent());

        opt = Optional.ofNullable(null);
        assertFalse(opt.isPresent());
    }
    @Test
    public void givenOptional_whenIfPresentWorks_thenCorrect() {
        Optional<String> opt = Optional.of("baeldung");

        opt.ifPresent(name -> System.out.println(name.length()));
    }
    @Test
    public void whenOrElseWorks_thenCorrect() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("john");
        assertEquals("john", name);
    }
    @Test
    public void whenOrElseGetWorks_thenCorrect() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
        assertEquals("john", name);
    }
    private String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }
    @Test
    public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
        String text = null;

        System.out.println("Using orElseGet:");
        String defaultText =
                Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Default Value", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Default Value", defaultText);
    }
    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        System.out.println("Using orElseGet:");
        String defaultText
                = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Text present", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Text present", defaultText);
    }
    @Test(expected = IllegalArgumentException.class)
    public void whenOrElseThrowWorks_thenCorrect() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseThrow(
                IllegalArgumentException::new);
    }
    @Test
    public void givenOptional_whenGetsValue_thenCorrect() {
        Optional<String> opt = Optional.of("baeldung");
        String name = opt.get();

        assertEquals("baeldung", name);
    }
//    @Test(expected = NoSuchElementException.class)
//    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
//        Optional<String> opt = Optional.ofNullable(null);
//        String name = opt.get();
//    }
//
//    Employee findById(Integer id) {
//        System.out.println("Looking for Employee #"+id);
//        return arrayOfEmps[id-1];
//    }

    private boolean priceIsInRange1(Employee employee) {
        return employee != null && employee.getMoney() != null && (employee.getMoney() >= 10 && employee.getMoney() <= 15);
    }

    private boolean priceIsInRange2(Employee employee) {
        return Optional.ofNullable(employee)
                .map(Employee::getMoney)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }
}
