package LinkList.src.DoubleLinkListTest;

import LinkList.src.SingleLinkListTest.HeroNode;

/**
 * @ClassName HeroNode2
 * @Description:
 * @Author:ypa
 * @Date 2021/4/15 20:33
 * @Version:1.0
 */
public class HeroNode2 {
    private int id;
    private String name;
    private int age;
    private HeroNode2 next;
    private HeroNode2 pre;

    public HeroNode2() {
    }

    public HeroNode2(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.pre=null;
        this.next=null;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
        this.next = next;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
