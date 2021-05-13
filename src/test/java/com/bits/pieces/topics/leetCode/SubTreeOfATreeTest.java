package com.bits.pieces.topics.leetCode;

import com.bits.pieces.topics.leetcode.SubTreeOfATree;
import com.bits.pieces.topics.leetcode.TreeNode;
import org.junit.Before;
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
 * EXAMPLE #1               |   EXAMPLE #2            |   EXAMPLE #3
 *    S:   3     T:  4      |      S: 3     T:  4     |      S: 1     T:  1
 *        / \       / \     |        / \       / \    |        /
 *       4   5     1   2    |       4   5     1   2   |       1
 *      / \                 |     / \                 |
 *     1   2                |    1   2                |
 *                          |       /                 |
 *                          |      0                  |
 *                          |                         |
 *    OUTPUT = TRUE         |   OUTPUT = FALSE        |   OUTPUT = TRUE
 *
 * @author Nate Vardell
 * @since 9/9/2019
 */
public class SubTreeOfATreeTest {

    TreeNode s1, s2, s3, t1, t2, t3;

    @Before
    public void setup() {
        buildTrees();
    }

    @Test
    public void testSubTrees_1() {
        assertThat(SubTreeOfATree.isSubtree(s1, t1), is(true));
    }

    @Test
    public void testSubTrees_2() {
        assertThat(SubTreeOfATree.isSubtree(s2, t2), is(false));
    }
    @Test
    public void testSubTrees_3() {
        assertThat(SubTreeOfATree.isSubtree(s3, t3), is(true));
    }



    private void buildTrees() {
        s1 = TreeNode.builder().val(3)
                .left(TreeNode.builder().val(4)
                        .left(TreeNode.builder().val(1).build())
                        .right(TreeNode.builder().val(2).build())
                        .build())
                .right(TreeNode.builder().val(5).build())
                .build();
        t1 = TreeNode.builder().val(4)
                .left(TreeNode.builder().val(1).build())
                .right(TreeNode.builder().val(2).build())
                .build();


        s2 = TreeNode.builder().val(3)
                .left(TreeNode.builder().val(4)
                        .left(TreeNode.builder().val(1).build())
                        .right(TreeNode.builder().val(2)
                                .left(TreeNode.builder().val(0).build())
                                .build())
                        .build())
                .right(TreeNode.builder().val(5).build())
                .build();
        t2 = TreeNode.builder().val(4)
                .left(TreeNode.builder().val(1).build())
                .right(TreeNode.builder().val(2).build())
                .build();

        s3 = TreeNode.builder().val(1)
                .left(TreeNode.builder().val(1).build())
                .build();
        t3 = TreeNode.builder().val(1).build();
    }
}
