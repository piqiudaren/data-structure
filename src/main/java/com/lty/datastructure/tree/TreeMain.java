package com.lty.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 主程序
 * @author yansou
 */
public class TreeMain {

    /**
     * 二叉排序树；左边是小于等于，右边是大于根节点。
     * @param root
     * @param data
     * @return
     */
    public static Tree insert (Tree root,Integer data) {
        if (root == null) {
            return new Tree(data);
        } else {
            Tree cur;
            if (data <= root.data) {
                cur = TreeMain.insert(root.treeLeft,data);
                root.treeLeft = cur;
            } else {
                cur = TreeMain.insert(root.treeRight,data);
                root.treeRight = cur;
            }
        }
        return root;
    }

    /**
     * 深度优先遍历分为，先序、中序和后序遍历
     * 先序遍历：先访问根，在访问左子树，最后访问右子树，总结就是“根左右”；
     * 中序遍历：先访问左子树，再访问根，最后访问右子树，总结就是“左根右”；
     * 后序遍历：先访问左子树，再访问右子树，最后访问根，总结就是“左右根”；
     * 通常采用递归的方式实现遍历，非递归方式需要结合栈（后进先出）的特点实现。
     */


    /**
     * 递归法实现先序遍历，并打印 根左右
     * @param root
     */
    public static void preOrder(Tree root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.treeLeft);
        preOrder(root.treeRight);
    }

    /**
     * 递归法实现中序遍历，并打印 左根右
     * @param root
     */
    public static void inOrder(Tree root) {
        if (root == null) {
            return;
        }
        inOrder(root.treeLeft);
        System.out.print(root.data+" ");
        inOrder(root.treeRight);
    }

    /**
     * 递归法实现后序遍历，并打印 左右根
     * @param root
     */
    public static void postOrder(Tree root){
        if (root == null) {
            return;
        }
        postOrder(root.treeLeft);
        postOrder(root.treeRight);
        System.out.print(root.data + " ");
    }


    /**
     * 非递归方法实现先序遍历，并打印 根左右
     * @param root
     */
    public static void preOrder2(Tree root){
        LinkedList<Tree> stack = new LinkedList<>();
        Tree currentRoot = null;
        stack.push(root);
        while (!stack.isEmpty()){
            currentRoot = stack.pop();
            System.out.print(currentRoot.data+" ");
            //栈是先入后出，需要先入栈右分支
            if (currentRoot.treeRight != null) {
                stack.push(currentRoot.treeRight);
            }
            if (currentRoot.treeLeft != null) {
                stack.push(currentRoot.treeLeft);
            }
        }
    }

    /**
     * 非递归方法实现中序遍历，并打印 左根右
     * @param root
     */
    public static void inOrder2 (Tree root) {
        LinkedList<Tree> stack = new LinkedList<>();
        Tree currentRoot = root;
        while (currentRoot != null || !stack.isEmpty()) {
            //遍历二叉树的最左侧
            while (currentRoot != null) {
                stack.push(currentRoot);
                currentRoot = currentRoot.treeLeft;
            }
            currentRoot = stack.pop();
            System.out.print(currentRoot.data+" ");
            currentRoot = currentRoot.treeRight;
        }
    }

    /**
     * 非递归方法实现后序遍历，并打印 左右根
     * @param root
     */
    public static void postOrder2 (Tree root) {
        LinkedList<Tree> stack = new LinkedList<>();
        Tree currentRoot =root;
        Tree rightRoot = null;
        while (currentRoot != null || !stack.isEmpty()) {
            while (currentRoot != null) {
                stack.push(currentRoot);
                currentRoot = currentRoot.treeLeft;
            }

            currentRoot = stack.pop();
            //当前节点没有右节点或上一个结点（已经输出的结点）是当前结点的右结点，则输出当前结点
            while (currentRoot.treeRight == null || currentRoot.treeRight == rightRoot) {
                System.out.print(currentRoot.data+" ");
                rightRoot = currentRoot;
                if (stack.isEmpty()) {
                    return;
                }
                currentRoot = stack.pop();
            }

            //还有未遍历的右侧节点
            stack.push(currentRoot);
            currentRoot =currentRoot.treeRight;
        }
    }


    public static void levelOrder (Tree root) {
        //新增队列
        Queue<Tree> queue = new LinkedList<>();

        if (root != null) {
            //将根节点加入队列
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            //创建cur的目的是在while循环的时候逐层将树带入，如果直接用root,会导致只能输出一级树
            Tree cur = queue.peek();
            System.out.print(cur.data+" ");
            queue.remove();

            //先将左分支加入队列，之后先输出
            if (cur.treeLeft != null) {
                queue.add(cur.treeLeft);
            }

            if (cur.treeRight != null) {
                queue.add(cur.treeRight);
            }
        }
    }

    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字的个数：");
        Integer T = sc.nextInt();*/
        Integer [] t = new Integer[]{20,35,40,15,29,50,16,28,30,45,55};

        /*System.out.println("请输入数字，已空格隔开：");*/

       /* for (int i = 0;i <t.length;i++){
            t[i] = sc.nextInt();
        }*/

        Tree root = null;
        for (int i = 0; i< t.length;i++) {
            root = insert(root,t[i]);
        }

        System.out.println("递归先序遍历：");
        preOrder(root);
        System.out.println();
        System.out.println("递归中序遍历：");
        inOrder(root);
        System.out.println();
        System.out.println("递归后序遍历：");
        postOrder(root);
        System.out.println();
        System.out.println("非递归先序遍历：");
        preOrder2(root);
        System.out.println();
        System.out.println("非递归中序遍历：");
        inOrder2(root);
        System.out.println();
        System.out.println("非递归后序遍历：");
        postOrder2(root);
        System.out.println();
        System.out.println("广度优先遍历：");
        levelOrder(root);

    }

}
