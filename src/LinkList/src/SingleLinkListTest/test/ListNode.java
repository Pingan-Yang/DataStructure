package LinkList.src.SingleLinkListTest.test;

/**
 * @ClassName ListNode
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/4 17:06
 * @Version:1.0
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
