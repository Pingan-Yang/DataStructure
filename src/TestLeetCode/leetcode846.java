package TestLeetCode;

import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName leetcode846
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/30 21:44
 * @Version:1.0
 */
public class leetcode846 {
    //暴力
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (groupSize==0)
            return true;
        if (hand.length<groupSize)
            return false;
        if (hand.length%groupSize!=0)
            return false;
        boolean[] flag= new boolean[hand.length];
        Arrays.sort(hand);
        int count=0;
        for (int i=0;i<hand.length;++i){
            if (flag[i]){
                continue;
            }
            int pre=hand[i];
            flag[i]=true;
            count++;
            for (int j=i+1;j<hand.length;++j){
                int next=hand[j];
                if (count<=groupSize&&!flag[j]&&next==pre+1){
                    flag[j]=true;
                    pre=next;
                    count++;
                }
                if (count==groupSize){
                    count=0;
                    break;
                }
                if (j==hand.length-1&&count<groupSize){
                    return false;
                }
            }
        }
        if (count!=0){
            return false;
        }
        return true;
    }

    //使用优先队列和哈希
    public boolean isNStraightHand2(int[] hand, int groupSize){
        if (groupSize==0)
            return true;
        if (hand.length<groupSize)
            return false;
        if (hand.length%groupSize!=0)
            return false;

        HashMap<Integer,Integer> map=new HashMap<>();
        Queue<Integer> queue=new PriorityQueue<>((a,b)->a-b);//优先队列从小到大
        for (int i:hand){
            queue.add(i);
            if (!map.containsKey(i)){
                map.put(i,1);
            }else{
                map.put(i,map.get(i)+1);
            }
        }
        while (!queue.isEmpty()){
            int t = queue.poll();
            if (map.get(t)==0){
                continue;
            }
            for (int i=0;i<groupSize;++i){
                //不存在下一个数
                if (!map.containsKey(t+i)){
                    return false;
                }else{
                    map.put(t+i,map.get(t+i)-1);
                }
            }
        }
        return true;

    }


    @Test
    public void test(){
       // int[] hand=new int[]{1,2,3,6,2,3,4,7,8};
       // int groupSize=3;
        int[] hand=new int[]{1,2,3,4,5,6};
        int groupSize=2;
        System.out.println(isNStraightHand(hand,groupSize));
    }
}
