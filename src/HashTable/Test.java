package HashTable;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test
 * @Description:
 * @Author:ypa
 * @Date 2021/5/11 16:53
 * @Version:1.0
 */
public class Test {
    public static void main(String[] args) {
        HashTable table = new HashTable(7);
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        while (true) {
            System.out.println("输入add添加Emp");
            System.out.println("输入delete删除Emp");
            System.out.println("输入find查找Emp");
            System.out.println("输入foreach遍历Emp");
            System.out.println("输入exit退出");
            String s = sc.next();

            switch (s) {
                case "add":
                    System.out.println("输入id");
                    int id = sc.nextInt();
                    System.out.println("输入name");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
                    table.add(emp);
                    break;
                case "delete":
                    System.out.println("输入id");
                    id = sc.nextInt();
                    table.delete(id);
                    break;
                case "find":
                    System.out.println("输入id");
                    id = sc.nextInt();
                    Emp emp1 = table.find(id);
                    System.out.println(emp1);
                    break;
                case "foreach":
                    table.foreach();
                    break;
                case "exit":
                    table=null;
                    return;
                default:
                    return;
            }
        }
    }
    @org.junit.Test
    public void test(){
        int x=121;
      /*  String s=new String(Integer.toString(x));
        StringBuilder s1=new StringBuilder(s);
        System.out.println(s1.reverse());
        System.out.println(s);
        System.out.println(s1);
        String s2=new String(s1);
        System.out.println(s.equals(s2));*/
    }
    @org.junit.Test
    public void test02() {
        int x=121;
        if (x < 0 || x % 10 == 0)
            return;
        int rev = 0;
        while (x != 0) {
            int digtal=x%10;
            x=x/10;
            rev=rev*10+digtal;
            if (rev>Integer.MAX_VALUE)
                return;
        }
        System.out.println();
    }
    @org.junit.Test
    public void test03(){
        String[]strs=new String[]{"flower","flow","flight"};
        if (strs == null || strs.length == 0)
            return;
        if (strs.length == 1)
            return;
        int minlength = strs[0].length();
        int k = 0;
        for (int i = 0; i < strs.length; ++i) {
            if (strs[i].length() < minlength) {
                minlength = strs[i].length();
                k = i;
            }
        }
        String substr = null;
        boolean flag = true;
        for (int i = strs[k].length(); i > 0; --i) {
            substr = strs[k].substring(0, i);
            flag = true;
            for (int j = 0; j < strs.length; ++j) {
                if (j == k)
                    continue;
                if (!substr.equals(strs[j].substring(0, i))) {
                    flag=false;
                    break;
                }
            }
            if (flag==true)
                System.out.println(substr);
                return;

        }
        return;
    }
}
