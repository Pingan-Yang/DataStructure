package LinkList.src.SingleLinkListTest.test;



import org.junit.Test;


import java.util.*;


/**
 * @ClassName Solution
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/4 17:06
 * @Version:1.0
 */
public class Solution {
    //给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //新链表的头
        ListNode newHead = new ListNode();
        //指向原链表当前节点的下一个节点
        ListNode next = head.next;
        //一个一个节点断开，重新插入到新链表头部后面的一节点
        while (head != null) {
            head.next = newHead.next;
            newHead.next = head;
            head = next;
            if (next != null) {
                next = next.next;
            }
        }
        return newHead.next;
    }

    @Test
    public void test01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode reverseList = reverseList(node1);
        ListNode p = reverseList;
        while (p != null) {
            System.out.println(p);
            p = p.next;
        }

    }

    public DoubleListNode reverseDoubleListNode(DoubleListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //新建一个双链表的头
        DoubleListNode newDoubleListHead = new DoubleListNode();
        //记录原链表现在指针的下一个位置
        DoubleListNode next = head.next;
        while (head != null) {
            //记录新链表头部的下一个位置
            DoubleListNode newDoubleListHeadNext = newDoubleListHead.next;
            //将原链表的当前指针的next指向新链表当前指针的下一个位置
            head.next = newDoubleListHead.next;
            //新链表的当前指针的next指向原链表的现在位置
            newDoubleListHead.next = head;
            //将原链表的当前指针的pre指向新链表的头部
            head.pre = newDoubleListHead;
            if (newDoubleListHeadNext != null) {
                //将新链表的原来的下一个位置的指针的pre指向原链表的当前指针的位置
                newDoubleListHeadNext.pre = head;
            }
            //原链表指针后移
            head = next;
            if (next != null) {
                //记录原链表当前指针的下一个位置
                next = next.next;
            }
        }
        return newDoubleListHead.next;
    }

    @Test
    public void test02() {
        DoubleListNode node1 = new DoubleListNode(1);
        DoubleListNode node2 = new DoubleListNode(2);
        DoubleListNode node3 = new DoubleListNode(3);
        node1.next = node2;
        node2.next = node3;
        node2.pre = node1;
        node3.pre = node2;
        DoubleListNode head = reverseDoubleListNode(node1);
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }

    //给定两个有序链表的头指针 head1 和 head2，打印两个链表的公共部分。
    public void CommonPrint(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return;
        }
        while (head1 != null && head2 != null) {
            //如果两部分值相等，则打印节点信息
            if (head1.val == head2.val) {
                System.out.println(head1);
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.val < head2.val) {
                //head1的节点值小则head1向后移动
                head1 = head1.next;
            } else {
                //head2的节点值小则head2向后移
                head2 = head2.next;
            }
        }
    }

    @Test
    public void test() {
        ListNode head1 = new ListNode(1);
        ListNode head1Node2 = new ListNode(2);
        ListNode head1Node3 = new ListNode(3);
        ListNode head1Node4 = new ListNode(4);
        head1.next = head1Node2;
        head1Node2.next = head1Node3;
        head1Node3.next = head1Node4;

        ListNode head2 = new ListNode(2);
        ListNode head2Node2 = new ListNode(3);
        ListNode head2Node3 = new ListNode(4);
        ListNode head2Node4 = new ListNode(5);
        head2.next = head2Node2;
        head2Node2.next = head2Node3;
        head2Node3.next = head2Node4;

        CommonPrint(head1, head2);
    }


    //给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
    // 如果是，返回 true ；否则，返回 false 。
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        //如果不考虑空间复杂度 ，可以用栈来实现
        Stack<ListNode> listNodeStack = new Stack<>();
        ListNode head2 = head;
        while (head != null) {
            //将链表所有数据入栈
            listNodeStack.push(head);
            head = head.next;
        }

        while (head2 != null) {
            //如果栈弹出的元素与链表当前的元素值不同则返回false
            if (head2.val != listNodeStack.pop().val) {
                return false;
            }
            head2 = head2.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        //考虑空间复杂度，使用空间复杂度为O(1)的算法
        //使用快慢指针
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {//循环走完，p1到了中点的位置
            p1 = p1.next;//p1一次走一步===>p1  mid
            p2 = p2.next.next;//p2一次走两步===> p2 end
        }
        p2 = p1.next;
        p1.next = null;
        ListNode p3 = null;

        while (p2 != null) {//右半边逆序eg:1->2->3->2->1==>1->2->3<-2<-1
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        p3 = p1;//记录尾部指针的位置
        p2 = head;
        while (p1 != null && p2 != null) {//检查是否是回文
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //将链表变回去
        p1 = p3.next;
        p3.next = null;
        while (p1 != null) {
            p2 = p1.next;
            p1.next = p3;
            p3 = p1;
            p1 = p2;
        }
        return true;
    }

    @Test
    public void test03() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // boolean isPalindrome = isPalindrome(node1);
        boolean isPalindrome = isPalindrome2(node1);
        System.out.println(isPalindrome);
    }

    //分割链表：将单链表按照某值划分成左边小、中间相等、右边大的形式

    public ListNode splitLinkList(ListNode head, int num) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        ListNode[] nodes = new ListNode[len];
        int j = 0;
        while (p != null) {
            nodes[j++] = p;
            p = p.next;
        }
        fastSort(nodes, 0, len - 1, num);
        for (int i = 0; i < nodes.length - 1; ++i) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[len - 1].next = null;

        return nodes[0];

    }

    private void fastSort(ListNode[] nodes, int left, int right, int num) {
        if (left < right) {
            int[] p = partition(nodes, left, right, num);
            fastSort(nodes, left, p[0] - 1, num);
            fastSort(nodes, p[1] + 1, right, num);
        }
    }


    private int[] partition(ListNode[] nodes, int left, int right, int num) {
        int leftArea = left - 1;
        int rightArea = right;
        while (left < rightArea) {
            if (nodes[left].val < num) {
                swap(nodes, left++, ++leftArea);
            } else if (nodes[left].val == num) {
                left++;
            } else {
                swap(nodes, left, --rightArea);
            }
        }

        return new int[]{leftArea + 1, rightArea};
    }

    private void swap(ListNode[] nodes, int i, int j) {
        ListNode temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }

    @Test
    public void test04() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(7);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = splitLinkList(node1, 4);
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }
    //给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，
    // 该指针可以指向链表中的任何节点或空节点
    //构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，
    // 其中每个新节点的值都设为其对应的原节点的值。
    // 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
    // 并使原链表和复制链表中的这些指针能够表示相同的链表状态。
    // 复制链表中的指针都不应指向原链表中的节点 。

    /**
     * 可以使用hash表去存储
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (head != null) {
            map.put(head, new Node(head.val));
            head = head.next;
        }
        head = p;
        while (head != null) {
            map.get(head).next = map.get(head.next);
            map.get(head).random = map.get(head.random);
            head = head.next;
        }
        return map.get(p);
    }

    //使用空间复杂度为O(1)的算法
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            Node node = new Node(cur.val);
            node.next = next;
            cur.next = node;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    @Test
    public void test05() {
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        Node node = copyRandomList2(node1);
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    //给你两个单链表的头节点 headA 和 headB ，
    // 请你找出并返回两个单链表相交的起始节点。
    // 如果两个链表不存在相交节点，返回 null 。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode res = null;
        int lenA = 0;
        int lenB = 0;
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != null) {
            lenA++;
            p1 = p1.next;
        }
        while (p2 != null) {
            lenB++;
            p2 = p2.next;
        }
        p1 = headA;
        p2 = headB;
        int count = 1;
        while (count <= lenA + lenB) {
            if (p1 == p2) {
                return p1;
            }
            if (p1.next == null && p2.next == null) {
                return res;
            } else if (p1.next == null) {
                p1 = headB;
                p2 = p2.next;
            } else if (p2.next == null) {
                p2 = headA;
                p1 = p1.next;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
            count++;
        }
        return res;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


    @Test
    public void test06() {
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(4);

        ListNode head2 = new ListNode(3);
        // head2.next = node4;

        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode intersectionNode = getIntersectionNode2(head1, head2);
        System.out.println(intersectionNode);
    }

    //给定一个链表，返回链表开始入环的第一个节点。
    // 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。
    // 如果链表无环，则返回 null。
    public ListNode detectCycle(ListNode head) {
        //可以使用hash表
        if (head == null || head.next == null) {
            return null;
        }
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur= cur.next;
        }
        return null;
    }

    //使用快慢指针--找到一个环的入环节点，如果无环，则返回null
    public ListNode detectCycle2(ListNode head){
        if (head == null || head.next == null||head.next.next==null) {
            return null;
        }
        //快指针一次走两步，慢指针一次走一步。
        ListNode slow=head.next;//慢指针 走一步
        ListNode fast=head.next.next;//快指针走两步

        while (slow!=fast){
            //没有环 则返回null
            if (fast.next==null||fast.next.next==null){
                return null;
            }
            //快指针一次走两步，慢指针一次走一步。
            slow=slow.next;
            fast=fast.next.next;
        }
        //快慢指针在环上相遇，则将快指针 重新指到链表头部
        fast=head;

        while (fast!=slow){
            //此时，让快慢指针一次都走一步，则再次相遇，必是入环的第一个节点
            fast=fast.next;
            slow=slow.next;
        }

        return slow;
    }


    @Test
    public void test07() {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode node = detectCycle2(node1);
        System.out.println(node);
    }

    // 链表相交的一些列问题
    //给定的两个链表，可能有环也可能无环，头节点head1和head2.请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
    //如果不相交，返回null
    //要求，如果两个来链表长度为n，时间复杂度为O（N），额外空间复杂度为O（1）

    /**
     * 两个链表 无环的情况
     * @param head1
     * @param head2
     * @return
     */
    public ListNode noLoop(ListNode head1,ListNode head2){
        if (head1==null||head2==null){
            return null;
        }
        if (head1==head2){
            return head1;
        }

        ListNode cur1=head1;
        ListNode cur2=head2;
        int len1=0;
        int len2=0;

        while (cur1!=null){
            len1++;
            cur1=cur1.next;
        }
        while (cur2!=null){
            len2++;
            cur2=cur2.next;
        }

        //cur1作为长链表的头
        cur1=len1>len2?head1:head2;
        cur2=len1>len2?head2:head1;
        //两链表值之间长度的差值
        int n=Math.abs(len1-len2);
        //让长链表的指针移动差值步
        while (n!=0){
            cur1=cur1.next;
            n--;
        }
        //长链表与短链表一起移动，若有交点，必在焦点处相遇
        while (cur1!=null&&cur2!=null&&cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }

    //使用快慢指针--找到一个环的入环节点，如果无环，则返回null
    public ListNode getLoopNode(ListNode head){
        if (head == null || head.next == null||head.next.next==null) {
            return null;
        }
        //快指针一次走两步，慢指针一次走一步。
        ListNode slow=head.next;//慢指针 走一步
        ListNode fast=head.next.next;//快指针走两步

        while (slow!=fast){
            //没有环 则返回null
            if (fast.next==null||fast.next.next==null){
                return null;
            }
            //快指针一次走两步，慢指针一次走一步。
            slow=slow.next;
            fast=fast.next.next;
        }
        //快慢指针在环上相遇，则将快指针 重新指到链表头部
        fast=head;

        while (fast!=slow){
            //此时，让快慢指针一次都走一步，则再次相遇，必是入环的第一个节点
            fast=fast.next;
            slow=slow.next;
        }

        return slow;
    }


    /**
     * 两个链表 有环情况
     * @param head1
     * @param head2
     * @param loop1
     * @param loop2
     * @return
     */
    public ListNode bothLoop(ListNode head1,ListNode head2,ListNode loop1,ListNode loop2){
        ListNode cur1=null;
        ListNode cur2=null;
        if (loop1==loop2){
            //情况一：如果loop1==loop2
            // 表明环上 的节点相同用 ，也就是相同的入环点，但是可能不是相交的第一个节点
            //所以可以用前面的无环的套路去判断前面一部分是否有相交点。
            cur1=head1;
            cur2=head2;
            int len1=0;
            int len2=0;
           while (cur1!=loop1){
               len1++;
               cur1=cur1.next;
           }
           while (cur2!=loop2){
               len2++;
               cur2=cur2.next;
           }
           int n=len1-len2;
           cur1=n>0?head1:head2;
           cur2=n<0?head1:head2;
           n=Math.abs(n);
           while (n!=0){
               cur1=cur1.next;
               n--;
           }
           while (cur1!=cur2){
               cur1=cur1.next;
               cur2=cur2.next;
           }
           return cur1;
        }else{
            //不同的入环点
            cur1=loop1.next;
            while (cur1!=loop1){
                if (cur1==loop2){
                    return  loop1;
                }
                cur1=cur1.next;
            }
            return null;
        }
    }
    public ListNode getIntersectNode3(ListNode head1, ListNode head2){
        if (head1==null||head2==null){
            return null;
        }
        ListNode loop1=getLoopNode(head1);
        ListNode loop2=getLoopNode(head2);
        if (loop1==null&&loop2==null){
            return noLoop(head1,head2);
        }
        if (loop1!=null&&loop2!=null){
            return  bothLoop(head1,head2,loop1,loop2);
        }

        return null;
    }

}
