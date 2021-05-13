package com.bits.pieces.topics.leetcode;

import lombok.Builder;
import lombok.Data;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 9/9/2019
 */
@Data
@Builder
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}
