package Tree.arraybinarytree;

import org.junit.Test;

/**
 * @ClassName ArrayBinaryTree--顺序存储二叉树
 * @Description:
 * @Author:ypa
 * @Date 2021/5/13 15:00
 * @Version:1.0
 */
/*
    顺序存储二叉树：
    基本说明：
        从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组。
    顺序存储二叉树的特点:
        顺序二叉树通常只考虑完全二叉树
        第n个元素的左子节点为  2 * n + 1
        第n个元素的右子节点为  2 * n + 2
        第n个元素的父节点为  (n-1) / 2

* */
public class ArrayBinaryTree {
    @Test
    public void test01(){
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println();
        arrBinaryTree.midOrder();
        System.out.println();
        arrBinaryTree.posterOrder();
    }
}
class ArrBinaryTree{
    private int[]data;

    public ArrBinaryTree(int[] data) {
        this.data = data;
    }

    //重载前序遍历方法
    public void preOrder(){
        preOrder(0);
    }
    /**
    * @Description: 前序遍历
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/13
    */
    private void preOrder(int index){
        //如果传入的数组为空或者数组长度为空，则无法遍历
        if (data==null||data.length==0)
            return;
        System.out.print(data[index]+"  ");
        if (2*index+1<data.length){
            preOrder(2*index+1);
        }
        if (2*index+2<data.length){
            preOrder(2*index+2);
        }
    }
    //重载中序遍历
    public void midOrder(){
        midOrder(0);
    }
    /**
    * @Description: 中序遍历
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/13
    */
    private void midOrder(int index){
        if (data==null||data.length==0)
            return;
        if (2*index+1<data.length){
            preOrder(2*index+1);
        }
        System.out.print(data[index]+"  ");
        if (2*index+2<data.length){
            preOrder(2*index+2);
        }
    }
    //重载后序遍历
    public void posterOrder(){
        posterOrder(0);
    }
    /**
    * @Description: 后续遍历
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/14
    */
    private void posterOrder(int index){
        if (data==null||data.length==0)
            return;
        if (2*index+1<data.length){
            preOrder(2*index+1);
        }
        if (2*index+2<data.length){
            preOrder(2*index+2);
        }
        System.out.print(data[index]+"  ");
    }
}
