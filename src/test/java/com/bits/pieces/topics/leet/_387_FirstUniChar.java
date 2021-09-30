package com.bits.pieces.topics.leet;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
 * RESULTS #1
 * Runtime: 23 ms, faster than 44.83% of Java online submissions for First Unique Character in a String.
 * Memory Usage: 39.6 MB, less than 60.12% of Java online submissions for First Unique Character in a String.
 *
 * RESULTS #2
 * Runtime: 23 ms, faster than 44.83% of Java online submissions for First Unique Character in a String.
 * Memory Usage: 39.4 MB, less than 70.49% of Java online submissions for First Unique Character in a String.
 *
 *
 * @author NV
 * @since ${DATE}
 */
public class _387_FirstUniChar {

    private static Map<Character, Integer> m;
    Set<Character> set;
    Stack<Character> uniStack;
    int[] seats;

    @Before
    public void init() {
        set = new HashSet<>();
        uniStack = new Stack<>();
    }

    @Test
    public void test_example_1() {
        assertThat(firstUniqChar("l"), is(0));
    }

    @Test
    public void test_example_2() {
        assertThat(firstUniqChar("leetcode"), is(0));
    }

    @Test
    public void test_example_3() {
        assertThat(firstUniqChar("loveleetcode"), is(2));
    }

    @Test
    public void test_example_4() {
        assertThat(firstUniqChar("aabb"), is(-1));
    }

    @Test
    public void test_example_5() {
        assertThat(firstUniqChar("aabbcdc"), is(5));
    }

    @Test
    public void test_example_6() {
        assertThat(firstUniqChar("abcdcba"), is(3));
    }

    @Test
    public void test_example_7() {
        assertThat(firstUniqChar("abcd"), is(0));
    }

    public int firstUniqChar(String s) {
        m = new HashMap<>();

        for(Character c : s.toCharArray())
            m.put(c,m.getOrDefault(c,0)+1);

        for(Character c : s.toCharArray())
            if(m.get(c)==1)
                return(s.indexOf(c));

        return -1;
    }

}
