package Tree.binarytree;

import org.junit.Test;



/**
 * @ClassName BinaryTree
 * @Description:
 * @Author:ypa
 * @Date 2021/5/11 20:03
 * @Version:1.0
 */
public class BinaryTree {
    @Test
    public void test() {
        //先手动创建二叉树，后面在用递归
        Node node1 = new Node(1, "张三");
        Node node2 = new Node(2, "李四");
        Node node3 = new Node(3, "王五");
        Node node4 = new Node(4, "赵六");
        Node node5 = new Node(5, "王八");
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setRight(node4);
        node2.setLeft(node5);

        Tree tree = new Tree(node1);
       /* tree.preOrder();
        System.out.println("---------------------");
        tree.midOrder();
        System.out.println("---------------------");
        tree.posterOrder();
        System.out.println("----------------------");*/
       /* Node node = tree.preOrderFind(3);
        System.out.println(node);*/
        /*Node node = tree.midOrderFind(1);
        System.out.println(node);*/
       /* Node node = tree.posterOrderFind(1);
        System.out.println(node);*/

        /*tree.deleteNode(1);
        tree.preOrder();*/
        tree.deleteNode2(1);
        tree.preOrder();

    }
}

class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    /**
     * @Description: 前序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public void preOrder() {
        if (root == null) {
            System.out.println("root为空，无法遍历");
        } else {
            this.root.preOrder();
        }
    }

    /**
     * @Description: 中序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public void midOrder() {
        if (root == null) {
            System.out.println("root为空，无法遍历");
        } else {
            this.root.midOrder();
        }
    }

    /**
     * @Description: 后续遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public void posterOrder() {
        if (root == null) {
            System.out.println("root为空，无法遍历");
        } else {
            this.root.posterOrder();
        }
    }

    /**
     * @Description: 前序查找
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public Node preOrderFind(int id) {
        if (root == null) {
            System.out.println("root为空，无法查找");
            return null;
        }
        Node.count = 0;
        Node node = this.root.preOrderFind(id);
        System.out.println("前序查找一共查找了：" + Node.count + "次");
        return node;
    }

    /**
     * @Description: 中序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public Node midOrderFind(int id) {
        if (root == null) {
            System.out.println("root为空，无法查找");
            return null;
        }
        Node.count = 0;
        Node node = this.root.midOrderFind(id);
        System.out.println("中序查找一共查找了：" + Node.count + "次");
        return node;
    }

    /**
     * @Description: 后序查找
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public Node posterOrderFind(int id) {
        if (root == null) {
            System.out.println("root为空，无法查找");
            return null;
        }
        Node.count = 0;
        Node node = this.root.posterOrderFind(id);
        System.out.println("后序查找一共查找了：" + Node.count + "次");
        return node;
    }

    public void deleteNode(int id) {
        //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
        if (root != null) {
            if (root.getId() == id) {
                System.out.println("删除成功");
                root = null;
                return;
            }
            //递归删除
            this.root.deleteNode(id);
        } else {
            System.out.println("root为空，无法删除");
        }
    }

    /**
     * @Description: 删除节点-----不要删除子树(根节点的左右子节点的子节点均有值--暂时不处理)--这个方法有问题
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/12
     */
    public void deleteNode2(int id) {
        if (this.root != null) {
            if (this.root.getId() == id) {
                if (this.root.getLeft() == null && this.root.getRight() == null) {
                    System.out.println("删除成功");
                    root = null;
                    return;
                }
                if (this.root.getLeft() != null && this.root.getRight() == null) {
                    System.out.println("删除成功");
                    root = this.root.getLeft();
                    return;
                }
                if (this.root.getRight() != null && this.root.getLeft() == null) {
                    System.out.println("删除成功");
                    root = this.root.getRight();
                    return;
                }
                if (this.root.getLeft() != null && this.root.getRight() != null) {
                    System.out.println("暂时不处理");
                    return;
                }
            }
            this.root.deleteNode2(id);
        } else {
            System.out.println("root为空，无法删除");
        }
    }
}

class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;
    public static int count = 0;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * @Description: 前序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * @Description: 中序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * @Description: 后序遍历
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public void posterOrder() {
        if (this.left != null) {
            this.left.posterOrder();
        }
        if (this.right != null) {
            this.right.posterOrder();
        }
        System.out.println(this);
    }

    /**
     * @Description: 前序查找
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public Node preOrderFind(int id) {
        count += 1;
        if (id == this.id)
            return this;
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        Node temp = null;
        if (this.left != null) {
            temp = this.left.preOrderFind(id);
        }
        if (temp != null)
            return temp;
        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            temp = this.right.preOrderFind(id);
        }
        return temp;
    }

    /**
     * @Description: 中序查找
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public Node midOrderFind(int id) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        Node temp = null;
        if (this.left != null)
            temp = this.left.midOrderFind(id);
        if (temp != null)
            return temp;
        count +=1;
        //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if (this.id == id)
            return this;
        //否则继续进行右递归的中序查找
        if (this.right != null)
            temp = this.right.midOrderFind(id);
        return temp;
    }

    /**
     * @Description: 后序查找
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/11
     */
    public Node posterOrderFind(int id) {
        Node temp = null;
        if (this.left != null)
            temp = this.left.posterOrderFind(id);
        if (temp != null)
            return temp;
        if (this.right != null)
            temp = this.right.posterOrderFind(id);
        if (temp != null)
            return temp;
        count += 1;

        return this.id == id ? this : temp;

    }

    /**
     * @Description: 删除节点-----仅限于叶子节点
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/5/12
     */

    //递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void deleteNode(int id) {
        //思路
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，
		      而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null;
			  并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;
			  并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.

		 */
        if (this.left != null && this.left.id == id) {
            System.out.println("删除成功");
            this.left = null;
            return;
        }
        if (this.right != null && this.right.id == id) {
            System.out.println("删除成功");
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.deleteNode(id);
        }
        if (this.right != null) {
            this.right.deleteNode(id);
        }
    }
    /**
    * @Description: 删除节点-----不要删除整个子树------这个方法有问题
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/5/12
    */
    /*
        如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则,
        假如规定如下:
            如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
            如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A。

    * */
    public void deleteNode2(int id){
        if (this.left!=null&&this.left.id==id){
            if (this.left.left==null&&this.left.right==null){
                System.out.println("删除成功");
                this.left=null;
                return;
            }
            if (this.left.left!=null&&this.left.right==null){
                System.out.println("删除成功");
                this.left=this.left.left;
                return;
            }
            if (this.left.right!=null&&this.left.left==null){
                System.out.println("删除成功");
                this.left=this.left.right;
                return;
            }
            //这里应该怎么处理
            if (this.left.left!=null&&this.left.right!=null){
                System.out.println("删除成功");
                Node temp=this.left.right;
                this.left=this.left.left;
                this.left.right=temp;
            }
        }
        if (this.right!=null&&this.right.id==id){
            if (this.right.left==null&&this.right.right==null){
                System.out.println("删除成功");
                this.right=null;
                return;
            }
            if (this.right.left!=null&&this.right.right==null){
                System.out.println("删除成功");
                this.right=this.right.left;
                return;
            }
            if (this.right.right!=null&&this.right.left==null){
                System.out.println("删除成功");
                this.right=this.right.right;
                return;
            }
            if (this.right.left!=null&&this.right.right!=null){
                System.out.println("删除成功");
                Node temp=this.right.right;
                this.left=this.right.left;
                this.right.right=temp;
            }
        }
        if (this.left!=null){
            this.left.deleteNode2(id);
        }
        if (this.right!=null){
            this.right.deleteNode2(id);
        }
    }
}
