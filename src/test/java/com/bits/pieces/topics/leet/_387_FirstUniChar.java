package com.bits.pieces.topics.leet;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * NAME - First Unique Character in a String
 * TOPICS - Hash Table, String, Queue, Counting
 * COMPLEX - Easy
 * URL - https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * PROBLEM
 * - Given a string s, find 1st non-repeating character.
 * Return chars index. If it doesn't exist, return -1
 *
 * CONSTRAINTS
 * - 1 <= s.length <= 10^5
 * - s consists of only lowercase English letters.
 *
 *
 * EXAMPLE #1               | EXAMPLE #2                 | EXAMPLE #3
 * Input: s = "leetcode"   | Input: s = "loveleetcode"  | Input: s = "aabb"
 * Output: 0               | Output: 2                  | Output: -1
 *
 * @author NV
 * @since ${DATE}
 */
public class _387_FirstUniChar {

    Set<Character> set;

    @Before
    public void init() {
        set = new HashSet<>();
    }


    @Test
    public void test_example_1() {
        assertThat(firstUniqChar("leetcode"), is(0));
    }

    @Test
    public void test_example_2() {
        assertThat(firstUniqChar("loveleetcode"), is(2));
    }

    @Test
    public void test_example_3() {
        assertThat(firstUniqChar("aabb"), is(-1));
    }

    @Test
    public void test_example_4() {
        assertThat(firstUniqChar("aabbcdc"), is(5));
    }

    @Test
    public void test_example_5() {
        assertThat(firstUniqChar("abcdcba"), is(3));
    }

    @Test
    public void test_example_6() {
        assertThat(firstUniqChar("abcd"), is(0));
    }

    public int firstUniqChar(String s) {
        int result = -1, length = s.length();
        char uni;

        if (length < 1 || length > 100000)
            return result;


        uni = s.charAt(0);
        set.add(uni);
        result = s.indexOf(uni);

        for(int i=1; i<length; i++) {
            char c = s.charAt(i);

            if(set.contains(c)) {   // 1. NOT Unique - Already in set
                if (uni == c)
                    uni = ' ';
            } else {                // 2. MAYBE Unique - Add to set
                set.add(c);
                uni = c;
                result = i;
            }

        }


        return result;
    }
}
