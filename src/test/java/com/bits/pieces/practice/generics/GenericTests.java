package com.bits.pieces.practice.generics;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 2/23/2020
 */
public class GenericTests {

    /**
     * the <T> in the method signature implies that the method will be dealing
     * with generic type T. This is needed even if the method is returning void.
     *
     * @param a
     * @param <T>
     * @return
     */
    public <T> List<T> fromArrayToList(T[] a) {
        return Arrays.stream(a).collect(Collectors.toList());
    }

    /**
     * All generic types must be added to the method signature, for example,
     * if we want to modify the above method to deal with type T and type G,
     * it should be written like this:
     * <p>
     * We're passing a function that converts an array with the elements of
     * type T to list with elements of type G.
     *
     * @param a
     * @param mapperFunction
     * @param <T>
     * @param <G>
     * @return
     */
    public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
        return Arrays.stream(a)
                .map(mapperFunction)
                .collect(Collectors.toList());
    }

    @Test
    public void givenArrayOfIntegers_thanListOfStringReturnedOK() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        List<String> stringList = fromArrayToList(intArray, Object::toString);

        assertThat(stringList, hasItems("1", "2", "3", "4", "5"));
    }



    /**
     * This is an example of type erasure:
     * Type erasure removes all type parameters and replaces it with their bounds
     * or with Object if the type parameter is unbounded. Thus the bytecode after
     * compilation contains only normal classes, interfaces and methods thus ensuring
     * that no new types are produced. Proper casting is applied as well to the Object
     * type at compile time.
     *
     * @param list
     * @param <T>
     * @return
     */
    public <T> List<T> genericMethod(List<T> list) {
        return list.stream().collect(Collectors.toList());
    }

    // With type erasure, the unbounded type T is replaced with Object as follows:
        // for illustration
        public List<Object> withErasure(List<Object> list) {
            return list.stream().collect(Collectors.toList());
        }
        // which in practice results in (Compile Error)
        // public List withErasure(List list) {
        //     return list.stream().collect(Collectors.toList());
        // }




    /**
     * What is Type Erasure?
     * Type erasure can be explained as the process of enforcing type constraints only
     * at compile time and discarding the element type information at runtime.
     *
     * For Example:
     * @param elements
     * @param element
     * @param <E>
     * @return
     */
    public static  <E> boolean containsElement(E [] elements, E element){
        for (E e : elements)
            if(e.equals(element))
                return true;
        return false;
    }
    // The compiler replaces the unbound type E with an actual type of Object:
    // Therefore the compiler ensures type safety of our code and prevents runtime errors.
    public static  boolean containsElement2(Object [] elements, Object element){
        for (Object e : elements)
            if(e.equals(element))
                return true;
        return false;
    }
}
