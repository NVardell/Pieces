package com.bits.pieces.topics.leet;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * URL - https://leetcode.com/problems/subtree-of-another-tree/
 *
 * PROBLEM
 *      Given two non-empty binary trees s and t, check whether tree t has exactly the same
 *      structure and node values with a subtree of s.  A subtree of s is a tree consists of
 *      a node in s and all of this node's descendants.
 *
 * NOTES
 *      - Definition for a binary tree node.
 *          public class TreeNode {
 *              int val;
 *              TreeNode left;
 *              TreeNode right;
 *              TreeNode(int x) { val = x; }
 *          }
 *
 * CONSTRAINTS
 *      • Number of nodes in RootTree is 1-2000
 *      • Number of nodes in SubTree is 1-1000
 *      • -104 <= root.val <= 104
 *      • -104 <= subRoot.val <= 104
 *
 * EXAMPLE #1               |   EXAMPLE #2            |   EXAMPLE #3
 *    T:   3     S:  4      |      T: 3     S:  4     |      T: 1     S:  1
 *        / \       / \     |        / \       / \    |        /
 *       4   5     1   2    |       4   5     1   2   |       1
 *      / \                 |     / \                 |
 *     1   2                |    1   2                |
 *                          |       /                 |
 *                          |      0                  |
 *                          |                         |
 *
 * @author NV
 * @since 9/4/2021
 */
public class _572_SubtreeOfATree {

    @Test
    public void tests() {
        assertThat(solution(null, null), is(false));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static boolean solution(TreeNode root, TreeNode subRoot) {
        boolean result = false;




        return result;
    }
}
