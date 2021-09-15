package com.bits.pieces.topics.temp;


//    Examples:
//
//    abc → abc
//    aa bbb c → a2b3c
//    abcdccccbbb → abcdc4b3
//    aaaabaaaccaaaba → a4ba3c2a3ba

public class T6 {
//    Write a string compression algorithm that takes in a string and
//    replaces subsequences of repeating characters with the character
//    and the number of repeating characters.


    public static void main(String[] args) {
        compressString("aabbc");
    }

    private static void compressString(String s) {

        char[] chars = s.toCharArray();
        char last = '1'; // 1 = Unassigned

        int count = 0;


        //  'a a b b'
        for(int i=0; i<chars.length; i++) {
            char current = chars[i];

            if(last == '1') {
                last = current;
                count++;

            }
            if(current==last){
                count++;
                break;
            }

            if(count == 2) {
                chars[last] = (char) count;
                // chars. Replace Chars in string with count
            }





        }

        System.out.println(s);
    }


}
