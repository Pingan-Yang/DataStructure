package TestLeetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName Leetcode825
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/27 9:39
 * @Version:1.0
 */
public class Leetcode825 {
    public int numFriendRequests(int[] ages) {
        int sum = 0;
        if (ages.length == 1) {
            return sum;
        }
        int left=0,right=0;
        for (int i=0;i<ages.length;++i){
            if (ages[i]<15){
                continue;
            }
            while (ages[left]<=0.5 * ages[i] + 7){
                left++;
            }
            while (right+1<ages.length&&ages[right+1]<=ages[i]){
                right++;
            }
            sum+=right-left;
        }
        return sum;
    }

    @Test
    public void test01() {
        int[] ages = {16,16};
        int sum = numFriendRequests(ages);
        System.out.println("sum="+sum);
    }
}

