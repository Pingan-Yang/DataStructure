package TestLeetCode;

import org.junit.Test;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName Leetcode1609
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/27 11:23
 * @Version:1.0
 */

public class Leetcode1609 {
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int level = 0;//层数的索引下标
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();//记录每一层有多少元素
            int pre = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            //开始遍历这一层
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();//获取队列头的第一个元素并删除；
                int val = node.val;
                //第0层
                if (level % 2 == 0 && val % 2 == 0) {
                    return false;
                }
                //其他的偶数层和奇数层
                if ((level%2==0&&val%2!=0&&val<=pre)||(level%2!=0&&val%2==0&&val>=pre)){
                    return false;
                }
                pre=val;
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
            level++;
        }
        return true;
    }

    //层序遍历

    /**
     * 初始时，根结点入队列
     *
     * 然后，while循环判断队列不空时，弹出一个结点，访问它，并把它的所有孩子结点入队列。
     * @param root
     * @return
     */
    public void  SequenceForeach(TreeNode root){
        if (root ==null){
            return;
        }
        Queue<TreeNode> queue=new ArrayDeque<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println("val="+node.val);
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(12);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(6);
        TreeNode node9 = new TreeNode(2);
        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        node4.left=node8;
        node5.right=node9;

       /* boolean tree = isEvenOddTree(root);
        System.out.println(tree);*/
        SequenceForeach(root);
    }


}

