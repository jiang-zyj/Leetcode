package com.zyj.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: LeetCode2
 * @ClassName Demo02
 * @author: YaJun
 * @Date: 2021 - 05 - 27 - 21:43
 * @Description: com.zyj.tree
 * @version: 1.0
 */
public class Demo02 {

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例1:
     * <p>
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * 示例2:
     * <p>
     * 输入:
     * 5
     * / \
     * 1   4
     * / \
     * 3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     * 根节点的值为 5 ，但是其右子节点值为 4 。
     */


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

    public static void main(String[] args) {
        new Demo02();
    }

    public Demo02() {

    }

    /**
     * 方法 1：深度优先遍历
     * 解题思路及算法：
     *      深度优先遍历，判断该树是否为二叉搜索树
     *      如何判断是否为二叉搜索树
     *      要解决这道题首先我们要了解二叉搜索树的性质，性质为：如果二叉树的左子树不为空，则左子树上
     *      的所有节点都要小于它的根节点，右子树不为空，则右子树的所有节点都大于根节点，它的左右子树也为二叉搜索树。
     *      这就很明确的需要设计一个递归函数 helper(root, lower, upper)来递归判断，函数表示考虑以
     *      root 为根的子树，判断子树中所有节点的值是否都在(l,r)的范围内(注意是开区间)。如果 root 的值 var
     *      不在(l,r)的范围内说明不满足条件直接返回，否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才
     *      说明这是一棵二叉搜索树。
     *      那么根据二叉搜索树的性质，在递归调用左子树时，我们需要把上界 upper 改为 root.val，即调用
     *      helper(root.left, lower, root.val)，因为左子树里所有节点的值均小于它的根节点的值。同理递归调用右子树时，
     *      我们需要把下界 lower 改为 root.val，即调用 helper(root.right, root.val, upper)。
     *      函数递归调用的入口为 helper(root, -inf, +inf)
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    /**
     * 方法 2：中序遍历
     *  由二叉搜索树的性质可知，如果对二叉树进行中序遍历(左, 根, 右)，那么得出来的结果必定是升序序列
     *  可以通过判断二叉树中序遍历的结果(即是否为升序)，可以得出该二叉树是否为二叉搜索树。
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
