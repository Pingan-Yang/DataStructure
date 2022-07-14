package Recursion;

import org.junit.Test;
import sun.nio.cs.FastCharsetProvider;

import java.util.Arrays;

/**
 * @ClassName Migong
 * @Description:
 * @Author:ypa
 * @Date 2021/4/26 19:13
 * @Version:1.0
 */
/*
    递归需要遵守的重要规则：

        ①执行一个方法时，就创建一个新的受保护的独立空间(栈空间)
        ②方法的局部变量是独立的，不会相互影响, 比如n变量
        ③如果方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.
        ④递归必须向退出递归的条件逼近，否则就是无限递归,出现StackOverflowError，死龟了:)
        ⑤当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕。

* */
public class Migong {
    private int[][] map = new int[8][7];

    /**
     * @Description: 初始化迷宫
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/26
     */
    public void initialMap() {
        //约定：将1设置为挡板 0表示没有走的格子
        for (int i = 0; i < 7; ++i) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; ++i) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
    }

    /**
     * @Description: 寻找迷宫的路径
     * @Param:
     * @return:
     * @Author: ypa
     * @Date: 2021/4/26
     */
    /*
        约定：
        map:表示地图
        i,j：表示在迷宫中的位置
        map[i][j]=0:表示迷宫中为走过的格子
        map[i][j]=1:表示迷宫中的挡板
        map[i][j]=2:表示迷宫中中的通路
        map[i][j]=3:表示迷宫中的死路
        策略：下->右->上->左
        //如何求出最短路径？------>没学算法之前  改变策略
    * */
    public boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//表示为通路，路已找到-----递归出口
            return true;
        } else {
            if (map[i][j] == 0) {//表示没走过
                map[i][j] = 2;//假设可以走得通
                if (setWay(map, i + 1, j)) {//下找
                    return true;
                } else if (setWay(map, i, j + 1)) {//右找
                    return true;
                } else if (setWay(map, i - 1, j)) {//上找
                    return true;
                } else if (setWay(map, i, j - 1)) {//左找
                    return true;
                } else {
                    map[i][j] = 3;//表示走不通
                    return false;
                }
            }else {
                //map[i][j]!=0
                return false;
            }
        }
    }

    @Test
    public void test01() {
        initialMap();
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
        setWay(map,1,1);
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
