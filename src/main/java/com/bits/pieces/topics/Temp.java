package com.bits.pieces.topics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 9/9/2019
 */
public class Temp {


    /**
     * Valid Anagram
     *
     * PROBLEM
     *  Given two strings s and t , write a function to determine
     *      if t is an anagram of s.
     *
     * Example 1:
     *      Input: s="anagram",
     *             t="nagaram"
     *      Output: true
     *
     * Example 2:
     *      Input: s="rat",
     *             t="car"
     *      Output: false
     *
     * An anagram is produced by rearranging the letters of ss into tt.
     * Therefore, if tt is an anagram of ss, sorting both strings will
     * result in two identical strings. Furthermore, if ss and tt have
     * different lengths, tt must not be an anagram of ss and we can
     * return early.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }


    /**
     * TWO SUM
     *
     * PROBLEM
     *      Given an array of integers, return indices of the two numbers
     *      such that they add up to a specific target.
     *
     *      You may assume that each input would have exactly one solution,
     *      and you may not use the same element twice.
     *
     * EXAMPLE
     *      Given nums = [2, 7, 11, 15], target = 9,
     *      Because nums[0] + nums[1] = 2 + 7 = 9,
     *      return [0, 1].
     *
     * BRUTE FORCE
     *      The brute force approach is simple.
     *      Loop through each element 'x'
     *      Find if there is another value that is equal to target−x.
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum_BruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    /**
     * TWO SUM
     *
     * ONE PASS HASH TABLE
     * It turns out we can do it in one-pass. While we iterate and inserting
     * elements into the table, we also look back to check if current element's
     * complement already exists in the table. If it exists, we have found a
     * solution and return immediately.
     *
     * Complexity Analysis:
     *      Time complexity : O(n)O(n). We traverse the list containing nn elements only once.
     *                                  Each look up in the table costs only O(1)O(1) time.
     *      Space complexity : O(n)O(n). The extra space required depends on the number of items
     *                                   stored in the hash table, which stores at most nn elements.
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum_OnePassHashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * VALID PARENTHESES
     *
     * PROBLEM
     *      Given a string containing the characters '(', ')', '{', '}', '[' and ']'.
     *      Determine if the input string is valid.
     *
     *      An input string is only valid if:
     *          - Open brackets must be closed by the same type of brackets.
     *          - Open brackets must be closed in the correct order.
     *          - Note that an empty string is also considered valid.
     *
     * EXAMPLES
     *        assertThat(isValid("( )"),         is(true));
     *        assertThat(isValid("( ) [ ] { }"), is(true));
     *        assertThat(isValid("( ]"),         is(false));
     *        assertThat(isValid("( [ ) ]"),     is(false));
     *        assertThat(isValid("{ [ ] }"),     is(true));
     */
    class Solution {
        // Hash table that takes care of the mappings.
        private HashMap<Character, Character> mappings;
        // Initialize hash map with mappings. This simply makes the code easier to read.
        public Solution() {
            this.mappings = new HashMap<Character, Character>();
            this.mappings.put(')', '(');
            this.mappings.put('}', '{');
            this.mappings.put(']', '[');
        }

        public boolean isValid(String s) {
            // Initialize a stack to be used in the algorithm.
            Stack<Character> stack = new Stack<Character>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                // If the current character is a closing bracket.
                if (this.mappings.containsKey(c)) {
                    // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                    char topElement = stack.empty() ? '#' : stack.pop();
                    // If the mapping for this bracket doesn't match the stack's top element, return false.
                    if (topElement != this.mappings.get(c))
                        return false;
                } else  // If it was an opening bracket, push to the stack.
                    stack.push(c);
            }

            // If the stack still contains elements, then it is an invalid expression.
            return stack.isEmpty();
        }
    }
}
