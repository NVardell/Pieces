package com.bits.pieces.spring.mongo;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 10/9/2019
 */
//public class MyClassC {
    class MainClassA{
        public static void main(String[] args) {
            System.out.println("Class A " + args[0]);
        }
    }
    class MainClassB{
        public static void main(String[] args) {
            System.out.println("Class B ");
            MainClassA.main(args);
        }
    }



//}
