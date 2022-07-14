package Stack;

import org.junit.Test;

/**
 * @ClassName Caculator
 * @Description: 使用栈完成中缀表达式的计算
 * @Author:ypa
 * @Date 2021/4/19 21:00
 * @Version:1.0
 */
/*
    使用栈完成中缀表达式的计算思路：
    1、准备两个栈，一个栈用于装数据，一个数用于装运算符，准备一个指针index去遍历表达式
    2、当我们发现数时就入栈（这里有问题----详情见代码部分的改进）
    3、如果我们扫描到的是运算符，分以下情况
        3.1如果运算符栈为空，则直接入栈
        3.2如果运算符不为空
            3.2.1 当前扫描到的运算符的优先级比栈顶元素的优先级高，则将起入栈
            3.2.2 当前扫描到的运算符的优先级比栈顶元素的优先级低(或者等于)，则需要从数栈中弹出两个元素且将运算栈的栈顶运算符弹出计算，将结果压入数栈，同时将当前运算符压入运算符栈
    4、当表达式扫描完毕，就顺序的从数栈中弹出数据、从运算栈中弹出相应的运算符 进行运算
    5、最后数栈中只有一个元素，就是当前结果，运算符栈为空

* */

public class Caculator {
    public void getResult(String expression) {
        //1、建立一个数栈和操作数栈，创建一个索引指针
        ArrayStack1 numStack = new ArrayStack1(10);
        ArrayStack1 oprStack = new ArrayStack1(10);
        int index = 0;
        //用于拼接数字
        String keepNum = "";
        //开始扫描expression
        while (true) {
            char temp = expression.charAt(index);
            //2、判断是否为运算符
            if (oprStack.isOperator(temp)) {
                //此时是运算符
                if (!oprStack.isEmppty()) {
                    //此时运算符栈不是空
                    int priority1 = oprStack.priority(temp);
                    int priority2 = oprStack.priority((char) oprStack.getTop());
                    if (priority1 > priority2) {
                        //当前扫描到的运算符的优先级比栈顶元素的优先级高，则将其入栈
                        oprStack.push(temp);
                    } else {
                        //当前扫描到的运算符的优先级比栈顶元素的优先级低(或者等于)
                        //则需要从数栈中弹出两个元素且将运算栈的栈顶运算符弹出计算
                        int num1 = numStack.getTop();
                        numStack.pop();
                        int num2 = numStack.getTop();
                        numStack.pop();
                        char opr = (char) oprStack.getTop();
                        oprStack.pop();
                        int res1 = numStack.caculation(num1, num2, opr);
                        //将结果压入数栈，同时将当前运算符压入运算符栈
                        numStack.push(res1);
                        oprStack.push(temp);
                    }
                } else {
                    //运算符栈为空，直接入栈
                    oprStack.push(temp);
                }
            } else {
                //如果是数，直接入栈(这里不对)
                //numStack.push(temp - 48);//接收到的是字符，要转换为数字
                //分析思路
                /*
                1、当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                2、在处理数时，需要想expression的表达式的index后在看一位，如果是数则，继续扫描，如果是符号才入栈
                3、因此需要定义一个字符串变量，用于拼接
                * */
                keepNum += temp;
                //如果temp已经是expression的最后一位，就直接入栈、
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (oprStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //注意：要清空keepNum！！！！！！！！！
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index == expression.length())
                break;
        }
        //4、当表达式扫描完毕，就顺序的从数栈中弹出数据、从运算栈中弹出相应的运算符 进行运算
        while (true) {
            if (oprStack.isEmppty())
                break;
            int num1 = numStack.getTop();
            numStack.pop();
            int num2 = numStack.getTop();
            numStack.pop();
            char opr = (char) oprStack.getTop();
            oprStack.pop();
            int res = numStack.caculation(num1, num2, opr);
            numStack.push(res);

        }
        System.out.printf("表达式%s=%d", expression, numStack.getTop());
    }

    @Test
    public void test() {
        String expression = "70+2*7-4";//80----->但是答案是10   有缺陷
        getResult(expression);
    }

}


//借用前面的栈，并添加新的功能
class ArrayStack1 {
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
    public ArrayStack1(int capacity) {
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

    /**
     * @Description: 返回运算符的优先级，数字越大，优先级越高(假设表达式只有+-*’/‘)
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public int priority(char ch) {
        if (ch == '+' || ch == '-')
            return 0;
        else if (ch == '*' || ch == '/')
            return 1;
        else
            throw new RuntimeException("未知运算符");
    }

    /**
     * @Description: 进行运算
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public int caculation(int data1, int data2, char opr) {
        int sum = 0;
        switch (opr) {
            case '+':
                sum = data1 + data2;
                break;
            case '-':
                sum = data2 - data1;
                break;
            case '*':
                sum = data1 * data2;
                break;
            case 4:
                sum = data2 / data1;
                break;
        }
        return sum;
    }

    /**
     * @Description:
     * @Param: 判断是不是一个运算符
     * @return:
     * @Author: ypa
     * @Date: 2021/4/19
     */
    public boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}
