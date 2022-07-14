package TestLeetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName LeetCode1154
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/21 10:12
 * @Version:1.0
 */
public class LeetCode1154 {
    public int dayOfYear(String date) {

        String[] dateStringArray = date.split("-");
        int[] dateArray=new int[3];
        int i=0;
        for (String s :dateStringArray){
           dateArray[i++]=Integer.parseInt(s);
        }
        //先判断是不是闰年
        int sum=0;
        if (dateArray[0]%4==0&&dateArray[0]%100!=0||dateArray[0]%400==0){
            for (int j=1;j<dateArray[1];++j){
                if (j==1||j==3||j==5||j==7||j==8||j==10||j==12){
                    sum+=31;
                    continue;
                }else if (j==2){
                    sum+=29;
                    continue;
                }else{
                    sum+=30;
                    continue;
                }
            }
        }else{
            for (int j=1;j<dateArray[1];++j){
                if (j==1||j==3||j==5||j==7||j==8||j==10||j==12){
                    sum+=31;
                    continue;
                }else if (j==2){
                    sum+=28;
                    continue;
                }else{
                    sum+=30;
                    continue;
                }
            }
        }
        sum=sum+dateArray[2];


        return sum;
    }

    @Test
    public void test01(){
        String date="2004-03-01";
        int days = dayOfYear(date);
        System.out.println(days);
    }
}
