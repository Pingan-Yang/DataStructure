package Stack;


import org.junit.Test;

/**
 * @ClassName ArrayStackTest
 * @Description:
 * @Author:ypa
 * @Date 2021/4/19 19:33
 * @Version:1.0
 */
public class ArrayStackTest {
    @Test
    public void test01() {
        ArrayStack stack = new ArrayStack(5);
        for (int i = 0; i < 5; ++i) {
            stack.push(i);
        }
        System.out.println("栈的容量：" + stack.getCapacity());
        System.out.println("栈的大小：" + stack.getSize());
        System.out.println("栈为空：" + stack.isEmppty());
        System.out.println("栈为满：" + stack.isFull());
        stack.foreach();
        for (int i = 0; i < 5; ++i) {
            stack.pop();
        }
        System.out.println("栈的容量：" + stack.getCapacity());
        System.out.println("栈的大小：" + stack.getSize());
        System.out.println("栈为空：" + stack.isEmppty());
        System.out.println("栈为满：" + stack.isFull());

    }
}

class ArrayStack {
    int top;
    int capacity;
    int[] stack;
    int size;

    /**
     * @Description: 初始化栈
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        top = -1;
        stack = new int[capacity];
        size = 0;
    }

    /**
     * @Description: 判断栈是否为空
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public boolean isEmppty() {
        return top == -1;
    }

    /**
     * @Description: 判断栈是否为满
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public boolean isFull() {
        return top == capacity - 1;
    }

    /**
     * @Description: 将数据压入栈中
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public void push(int data) {
        if (isFull()) {
            throw new RuntimeException("栈满，无法入栈");
        }
        top++;
        stack[top] = data;
        size++;
    }

    /**
     * @Description: 将数据弹出栈
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public void pop() {
        if (isEmppty()) {
            throw new RuntimeException("栈空,无法出栈");
        }
        top--;
        size--;
    }

    /**
     * @Description: 获取栈顶元素
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public int getTop() {
        if (isEmppty())
            throw new RuntimeException("栈空，无栈顶元素");
        return stack[top];
    }

    /**
     * @Description: 遍历栈中的元素
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public void foreach() {
        if (isEmppty())
            throw new RuntimeException("栈空，无法遍历");
        int temp = top;
        for (; temp >= 0; --temp) {
            System.out.print(stack[temp] + "  ");
        }
        System.out.println();
    }

    /**
     * @Description: 获取栈的实际大小
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public int getSize() {
        return size;
    }

    /**
     * @Description: 获取栈的容量
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public int getCapacity() {
        return capacity;
    }
}