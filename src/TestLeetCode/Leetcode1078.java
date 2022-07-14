package TestLeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName Leetcode
 * @Description: TODO
 * @Author 15368
 * @Date 2021/12/27 10:55
 * @Version:1.0
 */
public class Leetcode1078 {
    public String[] findOcurrences(String text, String first, String second) {
        List<String> stringList = new ArrayList<String>();
        String[] stringsArray = text.split(" ");
        if (text.length() <= first.length() + second.length()) {
            return null;
        }
        for (int i = 0; i < stringsArray.length; ++i) {
            if (i + 2 < stringsArray.length && first.equals(stringsArray[i]) && second.equals(stringsArray[i + 1])) {
                stringList.add(stringsArray[i + 2]);
            }
        }
        String[] result = new String[stringList.size()];
        int i = 0;
        for (String s : stringList) {
            result[i++] = s;
        }
        return result;
    }

    @Test
    public void test(){
       String text = "alice is a good girl she is a good student";
       String first = "a";
       String second = "good";
        String[] ocurrences = findOcurrences(text, first, second);
        System.out.println(Arrays.toString(ocurrences));
    }
}
