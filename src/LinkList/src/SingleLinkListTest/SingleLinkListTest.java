package LinkList.src.SingleLinkListTest;

import java.util.Stack;

/**
 * @ClassName SingleLinkListTest
 * @Description:
 * @Author:ypa
 * @Date 2020/12/21 15:52
 * @Version:1.0
 */
public class SingleLinkListTest {
    public static void main(String[] args) {
        //测试数据
        HeroNode node1 = new HeroNode(1, "关羽", 45, null);
        HeroNode node2 = new HeroNode(2, "张飞", 44, null);
        HeroNode node3 = new HeroNode(3, "刘备", 46, null);
        HeroNode node4 = new HeroNode(4, "曹操", 56, null);
        //开始测试
        SingleLinkList singleLinkList = new SingleLinkList();
        /*singleLinkList.addHeroNode(node1);
        singleLinkList.addHeroNode(node2);
        singleLinkList.addHeroNode(node4);
        singleLinkList.addHeroNode(node3);
        singleLinkList.foreach();*/
        System.out.println("---------------------------------------------");
        singleLinkList.addHeroNodeById(node1);
        singleLinkList.addHeroNodeById(node4);
        singleLinkList.addHeroNodeById(node3);
        singleLinkList.addHeroNodeById(node2);
        singleLinkList.foreach();
        System.out.println(singleLinkList.getLength());
        System.out.println("---------------------------------------------");
        singleLinkList.addHeroNodeById(new HeroNode(3, "孙权", 18, null));
        singleLinkList.foreach();
        System.out.println("-------------------------------------------------");
        singleLinkList.updateById(new HeroNode(3, "司马懿", 34, null));
        //singleLinkList.updateById(null);
        singleLinkList.foreach();
        System.out.println("-------------------------------------------------");
        singleLinkList.deleteById(1);
        singleLinkList.foreach();
        System.out.println("-------------------------------------------------------");
        System.out.println("链表的长度是："+singleLinkList.getLength());
        System.out.println("-------------------------------------------------------");
        System.out.println("倒数第1个节点的的信息："+singleLinkList.getLastIndexNode(1));
        System.out.println("-------------------------------------------------------");
        singleLinkList.reverseLinkList();
        System.out.println("反转链表结果如下");
        singleLinkList.foreach();
        System.out.println("-------------------------------------------------------");
        //逆序打印链表
        System.out.println("逆序打印结果如下：");
        singleLinkList.reserveForeach();
        System.out.println("-------------------------------------------------------");

    }
}

class SingleLinkList {
    //头节点，不需要存放数据
    private HeroNode head;
    private int length=0;

    //初始化链表
    public SingleLinkList() {
        //初始化头节点为空
        this.head = new HeroNode();
        head.setNext(null);
        length = 0;
    }

    //添加数据（尾插法）
    public void addHeroNode(HeroNode heroNode) {
        if (heroNode == null) {
            System.out.println("传入的节点数据为空！");
            return;
        }
        //辅助指针
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(heroNode);
        length++;
    }
    //添加数据--按照id添加
    //若已添加，则添加失败，抛出”添加失败异常“
    //自己写的
    /*public void addHeroNodeById(HeroNode heroNode){
        if (heroNode == null) {
            System.out.println("传入的节点数据为空！");
            return;
        }
        //获取id号
        int pos=heroNode.getId();
        HeroNode temp=head;
        //判断是否有重复的id
        while(true){
            if(temp.getNext()==null){
                break;
            }
            if (temp.getId()==pos){
                throw new RuntimeException("目前链表中已有当前id！");
            }
            temp=temp.getNext();
        }
        temp=head;
        //找到相应位置并插入
        while(true){
            if(temp.getNext()==null||temp.getNext().getId()>pos){
                //if(temp.getNext().getId()>pos||temp.getNext()==null)  抛异常
                break;
            }
            temp= temp.getNext();
        }
        heroNode.setNext(temp.getNext());
        temp.setNext(heroNode);
        length++;
    }*/

    //视频里的（有相同id 人家没抛异常）
    //按id插入
    public void addHeroNodeById(HeroNode heroNode) {
        //辅助指针
        HeroNode temp = head;
        //判断是否有相同id标志
        boolean flag = false;

        while (true) {
            if (temp.getNext() == null) {//到达链表尾
                break;
            } else if (temp.getNext().getId() > heroNode.getId()) {//找到要插入的位置
                break;
            } else if (temp.getNext().getId() == heroNode.getId()) {//有相同id
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag == true) {
            System.out.println("已经有 " + heroNode.getId() + ",无法加入链表");
            return;
        }
        heroNode.setNext(temp.getNext());
        temp.setNext(heroNode);
        length++;
    }


    //修改节点,根据id修改
    public void updateById(HeroNode heroNode) {
        if (heroNode == null) {
            throw new RuntimeException("传入的heroNode值为null");
        }
        boolean flag = false;
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            } else if (temp.getId() == heroNode.getId()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag == true) {
            temp.setName(heroNode.getName());
            temp.setAge(heroNode.getAge());
        } else {
            System.out.println("没有找到id=" + heroNode.getId() + "的英雄");
        }
    }

    //删除节点
    public void deleteById(int id) {
        boolean falg = false;
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            } else if (temp.getNext().getId() == id) {
                falg = true;
                break;
            }
            temp=temp.getNext();
        }
        if (falg) {
            temp.setNext(temp.getNext().getNext());
            length--;
        } else {
            System.out.println("没有查到id=" + id + "的节点，删除失败！");
        }

    }

    //遍历链表
    public void foreach() {

        if (head.getNext() == null) {
            System.out.println("空链表，请添加数据再遍历！");
            return;
        }
        //辅助指针
        HeroNode temp = head.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //一定要后移
            temp = temp.getNext();
        }
    }

    //链表的长度
    public int getLength() {
        return length;
    }
    //返回链表倒数第K个节点
    public  HeroNode getLastIndexNode(int n){
        if (head.getNext() == null) {
            return null;
        }
        if(n<=0||n>getLength()){
            return null;
        }
        HeroNode cur=head.getNext();
        for(int i=0;i<getLength()-n;++i){
            cur=cur.getNext();
        }
        return cur;
    }
    //反转单链表
    public void reverseLinkList(){
        if(head.getNext()==null||head.getNext().getNext()==null){
            return;
        }
        //当前指针
        HeroNode cur=head.getNext();
        //当前指针的下一个指针
        HeroNode next=null;
        //辅助节点
        HeroNode temp=new HeroNode();

        //每取出一个节点都放在，辅助节点的后面
        while (cur!=null){
            next=cur.getNext();
            cur.setNext(temp.getNext());
            temp.setNext(cur);
            cur=next;
        }
        head.setNext(temp.getNext());
        temp=null;
    }
    //逆序打印单链表
    public void reserveForeach(){
        if(head.getNext()==null){
            return;
        }
        if(head.getNext().getNext()==null){
            System.out.println(head.getNext());
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp=head.getNext();
        //将链表的元素入栈
        while (temp!=null){
            stack.push(temp);
            //System.out.println(temp);
            temp=temp.getNext();
        }
        //将元素出栈
        while (stack.isEmpty()==false){
            System.out.println(stack.pop());
        }
    }
}
