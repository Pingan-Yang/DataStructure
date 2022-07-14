package TestLeetCode;

import org.junit.Test;
import sun.security.util.Length;

import java.lang.annotation.Target;

/**
 * @ClassName leetcode33
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/20 21:14
 * @Version:1.0
 */
public class leetcode33 {
    public int search(int[] nums, int target) {
        //直接去求但是太弱智了 ==== O(n)
        //时间复杂度为 O(log n)====>二分查找
        if (nums.length == 1) {
            if (target == nums[0]) {
                return 0;
            } else {
                return -1;
            }
        }
        int index = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            if (target<nums[0]||target>nums[nums.length-1]){
                return -1;
            }else{
                return binarySearch(nums,0,nums.length-1, target);
            }
        }else{
            if (target>=nums[0]){
                return binarySearch(nums,0,index-1,target);
            }else{
                return binarySearch(nums,index,nums.length-1,target);
            }

        }

    }
    private int binarySearch(int[] nums,int left,int right,int target){
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right=mid-1;
                continue;
            }else{
                left=mid+1;
                continue;
            }
        }

        return -1;
    }

    @Test
    public void test(){
        int[] nums={1,3};
        int target=0;
        int i = search(nums, target);
        System.out.println(i);
    }
}
