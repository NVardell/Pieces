package com.bits.pieces.spring.mongo;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 10/9/2019
 */
 interface I1 {
    String NAME = "n1";
    String s1 = "s1";

}

 interface I2 extends I1 {
     String name = "N2";
 }

 class C2 implements I2 {
     public static void main(String[] args) {
         System.out.println(I2.name + ",");
         System.out.println(I2.s1+",");
//         System.out.println(((I1)new C2()).name);
     }
 }

