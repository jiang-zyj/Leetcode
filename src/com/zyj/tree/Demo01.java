package com.zyj.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 才二
 * @ClassName Demo01
 * @Auther: YaJun
 * @Date: 2021 - 05 - 26 - 21:25
 * @Description: com.zyj.tree
 * @version: 1.0
 */
public class Demo01 {

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     */

    public static void main(String[] args) {
        new Demo01();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public Demo01() {

    }

    /**
     * 方法一：使用深度优先遍历
     * 思路和算法：
     * 如果我们知道了左子树和右子树的最大深度 l 和 r，那么该二叉树的最大深度为
     * max(l,r) + 1
     * 而左子树和右子树的最大深度又可以以同样的方式进行计算。因此我们可以用深度优先遍历的方法来计算
     * 二叉树的最大深度。具体而言，在计算当前二叉树的最大深度时，可以先递归计算出其左子树和右子树的最大深度，
     * 然后在 O(1) 时间内计算出当前二叉树的最大深度。递归在访问到空节点时退出。
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 方法一的简写：
     */
    public int maxDepth2(TreeNode root) {

        return root == null ? 0 : Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;

    }


    /**
     * 方法二：广度优先遍历
     * 思路与算法：
     * 我们也可以用广度优先遍历的方式来解决该问题
     * 定义一个变量 ans 来维护拓展的次数，通过遍历每一层的节点，然后使 ans++，最后二叉树的最大深度即为 ans。
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
