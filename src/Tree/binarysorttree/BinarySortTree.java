package Tree.binarysorttree;

import org.junit.Test;


/**
 * @ClassName BinarySortTree
 * @Description:
 * @Author:ypa
 * @Date 2021/5/24 20:03
 * @Version:1.0
 */
public class BinarySortTree {
    @Test
    public void test01() {
        int[] data = {7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < data.length; ++i) {
            binarySortTree.add(new Node(data[i]));
        }
        /*System.out.println("二叉排序树中序遍历：");
        binarySortTree.midOrder();*/
        System.out.println("------------------------------------------------");
       /* Node deletenNode = binarySortTree.search(1);
        System.out.println(deletenNode);
        Node parentNode = binarySortTree.searchParent(1);
        System.out.println(parentNode);*/
        binarySortTree.delete(12);
        binarySortTree.delete(7);
        binarySortTree.delete(3);
        binarySortTree.delete(10);
        binarySortTree.delete(1);
        binarySortTree.delete(2);
        binarySortTree.delete(5);
        binarySortTree.delete(9);

        binarySortTree.midOrder();

    }

    private Node root;

    /**
     * @Description: 前序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public void midOrder() {
        if (root != null) {
            root.midOrder();
        } else {
            System.out.println("根节点为空无法遍历");
            return;
        }
    }

    /**
     * @Description: 加入节点，形成排序二叉树
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public void add(Node node) {

        if (root == null)
            root = node;
        else
            root.add(node);
    }

    /**
     * @Description: 查找要删除的节点
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public Node search(int targrt) {
        if (root == null) {
            return null;
        } else {
            return root.search(targrt);
        }
    }

    /**
     * @Description: 查找要删除节点的父节点
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public Node searchParent(int target) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(target);
        }
    }
    /**
    * @Description: 删除节点
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/25
    */
    /*

            7
          /   \
         3     10
       /  \    / \
      1    5   9  12
      \
       2
        第一种情况:
        删除叶子节点 (比如：2, 5, 9, 12)
        思路
        (1) 需求先去找到要删除的结点  targetNode
        (2)  找到targetNode 的 父结点 parent
        (3)  确定 targetNode 是 parent的左子结点 还是右子结点
        (4)  根据前面的情况来对应删除
        左子结点 parent.left = null
        右子结点 parent.right = null;
        第二种情况: 删除只有一颗子树的节点 比如 1
        思路
        (1) 需求先去找到要删除的结点  targetNode
        (2)  找到targetNode 的 父结点 parent
        (3) 确定targetNode 的子结点是左子结点还是右子结点
        (4) targetNode 是 parent 的左子结点还是右子结点
        (5) 如果targetNode 有左子结点
        5. 1 如果 targetNode 是 parent 的左子结点
        parent.left = targetNode.left;
        5.2  如果 targetNode 是 parent 的右子结点
        parent.right = targetNode.left;
        (6) 如果targetNode 有右子结点
        6.1 如果 targetNode 是 parent 的左子结点
        parent.left = targetNode.right;
        6.2 如果 targetNode 是 parent 的右子结点
        parent.right = targetNode.right


        情况三 ： 删除有两颗子树的节点. (比如：7, 3，10 )
        思路
        (1) 需求先去找到要删除的结点  targetNode
        (2)  找到targetNode 的 父结点 parent
        (3)  从targetNode 的右子树找到最小的结点
        (4) 用一个临时变量，将 最小结点的值保存 temp = 11
        (5)  删除该最小结点
        (6)  targetNode.value = temp
    * */
    public void delete(int target){
        Node targetNode = search(target);
        if (targetNode==null){
            return;
        }
        Node parentNode = searchParent(target);
        if (parentNode==null){//找到了目标节点，但是没有父节点 ，表明该树只有一个root节点
            root=null;
            return ;
        }
        if (targetNode.left==null&&targetNode.right==null){//表明要输出的是叶子节点
            if(parentNode.left==targetNode){//parent的左子结点
                parentNode.left=null;
            }else if(parentNode.right==targetNode){//parent的右子结点
                parentNode.right=null;
            }
            return;
        }else if(targetNode.right!=null&&targetNode.left!=null){//删除有两颗子树的节点
            int minvalue = deleteLeftMinNode(targetNode);
            targetNode.value=minvalue;
            return;
        }else{//删除只有一个子树的节点
            /*
                    10
                    /
                   1
                   此时如果要删除10 ，则需要注意 它的父节点为空，下面要左判断
            * */
            if (targetNode.left!=null){//targetNode 有左子结点
                if (parentNode==null){
                    root=targetNode.left;
                }else {
                    if (parentNode.left == targetNode) {//targetNode 是 parent 的左子结点
                        parentNode.left = targetNode.left;
                    } else if (parentNode.right == targetNode) {//targetNode 是 parent 的右子结点
                        parentNode.right = targetNode.left;
                    }
                }
                return;
            }
            if (targetNode.right!=null) {//targetNode 有右子结点
                if (parentNode == null) {
                    root = targetNode.right;
                } else {
                    if (parentNode.left == targetNode) {//targetNode 是 parent 的左子结点
                        parentNode.left = targetNode.right;
                    } else if (parentNode.right == targetNode) {//targetNode 是 parent 的右子结点
                        parentNode.right = targetNode.right;
                    }
                }
                return;
            }
        }
    }
    /**
     * @Description: 遍历子树的右节点寻找最小的节点，同时删除最小值节点
     * @Param:
     * @return:  最小的节点的value
     * @Author: ypa
     * @Date: 2021/5/25
     */
    private int deleteLeftMinNode(Node target){
        Node temp=target;
        while (target.left!=null){
            target=target.left;
        }
        delete(target.value);
        return target.value;
    }
}

class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * @Description: 中序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public void midOrder() {
        if (this.left != null)
            this.left.midOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.midOrder();
    }

    /**
     * @Description: 创建二叉排序数
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public void add(Node node) {
        if (node == null) {
            System.out.println("节点为空,无法加入！！");
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
                //System.out.println("添加" + node + "成功");
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
                //System.out.println("添加"+node+"成功");
            } else {
                this.right.add(node);
            }

        }

    }

    /**
     * @Description: 查找要删除的节点
     * @Param:
     * @return: 找到就返回 要删除的节点，否则返回null
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public Node search(int targert) {
        if (this.value == targert) {
            return this;
        } else if (targert < this.value && this.left != null) {
            return left.search(targert);
        } else if (targert >= this.value && this.right != null) {//"="号的情况与创建二叉排序树要一致
            return this.right.search(targert);
        } else {
            return null;
        }

    }

    /**
     * @Description: 查找要删除节点的夫父节点
     * @Param:
     * @return: 找到就返回父节点，否则返回null
     * @Author: ypa
     * @Date: 2021/5/24
     */
    public Node searchParent(int target) {
        if ((this.left != null && this.left.value == target) ||(this.right != null && this.right.value == target)) {
            return this;
        } else {
            if (target < this.value && this.left != null) {//向左子树递归查找
                return this.left.searchParent(target);
            } else if (target >= this.value && this.right != null) {//向右子树递归查找
                return this.right.searchParent(target);
            } else {
                return null;//没有找到父节点
            }
        }
    }
}
