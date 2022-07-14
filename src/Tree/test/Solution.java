package Tree.test;

import LinkList.src.SingleLinkListTest.test.Node;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.*;

/**
 * @ClassName Solution
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/10 20:15
 * @Version:1.0
 */
public class Solution {
    //----------------二叉树的递归遍历--------------------------
    //前序遍历
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        if (root.left != null) {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }

    //中序遍历
    public void midOrdr(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            midOrdr(root.left);
        }
        System.out.println(root.val);
        if (root.right != null) {
            midOrdr(root.right);
        }
    }

    //后序遍历
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postOrder(root.left);
        }
        if (root.right != null) {
            postOrder(root.right);
        }
        System.out.println(root.val);
    }

    //----------------------二叉树的非递归遍历--------------------------

    /**
     * ①：首先将根节点入栈
     * ②：弹出栈顶元素，并打印
     * ③：若栈顶元素的右子树不为空，则将其右子树入栈
     * ④：若栈顶元素的左子树不为空，则将其左子树入栈
     * ②：重复②-④过程，直至栈空
     *
     * @param root
     */
    public void preOrder1(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode head = stack.pop();
            System.out.println(head.val);
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    /**
     * 1、申请一个栈stack，初始时令cur=head
     * 2、先把cur压入栈中，依次把左边界压入栈中，即不停的令cur=cur.left，
     * 重复步骤2
     * 3、不断重复2，直到为null，从stack中弹出一个节点，记为node
     * 打印node的值，并令cur=node.right,重复步骤2
     * 4、当stack为空且cur为空时，整个过程停止。
     *
     * @param root
     */
    public void midOrder2(TreeNode root) {

        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    /**
     * 1、申请一个栈s1，然后将头节点压入栈s1中。
     * <p>
     * 2、从s1中弹出的节点记为cur，然后依次将cur的左孩子节点和右孩子节点压入s1中。
     * <p>
     * 3、在整个过程中，每一个从s1中弹出的节点都放进s2中。
     * <p>
     * 4、不断重复步骤2和步骤3，直到s1为空，过程停止。
     * <p>
     * 5、从s2中依次弹出节点并打印，打印的顺序就是后序遍历的顺序。
     *
     * @param root
     */
    public void postOrder2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode node = s1.pop();
            s2.push(node);
            if (node.left != null) {
                s1.push(node.left);
            }
            if (node.right != null) {
                s1.push(node.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.println(s2.pop().val);
        }

    }

    //------------------------------二叉树的层序遍历-----------------------------------
    //给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。

    /**
     * 1、首先将二叉树的根节点push到队列中，判断队列不为NULL，就输出队头的元素，
     * 2、判断节点如果有孩子，就将孩子push到队列中，
     * 3、遍历过的节点出队列，
     * 4、循环以上操作，直到Tree == NULL。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        //用于记录返回的结果
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //建立一个队列用于存放节点
        Queue<TreeNode> queue = new LinkedList<>();
        //将头节点放入到队列中
        queue.add(root);
        while (!queue.isEmpty()) {
            // 用于记录每一层的结果
            List<Integer> level = new ArrayList<>();
            //用于当前记录队列大小，也就是这一层有多少个元素
            int curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; ++i) {
                //弹出队列头的元素
                TreeNode cur = queue.poll();
                //将节点放入到当前层的集合中
                level.add(cur.val);
                //将左孩子放入到到队列里
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                //将右孩子放入到队列里
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            //将这一层的集合放入到总的结果中
            res.add(level);
        }
        //返回结果
        return res;
    }

    public int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //建立一个队列去存放节点
        Queue<TreeNode> queue = new LinkedList<>();
        //将根节点入队
        queue.add(root);
        //用于记录当前节点位于哪一层
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        //根节点在第一层
        levelMap.put(root, 1);
        //记录当前层数的节点数
        int curLevelNodes = 0;
        //记录当前所在的层数
        int curLevel = 1;
        //初始化最大层数的节点个数
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            //将对头出列
            TreeNode cur = queue.poll();
            //获取当前元素在的层数
            Integer curNodeLevel = levelMap.get(cur);
            if (curLevel == curNodeLevel) {
                //若当前层数与节点所在的层数相同，则当前节点的总个数加1
                curLevelNodes++;
            } else {
                //若当前层数与节点所在的层数相同,更新最大节点数
                max = Math.max(max, curLevelNodes);
                //当前层数加1
                curLevel++;
                //当前层数总结点数更新为1
                curLevelNodes = 1;
            }
            if (cur.left != null) {
                //将当前节点的左孩子入队，并记录左孩子所在的层数
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                //将当前节点的右孩子入队，并记录左孩子所在的层数
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
        }
        //返回最大宽度
        return max;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        node3.right = node5;


        //preOrder(root);
        //midOrder(root);
        //postOrder(root);

        // preOrder1(root);
        // midOrder2(root);
        //postOrder(root);

        //levelOrder(root);
       /* int maxWidth = maxWidth(root);
        System.out.println(maxWidth);*/

        List<List<Integer>> levelOrder = levelOrder(root);
        for (List<Integer> list : levelOrder) {
            System.out.println(list);
        }
    }

    //----------------------------------二叉树的相关概念及其实现判断---------------------------
    //如何判断一棵树是不是搜索二叉树（Binary Search Tree）
    //定义：
    //若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
    //若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
    //任意节点的左、右子树也分别为二叉查找树；

    /**
     * 递归的方式--利用中序遍历的方式，空间复杂度O（1）
     *
     * @param head
     * @return
     */
    public Integer preValue = Integer.MIN_VALUE;

    public boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        //检查左子树是不是BST
        boolean isLeftBST = isBST(head.left);
        if (!isLeftBST) {
            //如果左子树不是BST则直接返回false
            return false;
        }
        //查看当前节点的值是否比前驱节点的值小，如果是则不是BST
        if (head.val <= preValue) {
            return false;
        } else {
            //如果当前节点大于前驱节点，则把当前节点的值记作preValue
            preValue = head.val;
        }
        //检查右子树是不是BST
        boolean isRightBST = isBST(head.right);
        if (!isRightBST) {
            //如果右子树不是BST，则返回false
            return false;
        }
        //如果左子树和右子树都是BST则返回true
        return true;
    }

    /**
     * 利用一个集合记录中序遍历的值，判断是否是升序
     *
     * @param head
     * @return
     */
    public boolean isBST2(TreeNode head) {
        List<Integer> list = new ArrayList<>();
        //中序遍历，并将遍历的结果放到集合中
        preOrderToList(head, list);
        //预先设置一个preValue，下面好比较
        Integer preValue = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (i <= preValue) {
                return false;
            } else {
                preValue = i;
            }
        }
        return true;
    }

    public class ReturnData {
        boolean isBST;
        int min;
        int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public boolean isBST3(TreeNode head) {
        return check(head).isBST;
    }

    public ReturnData check(TreeNode head) {
        //节点为空不做处理
        if (head == null) {
            return null;
        }
        //返回左子树是不是BST的数据
        ReturnData leftData = check(head.left);
        //返回右子树是不是BST的数据
        ReturnData rightData = check(head.right);

        //判断当前节点是不是BST
        //默认当前节点的最大值、最小值都是它本身
        int min = head.val;
        int max = head.val;

        //如果左子树返回的数据不为空，则更新，当前节点的最小值、最大值
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        //如果右子树返回的数据不为空，则更新，当前节点的最小值、最大值
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        //默认以当前节点的为跟根节点的子树是BST
        boolean isBST = true;
        //如果左子树不不是空，但是左子树不是BST 或者左子树的最大值大于等于当前节点的值，则这棵树不是BST
        if (leftData != null && (!leftData.isBST || leftData.max >= head.val)) {
            isBST = false;
        }
        //如果右子树不不是空，但是右子树不是BST 或者右子树的最小值小于等于当前节点的值，则这颗树不是BST
        if (rightData != null && (!rightData.isBST || rightData.min <= head.val)) {
            isBST = false;
        }
        // 返回当前节点为根节点的树是不是BST的数据
        return new ReturnData(isBST, min, max);
    }

    private void preOrderToList(TreeNode head, List<Integer> list) {
        if (head == null) {
            return;
        }
        //左子树不为空，就遍历左子树
        if (head.left != null) {
            preOrderToList(head.left, list);
        }
        //加入当前节点的值
        list.add(head.val);
        //右子树不为空就遍历右子树
        if (head.right != null) {
            preOrderToList(head.right, list);
        }
    }

    @Test
    public void test01() {
        TreeNode node1 = new TreeNode(62);
        TreeNode node2 = new TreeNode(58);
        TreeNode node3 = new TreeNode(88);
        TreeNode node4 = new TreeNode(47);
        TreeNode node5 = new TreeNode(59);
        TreeNode node6 = new TreeNode(73);
        TreeNode node7 = new TreeNode(99);
        TreeNode node8 = new TreeNode(35);
        TreeNode node9 = new TreeNode(93);
        TreeNode node10 = new TreeNode(37);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node7.left = node9;
        node8.right = node10;

        //boolean isBFT = isBST(node1);
        //boolean isBFT = isBST2(node1);
        boolean isBFT = isBST3(node1);
        System.out.println(isBFT);
    }

    //判断一棵树是不是完全二叉树
    // 在一颗二叉树中，若除最后一层外的其余层都是满的
    // 并且最后一层要么是满的，要么在右边缺少连续若干节点，则此二叉树为完全二叉树（Complete Binary Tree）。
    // 具有n个节点的完全二叉树的深度为logn+1。
    // 深度为k的完全二叉树，至少有2^(k-1)个节点，至多有2^k-1个节点。

    /**
     * ①：任意一个节点有右无左，返回false
     * ②：在①不违规的情况下，如果遇到了第一个节点只有左孩子没有右孩子则后续的节点 都是叶子节点
     * <p>
     * 使用层序遍历的方法去判断
     *
     * @param head
     * @return
     */
    public boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        //建立一个队列，存放层序遍历的值
        Queue<TreeNode> queue = new LinkedList<>();
        //是否遇到左右两个孩子不全的节点
        boolean leaf = false;
        //将头节点加入到队列中
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            //如果全是叶子节点，并且叶子的左不为空 或者右不为空，则表示不是叶子节点 返回false
            //如果节点的左孩子为空 右孩子不为空为 直接返回false
            if ((leaf && (left != null || right != null)) || left == null && right != null) {
                return false;
            }
            //左孩子不为空，则将左孩子也加入到队列中
            if (left != null) {
                queue.add(left);
            }
            //右孩子不为空，则将右孩子加入到队列中
            if (right != null) {
                queue.add(right);
            }
            //如果 节点的左右孩子都为空，则表示该节点是叶子节点，则之后的所有节点 都是叶子节点
            if (left == null && right == null) {
                leaf = true;
            }
        }
        return true;
    }

    @Test
    public void test03() {
        TreeNode node1 = new TreeNode(62);
        TreeNode node2 = new TreeNode(58);
        TreeNode node3 = new TreeNode(88);
        TreeNode node4 = new TreeNode(47);
        TreeNode node5 = new TreeNode(59);
        TreeNode node6 = new TreeNode(73);
        TreeNode node7 = new TreeNode(99);
        TreeNode node8 = new TreeNode(35);
        TreeNode node9 = new TreeNode(93);
        TreeNode node10 = new TreeNode(37);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.left = node10;

        // boolean isCBT = isCBT(node1);
        //System.out.println(isCBT);
        levelOrder(node1);
    }

    //判断一棵树是不是平衡二叉树（AVL树）
    //定义：
    //平衡二叉树是一颗二叉排序树：满足二叉排序树的定义
    //  ①：若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
    //  ②：若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
    //其次它的每一个节点的左右子树的高度差之多等于1

    public boolean isAVL(TreeNode node) {
        return process(node).isBalance;
    }

    public class ReturnType {
        public boolean isBalance;
        public int height;

        public ReturnType(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public ReturnType process(TreeNode head) {
        //如果是空 则表明是平衡二叉树。且高度为0
        if (head == null) {
            return new ReturnType(true, 0);
        }
        //返回左子树是否是AVL树的数据
        ReturnType leftData = process(head.left);
        //返回右子树是否是AVL树的数据
        ReturnType rightData = process(head.right);

        //计算以当前节点的根节点的高度
        int height = Math.max(leftData.height, rightData.height) + 1;
        //确定以当前节点为根节点的树是AVL树
        boolean isBalanced = leftData.isBalance && rightData.isBalance && Math.abs(leftData.height - rightData.height) <= 1;
        //返回以当前节点为根节点的树是否是AVL树的数据
        return new ReturnType(isBalanced, height);

    }

    @Test
    public void test04() {

        TreeNode node1 = new TreeNode(62);
        TreeNode node2 = new TreeNode(47);
        TreeNode node3 = new TreeNode(88);
        TreeNode node4 = new TreeNode(35);
        TreeNode node5 = new TreeNode(51);
        TreeNode node6 = new TreeNode(73);
        TreeNode node7 = new TreeNode(99);
        TreeNode node8 = new TreeNode(37);
        TreeNode node9 = new TreeNode(58);
        TreeNode node10 = new TreeNode(93);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.right = node8;
        node5.right = node9;
        node7.left = node10;


        boolean isAVL = isAVL(node1);
        System.out.println(isAVL);
    }

    //判断这棵树是不是满二叉树（CompleteTree）
    //定义：
    // 一棵深度为k，且有2^k-1个节点的二叉树，称为满二叉树。
    // 这种树的特点是每一层上的节点数都是最大节点数。
    //性质：
    //共有2^k-1个节点
    //节点个数一定为奇数
    //第i层有2^i-1个节点
    //共有2^(k-1)个叶子

    /**
     * 利用二叉树的深度和总节点数的关系进行判断。
     * @param head
     * @return
     */
    public boolean isCT(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        //记录当前的深度
        int curleval = 1;
        //记录总的节点数
        int sumNodes = 0;
        //记录每一个节点在哪一层
        Map<TreeNode, Integer> map = new HashMap<>();
        queue.add(head);
        map.put(head, 1);
        //利用层序遍历，将树的总深度和总的节点数找到
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            sumNodes++;
            Integer curNodeLevel = map.get(node);
            if (curleval != curNodeLevel) {
                curleval++;
            }
            if (node.left != null) {
                map.put(node.left, curNodeLevel + 1);
                queue.add(node.left);
            }
            if (node.right != null) {
                map.put(node.right, curNodeLevel + 1);
                queue.add(node.right);
            }
        }
        // System.out.println("deepth=="+curleval+",sumNodes="+sumNodes);

        //利用 完全二叉树的定义返回结果
        return sumNodes == Math.pow(2, curleval) - 1 ? true : false;
    }


    @Test
    public void test05() {
        TreeNode node1 = new TreeNode(62);
        TreeNode node2 = new TreeNode(47);
        TreeNode node3 = new TreeNode(88);
        TreeNode node4 = new TreeNode(35);
        TreeNode node5 = new TreeNode(51);
        TreeNode node6 = new TreeNode(73);
        TreeNode node7 = new TreeNode(99);
        TreeNode node8 = new TreeNode(37);
        TreeNode node9 = new TreeNode(58);
        TreeNode node10 = new TreeNode(93);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.right = node10;

        boolean isCT = isCT(node1);
        System.out.println(isCT);

    }


    //给定二叉树的节点node1和node2，找到他们最低公共祖先节点

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == q) {
            return p;
        }
        //用于记录所有的节点的父节点
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        //头节点的父节点是它自己
        parentMap.put(root, root);
        //将整棵树遍历，分别记录他们的父节点
        findParentTreeNode(root, parentMap);
        //用于记录从p开始打root的一条父亲链
        Set<TreeNode> pParent = new HashSet<>();
        while (p != parentMap.get(p)) {
            pParent.add(p);
            p = parentMap.get(p);
        }

        //用于记录从q开始到root的提议奥父亲链
        Set<TreeNode> qParent = new HashSet<>();
        while (q!=parentMap.get(q)){
            qParent.add(q);
            //如果从q开始往上找的过程中，发现了第一个出现在一个在p父亲链中的节点，则就是最低公共父节点
            if (pParent.contains(q)){
                return q;
            }
            q=parentMap.get(q);
        }

        //如果两条链都走到头了，则证明root是他们的最低公共父节点
        return root;
    }

    private void findParentTreeNode(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        parentMap.put(root.left, root);
        parentMap.put(root.right, root);
        findParentTreeNode(root.left, parentMap);
        findParentTreeNode(root.right, parentMap);
    }

    //使用递归的方式

    /**
     * 只有两种情况
     * ①：p是q的最低公共父节点，或者q是p的最低公共父节点
     * ②：p和q在两个分支上，互相不是对方的最低公共父节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q){
        //base case
        if (root==null||root==p||root==q){
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left!=null&&right!=null){
            return root;
        }
        return left!=null?left:right;
    }

    //在二叉树中找到一个节点的后继节点（中序遍历的顺序）
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root==null||p==null){
            return null;
        }
        //使用集合记录中序遍历的顺序
        List<TreeNode> treeNodes=new ArrayList<>();
        inorder(root,treeNodes);
        //在集合中查找中p的后继节点
        for (int i=0;i<treeNodes.size();++i){
            if (treeNodes.get(i)==p){
                if (i+1<treeNodes.size()){
                    return treeNodes.get(i+1);
                }else{
                    return null;
                }
            }
        }
        return null;
    }

    private void inorder(TreeNode root, List<TreeNode> treeNodes) {
        if (root==null){
            return;
        }
        if (root.left!=null){
            inorder(root.left, treeNodes);
        }
        treeNodes.add(root);
        if (root.right!=null){
            inorder(root.right, treeNodes);
        }
    }

    //二叉树的序列化与反序列化

    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
     * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。
     * 这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);
        return root;
    }

    @Test
    public void test06(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;

        String res = serialize(node1);
        System.out.println(res);
    }

    // 折纸问题
    //请把一段纸条竖着放在桌子上，然后从纸条下方向上方对折一次，压出折痕后展开
    //如果折痕是凹下去的，即折痕突起的方向指向纸条的背面
    //如果从纸条的下边向上方连续对折两次，压出折痕后展开，此时有三条折痕，从下到上依次是下折痕、下折痕、上折痕
    //给定一个输入参数N，代表纸条都是从下边向上方连续对折N次
    //请从上到下打印所有折痕方向
    public void printAllFolds(int N){
        printProcess(1,N,true);
    }

    private void printProcess(int i,int n, boolean down) {
        if (i>n){
            return;
        }
        printProcess(i+1,n,true);
        System.out.print(down?i+"凹 ":i+"凸  ");
        printProcess(i+1, n, false);
    }

    @Test
    public void test07(){
        int N=3;
        printAllFolds(N);
    }
}
