package TestLeetCode;

import org.junit.Test;


/**
 * @ClassName Leetcode686
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/22 20:49
 * @Version:1.0
 */
public class Leetcode686 {
    public int repeatedStringMatch(String a, String b) {

        int count=0;
        StringBuilder sb=new StringBuilder();
        int max=a.length()+b.length();//why
        while (sb.length()<max){
            count++;
            sb.append(a);
            if (sb.toString().indexOf(b)!=-1){
                return count;
            }
        }

        return -1;
    }

    @Test
    public void test(){
        String a="abcd";
        String b="bc";
        int count = repeatedStringMatch(a, b);
        System.out.println(count);
    }
}
