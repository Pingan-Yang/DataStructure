package LinkList.src.SingleLinkListTest.test;

/**
 * @ClassName Node
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/6 19:29
 * @Version:1.0
 */
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", random=" + random +
                '}';
    }
}
