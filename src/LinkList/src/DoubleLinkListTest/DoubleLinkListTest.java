package LinkList.src.DoubleLinkListTest;
import org.junit.Test;
import java.util.Scanner;

/**
 * @ClassName DoubleLinkListTest
 * @Description:
 * @Author:ypa
 * @Date 2021/4/15 20:33
 * @Version:1.0
 */
public class DoubleLinkListTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoubleLinkList list = new DoubleLinkList();
        while (true) {
            System.out.println("请输入相应字符串 add delete update foreach，输入quit退出");
            String s = scanner.next();
            if (s.equals("quit")) {
                break;
            }
            switch (s) {
                case "add":
                    System.out.println("请添加数据");
                    HeroNode2 heroNode2 = new HeroNode2();
                    System.out.println("请输入id:");
                    int id = scanner.nextInt();
                    heroNode2.setId(id);
                    System.out.println("请输入name:");
                    String name = scanner.next();
                    heroNode2.setName(name);
                    System.out.println("请输入age：");
                    int age = scanner.nextInt();
                    heroNode2.setAge(age);
                    list.add(heroNode2);
                    break;
                case "delete":
                    System.out.println("请输入需要删除的id");
                    id = scanner.nextInt();
                    list.deleteById(id);
                    break;
                case "update":
                    System.out.println("请输入需要修改的数据");
                    System.out.println("请添加数据");
                    heroNode2 = new HeroNode2();
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    heroNode2.setId(id);
                    System.out.println("请输入name:");
                    name = scanner.next();
                    heroNode2.setName(name);
                    System.out.println("请输入age：");
                    age = scanner.nextInt();
                    heroNode2.setAge(age);
                    list.update(heroNode2);
                    break;
                case "foreach":
                    System.out.println("开始遍历：");
                    list.foreach();
                    break;

            }
        }

    }
    @Test
    public  void  test(){
        HeroNode2 node1 = new HeroNode2(1, "关羽", 45);
        HeroNode2 node2 = new HeroNode2(2, "张飞", 44);
        HeroNode2 node3 = new HeroNode2(3, "刘备", 46);
        HeroNode2 node4 = new HeroNode2(4, "曹操", 56);
        DoubleLinkList list = new DoubleLinkList();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.foreach();
        System.out.println("---------------------------------");
        HeroNode2 node5 = new HeroNode2(4, "司马懿", 56);
        list.update(node5);
        list.foreach();
    }

}

class DoubleLinkList {
    private HeroNode2 headNode;//头指针

    //初始化链表
    public DoubleLinkList() {
        this.headNode = new HeroNode2();
    }

    /**
     * @Description: 在双链表的末尾添加数据
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/15
     */
    public void add(HeroNode2 heroNode) {
        if (heroNode == null) {
            System.out.println("传入的节点数据为空！");
            return;
        }
        //辅助指针
        HeroNode2 temp = headNode;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(heroNode);
        heroNode.setPre(temp);
    }

    /**
     * @Description: 根据id删除双链表的数据
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/15
     */
    public void deleteById(int id) {
        if (headNode.getNext() == null) {
            return;
        }
        boolean flag = false;
        HeroNode2 temp = headNode.getNext();
        while (temp != null) {
            if (temp.getNext().getId() == id) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.setNext(temp.getNext().getNext());
            if (temp.getNext().getNext() != null)//删除最后一个节点不需要设置它的前驱
                temp.getNext().getNext().setPre(temp);
        }
        return;
    }

    /**
     * @Description: 根据id修改双链表的数据
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/15
     */
    public void update(HeroNode2 heroNode) {
        if (heroNode == null) {
            throw new RuntimeException("传入的heroNode值为null");
        }
        boolean flag = false;
        HeroNode2 temp =headNode;
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

    /**
     * @Description: 遍历整张双链表
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/15
     */
    public void foreach() {
        if (headNode.getNext() == null) {
            System.out.println("空链表，请添加数据再遍历！");
            return;
        }
        //辅助指针
        HeroNode2 temp = headNode.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //一定要后移
            temp = temp.getNext();
        }
    }
}