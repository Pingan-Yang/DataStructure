package Tree.huffmantree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HuffmanTree----将一个数组创建成huffmantree
 * @Description:
 * @Author:ypa
 * @Date 2021/5/19 19:42
 * @Version:1.0
 */
/*
 *  赫夫曼树:
    ①基本介绍：
        给定n个权值作为n个叶子结点，构造一棵二叉树，若该树的带权路径长度(wpl)达到最小，
        称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman Tree), 还有的书翻译为霍夫曼树。
        赫夫曼树是带权路径长度最短的树，权值较大的结点离根较近。

        赫夫曼树是带权路径长度最短的树，权值较大的结点离根较近。

        树的带权路径长度：树的带权路径长度规定为所有叶子结点的带权路径长度之和，记为WPL(weighted path length) ,权值越大的结点离根结点越近的二叉树才是最优二叉树。

        WPL最小的就是赫夫曼树


 */

public class HuffmanTree {
    @Test
    public void test01(){
        int[] arr={13, 7, 8, 3, 29, 6, 1};
        Node node = creatHuffmanTree(arr);
        Node.preOrder(node);

    }

    /**
     * @Description: 创建Huffmantree
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/19
     */
    /*
        思路：
         构成赫夫曼树的步骤：
            从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
            取出根节点权值最小的两颗二叉树
            组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树

    * */
    public Node creatHuffmanTree(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        //将数组变成Node节点放入到集合中(方便操作，有现成的排序方法)
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {//3、不断的重复1、2 直到集合中只剩一个节点
            //1、将集合从小到大排序
            Collections.sort(nodes);
            //System.out.println(nodes);

            //2、取出两个最小的二叉树，构建成一个新的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    private int value;//权值
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        /*
            返回 0 表示 this == obj
            返回正整数表示 this > obj
            返回负数表示 this < obj
        * */
        // this.value-o.value 从小到大排序 ;-(this.value-o.value)从大到小排序
        return this.value - o.value;
    }

    public static void preOrder(Node root) {
        if (root == null) {
            return;
        } else {
            preOrder(root, 1);
        }
    }

    private static void preOrder(Node node, int flag) {
        System.out.println(node);
        if (node.left != null)
            preOrder(node.left, 1);
        if (node.right != null)
            preOrder(node.right, 1);
    }
}