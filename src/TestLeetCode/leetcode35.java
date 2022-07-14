package TestLeetCode;

import org.junit.Test;

import java.lang.annotation.Target;

/**
 * @ClassName leetcode35
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/21 10:58
 * @Version:1.0
 */
public class leetcode35 {
    public int searchInsert(int[] nums, int target) {
        if (target<nums[0]||target==nums[0]){
            return 0;
        }else if (target>nums[nums.length-1]){
            return nums.length;
        }
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if (nums[mid]==target){
                return mid;
            }else if (nums[mid]>target){
                right=mid-1;
                continue;
            }else{
                left=mid+1;
                continue;
            }
        }
        if (target>nums[left]){
            return left+1;
        }
        return left;
    }

    @Test
    public void test01(){
        int[] nums={1,3,5,6};
        int target=0;
        System.out.println("index="+searchInsert(nums,target));
    }
}
