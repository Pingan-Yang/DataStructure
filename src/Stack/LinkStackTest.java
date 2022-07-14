package Stack;

import org.junit.Test;


/**
 * @ClassName LinkStackTest
 * @Description:
 * @Author:ypa
 * @Date 2021/4/19 19:55
 * @Version:1.0
 */
public class LinkStackTest {
    @Test
    public void test(){
        LinkStack stack = new LinkStack();
        for (int i=0;i<5;++i){
            stack.push(new IntDate(i));
        }

        System.out.println("栈的大小：" + stack.getSize());
        System.out.println("栈为空：" + stack.isEmpty());
        int size=stack.getSize();
        for (int i=0;i<size;++i){
            System.out.println(stack.getTop());
            stack.pop();
        }
        System.out.println("栈的大小：" + stack.getSize());
        System.out.println("栈为空：" + stack.isEmpty());
    }
}

class LinkStack {
    private IntDate top = new IntDate();
    private IntDate botten=new IntDate();
    private int size = 0;
    /**
    * @Description: 判断栈是个否为空
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/4/19
    */
    public boolean isEmpty(){
        return size==0;
    }
    /**
    * @Description: 返回栈的大小
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/4/19
    */
    public int getSize(){
        return  size;
    }
    /**
    * @Description: 将数据压入栈中
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/4/19
    */
    public void push(IntDate data){
        if(data==null)
            throw  new RuntimeException("传入的数据不可以为空");
        top.next=data;
        top=data;
        top.next=null;
        size++;
        if(size==1){
            botten=top;
        }

    }
    /**
    * @Description: 出栈
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/4/19
    */
    public void pop(){
        if(isEmpty())
            throw new RuntimeException("栈空，无法出栈");
        IntDate temp = new IntDate();
        temp=botten;
        if(temp.next==null){
            top=null;
            size--;
            return;
        }
        while (temp.next!=top){
            temp=temp.next;
        }

        top=temp;
        top.next=null;
        size--;
    }
    /**
    * @Description: 获取栈顶元素
    * @Param:
    * @return:
    * @Author: ypa
    * @Date: 2021/4/19
    */
    public IntDate getTop(){
        return top;
    }

}
class IntDate{
    int data=0;
    IntDate next=null;

    public IntDate(int data) {
        this.data = data;
        next=null;
    }

    public IntDate() {
    }

    @Override
    public String toString() {
        return "IntDate{" +
                "data=" + data +
                '}';
    }
}
