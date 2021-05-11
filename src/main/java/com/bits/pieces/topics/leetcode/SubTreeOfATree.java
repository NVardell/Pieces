package com.bits.pieces.topics.leetcode;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 9/9/2019
 */
public class SubTreeOfATree {

    public static boolean isSubtree(TreeNode s, TreeNode t) {
//        return traverse(s,t);
        return  s!=null && ( equals(s,t) || traverse(s.left,t) || traverse(s.right,t));
    }
    public static boolean equals(TreeNode x, TreeNode y)
    {
        if(x==null && y==null)
            return true;
        if(x==null || y==null)
            return false;
        return x.val==y.val && equals(x.left,y.left) && equals(x.right,y.right);
    }
    public static boolean traverse(TreeNode s, TreeNode t)
    {
        return  s!=null && ( equals(s,t) || traverse(s.left,t) || traverse(s.right,t));
    }









//    public static boolean isSubtree(TreeNode s, TreeNode t) {
//
//        if(s == null || t == null)
//            return false;
//
//        Queue<TreeNode> sQ = new LinkedList<>();
//        sQ.add(s);
//
//        Queue<TreeNode> tQ = new LinkedList<>();
////        tQ.add(t);
//
//        while(!sQ.isEmpty()) {
//            s = sQ.poll();
//
//            System.out.print("t = " + t.val + "\ttQ Size = " + tQ.size() + "\ttQ = [");
//            for(TreeNode n : tQ) {
//                System.out.print(n.val + ", ");
//            }
//            System.out.print("]\n");
//            System.out.print("s = " + s.val + "\tsQ Size = " + sQ.size() + "\tsQ = [");
//            for(TreeNode n : sQ) {
//                System.out.print(n.val + ", ");
//            }
//            System.out.print("]\n");
//
//            if(s.val == t.val) {
//                System.out.println("****************   S.VAL = T.VAL    ****************");
////                if(tQ.isEmpty() && sQ.isEmpty() && s.val == t.val) {
////                    System.out.println("****************    tQ is Empty && sQ is Empty && s.val == t.val");
////                    return true;
////                }
//                if(s.left != null && s.right != null && t.left != null && t.right != null
//                    && s.left.val == t.left.val && s.right.val == t.right.val) {
//                    System.out.println("****************   ADDING LEFT & RIGHT NODES TO tQ.");
//                    tQ.add(t.left);
//                    tQ.add(t.right);
//                    t = tQ.poll();
//                } else if(s.left != null && t.left != null && s.left.val == t.left.val) {
//                    System.out.println("****************    ADDING LEFT NODE TO tQ.");
//                    tQ.add(t.left);
//                    t = tQ.poll();
//                } else if(s.right != null && t.right != null && s.right.val == t.right.val) {
//                    System.out.println("****************    ADDING RIGHT NODE TO tQ.");
//                    tQ.add(t.right);
//                    t = tQ.poll();
//                } else if(tQ.isEmpty() && sQ.isEmpty() && s.val == t.val && s.left == null && s.right == null) {
//                    System.out.println("****************    tQ is Empty && sQ is Empty && s.val == t.val");
//                    return true;
//                } else if (s.left == null && s.right == null && t.left == null && t.right == null) {
//                    t = tQ.poll();
//                }
//
//
//            }
////            else {
////                if(s.left != null && s.right != null && s.left.val != t.val && s.right.val != t.val) {
////                    System.out.println("****************   ADDING LEFT & RIGHT NODES TO sQ.");
////                    sQ.add(s.left);
////                    sQ.add(s.right);
////                } else if (s.left != null && s.left.val == t.val) {
////                    System.out.println("****************    ADDING LEFT NODE TO sQ.");
////                    sQ.add(s.left);
////                } else if(s.right != null && s.right.val == t.val) {
////                    System.out.println("****************    ADDING RIGHT NODE TO sQ.\n");
////                    sQ.add(s.right);
////                }
//
////            }
////            if(s.left != null && s.left.val == t.val)
////                sQ.add(s.left);
////            if(s.right != null && s.right.val == t.val)
////                sQ.add(s.right);
//            if(s.left != null)
//                sQ.add(s.left);
//            if(s.right != null)
//                sQ.add(s.right);
//        }
//
//        return false;
//    }






































//    public static boolean isSubtree(TreeNode s, TreeNode t) {
//        Queue<TreeNode> sQ = new LinkedList<>();
//        sQ.add(s);
//
//        Queue<TreeNode> tQ = new LinkedList<>();
//        tQ.add(t);
//
//        while(!sQ.isEmpty()) {
//
//            s = sQ.poll();
//            System.out.print("t = " + t.val + "\ttQ Size = " + tQ.size() + "\ttQ = [");
//            for(TreeNode n : tQ) {
//                System.out.print(n.val + ", ");
//            }
//            System.out.print("]\n");
//            System.out.print("s = " + s.val + "\tsQ Size = " + sQ.size() + "\tsQ = [");
//            for(TreeNode n : sQ) {
//                System.out.print(n.val + ", ");
//            }
//            System.out.print("]\n");
//
//            // if(tQ.isEmpty())
//            //     return false;
//
//            if(s.val == t.val) {
//                if(sQ.isEmpty() && tQ.isEmpty())
//                    return true;
//                t = tQ.poll();
//                System.out.println("****************   S.VAL = T.VAL    ****************");
//                if(t.left != null && t.right != null) {
//                    System.out.println("****************   ADDING LEFT & RIGHT NODES TO tQ.");
//                    tQ.add(t.left);
//                    tQ.add(t.right);
//                    t = tQ.poll();
//                }
//                else if(t.left != null) {
//                    System.out.println("****************    ADDING LEFT NODE TO tQ.");
//                    tQ.add(t.left);
//                    t = tQ.poll();
//                }
//                else if(t.right != null) {
//                    System.out.println("****************    ADDING RIGHT NODE TO tQ.");
//                    tQ.add(t.right);
//                    t = tQ.poll();
//                }
//                System.out.print("****************    t = " + t.val + "\ttQ Size = " + tQ.size() + "\ttQ = [");
//                for(TreeNode n : tQ) {
//                    System.out.print(n.val + ", ");
//                }
//                System.out.print("]\n");
//            }
//            if(s.left != null)
//                sQ.add(s.left);
//            if(s.right != null)
//                sQ.add(s.right);
//
//            // s = sQ.poll();
//            System.out.print("s = " + s.val + "\tsQ Size = " + sQ.size() + "\tsQ = [");
//            for(TreeNode n : sQ) {
//                System.out.print(n.val + ", ");
//            }
//            System.out.print("]\n\n\n");
//        }
//
//        if(tQ.isEmpty())
//            return true;
//
//        return false;
//    }
}
