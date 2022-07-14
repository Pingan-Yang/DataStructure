package LinkList.src.Josepfu;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName JosefuTest
 * @Description:
 * @Author:ypa
 * @Date 2021/4/16 20:27
 * @Version:1.0
 */
public class JosefuTest {
    @Test
    public void test01() {
        singleLoopList list = new singleLoopList();
        list.add(5);
        list.foreach();
        System.out.println("------------------------------------");
        List<Boy> boyList = list.countBoy(5, 1, 2);
        boyList.forEach(System.out::println);
    }

}

class singleLoopList {
    private Boy first = null;

    /**
     * @Description: 按照传入的个数来构建单循环链表
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/16
     */
    public void add(int nums) {
        if (nums < 2) {
            System.out.println("小于两人,不可以玩这个游戏");
            return;
        }
        Boy cur = null;
        for (int i = 1; i <= nums; ++i) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                cur = first;
            }
            cur.setNext(boy);
            boy.setNext(first);
            cur = boy;
        }
    }

    /**
     * @Description: 遍历单向循环链表
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/16
     */
    public void foreach() {
        if (first == null) {
            throw new RuntimeException("单向循环链表为空，无法遍历");
        }
        Boy cur = first;
        while (true) {
            System.out.println(cur);
            cur = cur.getNext();
            if (cur == first) {
                break;
            }
        }
    }

    /**
     * @Description: 约瑟夫问题，小孩出圈的顺序
     * @Param: sumBoy:总共有多少小孩，startBoy：第一个开始的小孩，stepsize:一次数的数
     * @return:
     * @Author: ypa
     * @Date: 2021/4/16
     */
    public List<Boy> countBoy(int sumBoy, int startBoy, int stepsize) {
        if (first == null || startBoy < 1 || stepsize > sumBoy) {
            System.out.println("输入的参数有误。请重新输入");
            return null;
        }
        //用于存放出圈的小孩
        LinkedList<Boy> list = new LinkedList<>();
        //辅助指针helper，位于cur的后面
        Boy cur = first;
        Boy helper = first;
        while (true) {
            if (helper.getNext() == cur)
                break;
            helper = helper.getNext();
        }
        //游戏开始前,将cur指针移动到开始小孩的位置
        for (int i = 0; i < startBoy - 1; ++i) {
            cur = cur.getNext();
            helper = helper.getNext();
        }
        //开始游戏
        while (true) {
            if (cur == helper) {//游戏结束
                list.add(cur);
                break;
            }
            //找到要出圈的小孩
            for (int i = 0; i < stepsize - 1; ++i) {//自己也需要数数一次，所以i<stepsize-1
                cur = cur.getNext();
                helper = helper.getNext();
            }
            //出圈
            list.add(cur);
            helper.setNext(cur.getNext());
            cur = cur.getNext();
        }
        return list;
    }
}
