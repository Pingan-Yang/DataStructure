package TestLeetCode;
import org.junit.Test;
import java.util.Arrays;

/**
 * @ClassName leetcode34
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/20 21:34
 * @Version:1.0
 */
public class leetcode34 {
    public int[] searchRange(int[] nums, int target) {
        int[] range={-1,-1};
        if (nums==null||nums.length==0){
            return range;
        }

        if (target<nums[0]||target>nums[nums.length-1]){
            return range;
        }

       /* if (nums.length==1&&nums[0]==target){
            return  new int[]{1,1};
        }*/
        range=binarySreach(nums,0,nums.length-1,target);

        return  range;
    }

    private int[] binarySreach(int[] nums, int left, int right, int target) {
        int [] range={-1,-1};
        while (left<=right){
            int mid=(left+right)/2;
            if (nums[mid]==target){
                int l=mid;
                while (l>=0&&nums[l]==nums[mid]){
                    l--;
                }
                range[0]=l+1;
                range[1]=range[0];
                int r=mid;
                while (r<=nums.length-1&&nums[r]==nums[mid]){
                    r++;
                }
                range[1]=r-1;
                return range;
        }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return range;
    }
    @Test
    public void test(){
        int[] nums = {1};
        int target = 1;
        int[] range = searchRange(nums, target);
        System.out.println("range="+ Arrays.toString(range));
    }
}
