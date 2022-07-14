package Stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName PolandNotation
 * @Description:利用栈计算逆波兰表达式的结果
 * @Author:ypa
 * @Date 2021/4/21 19:33
 * @Version:1.0
 */
public class PolandNotation {
    @Test
    public void test() {
        String expression = "3 4 + 5 * 6 -";
        List<String> list = getListString(expression);
        //list.forEach(System.out::print);
        int result = caculation(list);
        System.out.println("3 4 + 5 * 6 - =" + result);

    }

    @Test
    public void test01() {
        String expression = "1+((2+3)*4)-5";//16
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);
        System.out.println();
        List<String> paraseSuffixExpressionList = paraseSuffixExpressionList(list);
        System.out.println(paraseSuffixExpressionList);
        int result = caculation(paraseSuffixExpressionList);
        System.out.println(result);//16
    }

    /**
     * @Description: 将字符串表达式中的字符取出放入到列表中
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/21
     */
    public List<String> getListString(String str) {
        List<String> list = new ArrayList<>();
        String[] split = str.split(" ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * @Description: 将中缀表达式转换为相应的list
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/21
     */
    public List<String> toInfixExpressionList(String exp) {
        List<String> list = new ArrayList<>();
        String str = "";//用于拼接整数
        char ch = '0';//用于接受扫描表达式所得到的字符
        //开始扫描
        for (int i = 0; i < exp.length(); ) {
            ch = exp.charAt(i);
            if (ch < 48 || ch > 57) { //如果扫描的不是数，就直接加到列表里
                //0~9数字对应十进制48－57
                list.add(String.valueOf(ch));
                i++;
            } else {
                //如果扫描的是数字，进行拼接
                str = "";
                while (ch >= 48 && ch <= 57 && i < exp.length()) {
                    str += ch;
                    //这里要加边界条件判断,防止越界
                    if ((++i) == exp.length())
                        break;
                    ch = exp.charAt(i);
                }
                list.add(str);
            }
        }
        return list;
    }

    /**
     * @Description: 将中缀表达式对应的列表，转换为后缀表达式
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/21
     */
    public List<String> paraseSuffixExpressionList(List<String> ls) {
        /*
        ①：初始化两个栈：运算符栈s1和储存中间结果的栈s2；
        ②：从左至右扫描中缀表达式；
        ③：遇到操作数时，将其压s2；
        ④：遇到运算符时，比较其与s1栈顶运算符的优先级：
            （1）如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
            （2）否则，若优先级比栈顶运算符的高，也将运算符压入s1；
            （3）否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
        ⑤：遇到括号时：
         (1) 如果是左括号“(”，则直接压入s1
         (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
       ⑥重复步骤2至5，直到表达式的最右边
       ⑦将s1中剩余的运算符依次弹出并压入s2
       ⑧依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
        * */
        Stack<String> s1 = new Stack<>();//符号栈
        List<String> result = new ArrayList<>();

        for (String item : ls) {
            if (item.matches("\\d+")) {
                result.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止,此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    result.add(s1.pop());
                }
                //将左括号弹出，消除左括号
                s1.pop();
            } else {
                //匹配到的是运算符
                /*
                （1）如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
                （2）否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                （3）否则，若优先级小于等于栈顶元素的优先级,将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
                * */

                //自己的写法
                if (s1.isEmpty() || s1.peek().equals("(")) {
                    s1.push(item);
                } else {
                    if (Operator.getPeriority(item) > Operator.getPeriority(s1.peek())) {
                        s1.push(item);
                    } else {
                        while (Operator.getPeriority(item) <= Operator.getPeriority(s1.peek())) {
                            result.add(s1.pop());
                            //这一步很重要  不加这一步判断 就会报栈空指针异常 因为栈为空时，还调用了peek()
                            if (s1.isEmpty())
                                break;
                        }
                        s1.push(item);

                    }
                }


                //视频中的写法   这个写法        更简洁 更好
                /*while (s1.size() != 0 && Operator.getPeriority(s1.peek()) >= Operator.getPeriority(item)) {
                    result.add(s1.pop());
                }
                //还需将item压入栈
                s1.push(item);
                */

            }
        }
        //⑦将s1中剩余的运算符依次弹出并压入s2
        while (s1.size() != 0) {
            result.add(s1.pop());
        }
        return result;//存放到list中 ，正常输输出就可
    }

    /**
     * @Description: 计算逆波兰表达式
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/21
     */
    public int caculation(List<String> list) {
        /*
            expression=”3 4 + 5 * 6 -“
            ①：从左至右扫描，将3和4压入堆栈；
            ②：遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
            ③：将5入栈；
            ④：接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
            ⑤：将6入栈；
            ⑥：最后是-运算符，计算出35-6的值，即29，由此得出最终结果
        * */
        //存放数据的栈
        Stack<String> stack = new Stack<>();
        for (String ele : list) {
            //这里使用正则表达式取出值
            if (ele.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(ele);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (ele.equals("+")) {
                    res = num1 + num2;
                } else if (ele.equals("-")) {
                    res = num1 - num2;
                } else if (ele.equals("*")) {
                    res = num1 * num2;
                } else if (ele.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符异常");
                }
                stack.push(String.valueOf(res));
            }
        }

        return Integer.parseInt(stack.pop());
    }
}

class Operator {
    public static int ADD = 1;
    public static int SUB = 1;
    public static int MUL = 2;
    public static int DIV = 2;

    public static int getPeriority(String str) {
        int res = 0;
        switch (str) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                //这里为啥不可以抛异常
                System.out.println("不存在该表达式");
                break;
        }
        return res;
    }


}