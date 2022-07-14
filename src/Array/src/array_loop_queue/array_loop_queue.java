package Array.src.array_loop_queue;

/**
 * @ClassName array_loop_queue
 * @Description: 循环队列----最大的长度只有Cacacity-1；因为rear后面流一个位置 这是预定
 * @Author:ypa
 * @Date 2020/12/13 18:18
 * @Version:1.0
 */
public class array_loop_queue {
    public static void main(String[] args) {
        Loop_Queue loop_queue = new Loop_Queue(6);
        for (int i = 0; i < 3; i++) {
            loop_queue.push(i);
        }
        loop_queue.show();
        System.out.println("队列长度：" + loop_queue.getLength());
        System.out.println("队首元素：" + loop_queue.getFirstDate());
        System.out.println("队列是否为空：" + loop_queue.isEmpty());
        System.out.println("队列是否已满：" + loop_queue.isFull());
        System.out.println("----------------------------------------");

        for (int i = 3; i < 5; i++) {
            loop_queue.push(i);
        }
        loop_queue.show();
        System.out.println("队列长度：" + loop_queue.getLength());
        System.out.println("队首元素：" + loop_queue.getFirstDate());
        System.out.println("队列是否为空：" + loop_queue.isEmpty());
        System.out.println("队列是否已满：" + loop_queue.isFull());
        System.out.println("----------------------------------------");

        loop_queue.pop();
        loop_queue.push(1000);
        loop_queue.pop();
        loop_queue.show();
        System.out.println("队列长度：" + loop_queue.getLength());
        System.out.println("队首元素：" + loop_queue.getFirstDate());
        System.out.println("队列是否为空：" + loop_queue.isEmpty());
        System.out.println("队列是否已满：" + loop_queue.isFull());
        System.out.println("----------------------------------------");

    }
}

class Loop_Queue {
    private int[] value;//存放数据  模拟队列
    private int first;//指向第一个元素
    private int rear;//指向最后一个元素的后一个元素
    private int capacity;//最大容量

    public Loop_Queue(int capacity) {
        this.capacity = capacity;
        value = new int[capacity];
        first = 0;//初始化头指针为0
        rear = 0;//初始化尾指针也为0
    }

    //入队
    public void push(int date) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法入队！");
        } /*else if(rear+1>capacity){
            value[rear]=date;
            rear=0;
        }else{
            value[rear++]=date;
        }*/
        value[rear] = date;
        //rear后移，这里必须取模
        rear = (rear + 1) % capacity;
    }

    //出队
    public void pop() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，无法出队！");
        }/*else if(first+1>=capacity){
            value[first]=0;
            first=0;
        }else {
            value[first]=0;
            first++;
        }*/
        value[first] = 0;
        //这里  必须取模
        first = (first + 1) % capacity;

    }

    //取队首元素
    public int getFirstDate() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，无首元素");
        }
        return value[first];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return first == rear;
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % capacity == first;
    }

    //获取队列长度
    public int getLength() {
        /*
        raer>first------>length=rear-first;
        rear<first------>分为两部分，第一部分：capacity-front 第二部分：rear+0
        * */
        return (rear + capacity - first) % capacity;
    }

    //获取队列容量
    public int getCapacity() {
        return this.capacity;
    }

    //显示队列中的元素（注意队列没有遍历功能）
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("没有元素");
        }/*else if(first<rear){
            for(int i=first;i<rear;++i){
                System.out.print(value[i]+" \t ");
            }
            System.out.println();
        }else {
            for(int i=first;i<capacity;++i){
                System.out.print(value[i]+" \t ");
            }
            for(int j=0;j<rear;++j){
                System.out.print(value[j]+" \t ");
            }*/
        for (int i = first; i < first + getLength(); i++) {
            System.out.print(value[i % capacity] + "\t");
        }
        System.out.println();
    }
}

