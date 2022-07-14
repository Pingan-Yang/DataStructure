package TestLeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Leetcode1995
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/29 15:39
 * @Version:1.0
 */
public class Leetcode1995 {
    //这一题 不能排序
   /* public int countQuadruplets(int[] nums) {
        int len=nums.length;
        int res=0;
        Arrays.sort(nums);
        if (len==4&&nums[0]+nums[1]+nums[2]!=nums[3]){
            return res;
        }

        for (int i=len-1;i>=3;i--){
            //枚举第一个数
            int num1=nums[i];
            for (int j=len-2;j>=2;j--){
                //枚举第二个数
                int num2=nums[j];
                //利用双指针 查找剩下的两个数
                int left=0;
                int right=j-1;
                while (left<right){
                    if (nums[left]+nums[right]+num2==num1){
                        res+=1;
                        System.out.println("index1="+left+",index2="+right+",index3="+j+",index4="+i);
                        int l=left+1;
                        int r=right-1;
                        while (l<right&&nums[l]==nums[left]){
                            System.out.println("index1="+left+",index2="+right+",index3="+j+",index4="+i);
                            res+=1;
                            l++;
                        }
                        while (r>left&&nums[r]==nums[right]){
                            System.out.println("index1="+left+",index2="+right+",index3="+j+",index4="+i);
                            res+=1;
                            r--;
                        }
                        left++;
                        right--;
                    }else if(nums[left]+nums[right]+num2<num1){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return res;
    } */
    //暴力法
    public int countQuadruplets(int[] nums) {
        int len=nums.length;
        int res=0;
        for (int i=0;i<len;++i){
            for (int j=i+1;j<len;++j){
                for (int k=j+1;k<len;++k){
                    for (int m=k+1;m<len;++m){
                        if (nums[i]+nums[j]+nums[k]==nums[m])
                            res+=1;
                    }
                }
            }
        }
        return  res;
    }

    //使用hashmap
    public int countQuadruplets1(int[] nums) {
        int len=nums.length;
        int res=0;
        Map<Integer,Integer>map=new HashMap<>();
        for (int b=len-3;b>=1;b--){
            int c=b+1;
            for (int d=c+1;d<len;++d){
                if (!map.containsKey(nums[d]-nums[c])){
                    map.put(nums[d]-nums[c],1);
                }else{
                    map.put(nums[d]-nums[c],map.get(nums[d]-nums[c])+1);
                }
            }
            for (int a=0;a<b;++a){
                if (map.containsKey(nums[b]+nums[a])){
                    res+=map.get(nums[b]+nums[a]);
                }
            }
        }
        return res;
    }


    @Test
    public void test(){
        //8 8 20 28 37 49 85 90
        int[] nums={28,8,49,85,37,90,20,8};//1
       // int[] nums={1,1,1,3,5};//4
        int countQuadruplets = countQuadruplets1(nums);
        System.out.println(countQuadruplets);
    }
}
