package Tree.test;

/**
 * @ClassName TreeNode
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/10 20:14
 * @Version:1.0
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}
