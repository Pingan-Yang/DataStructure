package LinkList.src.Josepfu;

/**
 * @ClassName Boy
 * @Description:
 * @Author:ypa
 * @Date 2021/4/16 20:25
 * @Version:1.0
 */
public class Boy {
    private int id;
    private Boy next;

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                '}';
    }
}
