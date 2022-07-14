package TestLeetCode;

import org.junit.Test;

import javax.sound.midi.Soundbank;

/**
 * @ClassName leetcode26
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/20 16:38
 * @Version:1.0
 */
public class leetcode26 {
    public int removeDuplicates(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        if (nums.length==1){
            return 1;
        }
        int i=0;//用于记录不重复数的最后的位数
        for(int j=i+1;j<nums.length;++j){
            if (nums[i]!=nums[j]){
                nums[++i]=nums[j];
            }
        }
        return i+1;
    }

    @Test
    public void test(){
        int[] nums={1,2,3,3,3,4,4,5};
        int i = removeDuplicates(nums);
        System.out.println("i="+i);
    }
}
