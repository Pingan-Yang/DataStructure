package TestLeetCode;

/**
 * @ClassName Leetcode04
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/16 21:50
 * @Version:1.0
 */
public class Leetcode04 {
    public  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //保证nums1的长度 ，小于nums2的长度
        if (nums1.length>nums2.length){
            return findMedianSortedArrays(nums2,nums1);
        }
        //partion1在短数组上不停往前移动(可以等于)
        for (int partion1=0;partion1<=nums1.length;++partion1){
            //根据parttion1来确定 partion2的位置
            int partion2=(nums1.length+nums2.length+1)/2-partion1;
            //根据partion1和partion2的位置求出左右两个数
            int maxLeft1=partion1==0?Integer.MIN_VALUE:nums1[partion1-1];
            int minRight1=partion1==nums1.length?Integer.MAX_VALUE:nums1[partion1];

            int maxLeft2=partion2==0?Integer.MIN_VALUE:nums2[partion2-1];
            int minRight2=partion2==nums2.length?Integer.MAX_VALUE:nums2[partion2];

            //若满足中位数条件则求出中位数
            if (maxLeft1<=minRight2&&minRight1>=maxLeft2){
                if ((nums1.length+nums2.length)%2==0){
                    return ((double) Math.max(maxLeft1,maxLeft2)+Math.min(minRight1,minRight2))/2;
                }else{
                    return (double)Math.max(maxLeft1,maxLeft2);
                }
            }
        }
        return Double.MIN_EXPONENT;
    }

    public static void main(String[] args) {
        int[] nums1={1,2};
        int[] nums2={3,4};
        Leetcode04 leetcode04 = new Leetcode04();
        double midValue = leetcode04.findMedianSortedArrays(nums1, nums2);
        System.out.println(midValue);
    }
}
