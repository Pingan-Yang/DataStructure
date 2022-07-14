package LinkList.src.SingleLinkListTest.test;

/**
 * @ClassName DoubleListNode
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/4 20:07
 * @Version:1.0
 */
public class DoubleListNode {
    int val;
    DoubleListNode pre;
    DoubleListNode next;

    public DoubleListNode(int val) {
        this.val = val;
    }

    public DoubleListNode() {
    }

    @Override
    public String toString() {
        return "DoubleListNode{" +
                "val=" + val +
                '}';
    }
}
