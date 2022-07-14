package Recursion;

import org.junit.Test;

/**
 * @ClassName MaxArrayElement
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/29 20:27
 * @Version:1.0
 */
public class MaxArrayElement {
    public int maxElement(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = left+((right-left)>>1);
        int maxLeft = maxElement(nums, left, mid);
        int maxRight = maxElement(nums, mid + 1, right);
        return Math.max(maxLeft, maxRight);
    }

    @Test
    public void test() {
        int[] arr = {4, 2, 1, 66, 48};
        System.out.println("maxElement=" + maxElement(arr, 0, arr.length - 1));
    }
}
