package Array.src.array_queue;


import org.junit.Test;


/**
 * @ClassName ArrayQueueTest
 * @Description: 用数组模拟一个队列
 * @Author:ypa
 * @Date 2020/12/11 20:41
 * @Version:1.0
 */
public class ArrayQueueTest {
    @Test
    public void test() {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        for (int i = 0; i < 10; ++i) {
            arrayQueue.push(i);
        }
        System.out.println("队列是否为满：" + arrayQueue.isFull());
        System.out.println("队列是否空：" + arrayQueue.isEmpty());
        arrayQueue.show();
        for (int i = 0; i < 10; ++i) {
            System.out.println("队列首部元素："+arrayQueue.getTop());
            arrayQueue.pop();
            System.out.println("队列长度：" + arrayQueue.getLength());
        }
        System.out.println("队列是否为满：" + arrayQueue.isFull());
        System.out.println("队列是否空：" + arrayQueue.isEmpty());

        //此时会出错   因为不是一个环形队列   只能用一次   最后rear始终在capacity-1的地方  无法 后移加数据
        //arrayQueue.push(1000);
    }

}

//------------问题：数组只能用一次----------------------------------
class ArrayQueue {
    private int capacity;//队列容量
    private int length;//队列长度
    private int front;//头指针------指向队列头元素的前一个位置
    private int rear;//尾指针-------指向尾元素
    private int[] queue;//用来存放元素的数组

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        length = 0;
        front = rear = -1;//初始时令 front rear 都为-1
        queue = new int[capacity];
    }

    //清空队列
    public void clear() {
        if (isEmpty()) {
            throw new RuntimeException("队列已为空");
        }
        while (!isEmpty()) {
            pop();
        }
    }

    //获取队列长度
    public int getLength() {
        return length;
    }

    //判断是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == capacity - 1 && front == -1;//return rear==capacity-1 error//这样  先满队列  在pop()会显示满队列
    }

    //返回栈顶元素
    public int getTop() {
        return queue[front + 1];//不可以front++ 这样会改变front指针
    }

    //入队
    public void push(int date) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法入队");
        }
        rear++;
        queue[rear] = date;
        length++;
    }

    //出队
    public void pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法出队");
        }
        front++;
        System.out.println("出队的元素："+queue[front]);
        length--;
    }

    //查看所有元素
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有任何元素");
        }
        for (int i = front + 1; i <= rear; ++i) {
            System.out.print(queue[i] + " \t");
        }
        System.out.println();
    }

}
