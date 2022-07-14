package Test;

import java.util.*;

/**
 * @ClassName Solution
 * @Description: TODO
 * @Author 15368
 * @Date 2021/10/21 16:02
 * @Version:1.0
 */
public class Solution {
    public static int solution(int[] nums,int[] result){
            Set<Integer> set = new HashSet<Integer>();
            List<Integer> ans=new ArrayList<>();
            for(int i = 0;i<nums.length;i++){
               set.add(nums[i]);
            }
            for(int i = 1;i<=nums.length;i++){
                if(!set.contains(i)){
                    ans.add(i);
                }
            }
            result=new int[ans.size()];
            for (int i=0;i<ans.size();i++){
                result[i]=ans.get(i);
            }

        return ans.size();
    }

    public static int[] solution2(int[] nums1,int nums2[]){
        int a=0,b=0,sum=0;
        for (int i=0;i<nums1.length;++i){
            a=a*10+nums1[i];
        }
        for (int i=0;i<nums2.length;++i){
            b=b*10+nums2[i];
        }
        sum=a+b;
        String ssum=String.valueOf(sum);
        int[] result=new int[ssum.length()];
        for (int i=0;i<ssum.length();++i){
            result[i]=Integer.parseInt(ssum.charAt(i)+"");
        }
        return  result;
    }
    public static void main(String[] args) {
       /* int[] num=new int[]{1,3,4,6,6,6,7,8};
        int[] result=new int[1];
        int solution = solution(num, result);
        System.out.println(solution);*/
        int[] nums1={1,2,3};
        int[] nums2={9,5,9};
        int[] ints = solution2(nums1, nums2);
        System.out.println(Arrays.toString(ints));
    }
}
