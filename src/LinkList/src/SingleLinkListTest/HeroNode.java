package LinkList.src.SingleLinkListTest;

/**
 * @ClassName HeroNode
 * @Description:
 * @Author:ypa
 * @Date 2020/12/21 15:52
 * @Version:1.0
 */
public class HeroNode {
    private int id;
    private String name;
    private int age;
    private HeroNode next;

    public HeroNode() {
    }

    public HeroNode(int id,String name, int age,HeroNode next) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.next=next;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
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

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
