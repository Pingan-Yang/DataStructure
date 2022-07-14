package TestLeetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName Leetcode475
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/20 15:10
 * @Version:1.0
 */
public class Leetcode475 {
    public int findRadius(int[] houses, int[] heaters) {
        //方法一：直接暴力的去找每个房间距离加热器的最短距离
         /*
            解答成功:
			执行耗时:1359 ms,击败了8.15% 的Java用户
			内存消耗:41.6 MB,击败了61.28% 的Java用户
        * */
        Arrays.sort(heaters);
        int redius = 0;
        for (int i = 0; i < houses.length; ++i) {
            int curPos = houses[i];
            int rediusI = Integer.MAX_VALUE;
            //找出当前房间的最短距离
            for (int j = 0; j < heaters.length; ++j) {
                rediusI = Math.min(rediusI, Math.abs(curPos - heaters[j]));
            }
            redius = Math.max(rediusI, redius);
        }
        return redius;
    }

    public int findRadius2(int[] houses, int[] heaters) {
        //优化 对于第二个数组来说  遍历一遍就够了
        /*
            ①：找到第i房子距离最近的最右边的房子；
            ②：找到第i房子距离最近的最左边的房子；
            ③：distenceI=min(disLeft,disRight);
            ④：radis=max(distence1,...,distenceN);
        * */

        int[] radisI = new int[houses.length];
        //防止错误状态转移方程的发生===>因为上面的初始化数组中全是0
        Arrays.fill(radisI, Integer.MAX_VALUE);
        Arrays.sort(houses);
        Arrays.sort(heaters);
        // ①：找到第i房子距离最近的最右边的房子；
        for (int i = 0, j = 0; i < houses.length && j < heaters.length; ) {
            if (houses[i] <= heaters[j]) {
                radisI[i] = heaters[j] - houses[i];
                i++;
            } else {
                j++;
            }
        }
        // ②：找到第i房子距离最近的最左边的房子；
        for (int i = houses.length - 1, j = heaters.length - 1; i >= 0 && j >= 0; ) {
            if (houses[i] >= heaters[j]) {
                radisI[i] = Math.min(radisI[i], houses[i] - heaters[j]);
                i--;
            } else {
                j--;
            }
        }
        //找出最小的radis
        int radius = 0;
        for (int r : radisI) {
            radius = Math.max(radius, r);
        }
        return radius;
    }

    @Test
    public void test() {
        int[] hourse = {282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923};
        int[] heaters = {823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612};
        int radius = findRadius2(hourse, heaters);
        System.out.println("radius=" + radius);
    }
}
