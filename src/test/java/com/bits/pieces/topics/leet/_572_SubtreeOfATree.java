package com.bits.pieces.topics.leet;

import com.bits.pieces.app.model.TreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * STATUS - Solution Accepted.
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

    TreeNode rootOne, rootTwo, rootThree, subRootOne, subRootTwo, subRootThree;

    @Before
    public void setup() {
        rootOne = TreeNode.builder().val(3)
                .left(TreeNode.builder().val(4)
                        .left(TreeNode.builder().val(1).build())
                        .right(TreeNode.builder().val(2).build())
                        .build())
                .right(TreeNode.builder().val(5).build())
                .build();
        subRootOne = TreeNode.builder().val(4)
                .left(TreeNode.builder().val(1).build())
                .right(TreeNode.builder().val(2).build())
                .build();


        rootTwo = TreeNode.builder().val(3)
                .left(TreeNode.builder().val(4)
                        .left(TreeNode.builder().val(1).build())
                        .right(TreeNode.builder().val(2)
                                .left(TreeNode.builder().val(0).build())
                                .build())
                        .build())
                .right(TreeNode.builder().val(5).build())
                .build();
        subRootTwo = TreeNode.builder().val(4)
                .left(TreeNode.builder().val(1).build())
                .right(TreeNode.builder().val(2).build())
                .build();

        rootThree = TreeNode.builder().val(1)
                .left(TreeNode.builder().val(1).build())
                .build();
        subRootThree = TreeNode.builder().val(1).build();
    }


    @Test
    public void givenNullTrees_assertFalse() {
        assertThat(isSubTree(null, null), is(false));
    }

    @Test
    public void testSubTrees_1() {
        assertThat(isSubTree(rootOne, subRootOne), is(true));
    }

    @Test
    public void testSubTrees_2() {
        assertThat(isSubTree(rootTwo, subRootTwo), is(false));
    }
    @Test
    public void testSubTrees_3() {
        assertThat(isSubTree(rootThree, subRootThree), is(true));
    }

    /**
     * Evaluates tree nodes & returns true or false if the node is a subtree.
     * @param root ~ Root Tree Node
     * @param subRoot ~ Sub Root Tree Node
     * @return boolean ~ True/False
     */
    public static boolean isSubTree(TreeNode root, TreeNode subRoot) {
        return root != null
                && (equals(root, subRoot)
                    || traverse(root.left, subRoot)
                    || traverse(root.right, subRoot)
        );
    }

    public static boolean equals(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null)
            return true;
        if(root == null || subRoot == null)
            return false;
        return root.val == subRoot.val
                && equals(root.left, subRoot.left)
                && equals(root.right, subRoot.right);
    }

    public static boolean traverse(TreeNode root, TreeNode subRoot) {
        return root != null
                && (equals(root, subRoot)
                    || traverse(root.left, subRoot)
                    || traverse(root.right, subRoot)
        );
    }
}
