package TestLeetCode;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @ClassName TestLeetCode
 * @Description:
 * @Author:ypa
 * @Date 2021/5/31 16:07
 * @Version:1.0
 */
public class TestLeetCode {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode();
        ListNode tempHead = newHead;

        int value = head.val;
        tempHead.next = head;
        tempHead = tempHead.next;
        ListNode current = head.next;
        //这里会显示current==null why?
        // System.out.println(head.next.val);
        while (current != null) {
            if (current.val != value) {
                tempHead.next = current;
                tempHead = tempHead.next;
                value = current.val;
            }
            current = current.next;
        }
        return newHead.next;
    }

    @Test
    public void test01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;

        ListNode listNode = deleteDuplicates(node1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    @Test
    public void test02() {
        List<Integer> row = getRow(5);
        //System.out.println(row);

    }

    public List<Integer> getRow(int rowIndex) {
        /*List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j+1));
                }
            }
            ret.add(row);
        }
        return ret.get(rowIndex);*/
        List<List<Integer>> C = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(C.get(i - 1).get(j - 1) + C.get(i - 1).get(j));
                }
            }
            C.add(row);
        }
        for (int i = 0; i < C.size(); ++i) {
            System.out.println(C.get(i));
        }
        return C.get(rowIndex);
    }

    @Test
    public void test03() {
        String s = "A man, a plan, a canal: Panama";
        boolean palindrome = isPalindrome(s);
        System.out.println(palindrome);
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return true;
        }
        String s1 = s.toLowerCase();
        System.out.println("s1=" + s1);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            if ((c - 'a' >= 0 && 'z' - c >= 0) || (c - '0' >= 0 && '9' - c >= 0)) {
                str.append(c);
            }
        }
        System.out.println("str=" + str);
        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;

    }

    @Test
    public void test04() {
        int[] nums = {2, 2, 1};
        int num = singleNumber(nums);
        System.out.println(num);
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        int num = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                num = entry.getKey();
                break;
            }
        }
        return num;
    }

    @Test
    public void test05() {
        int zeroes = trailingZeroes(13);
        System.out.println(zeroes);
    }

    public int trailingZeroes(int n) {
        int count = 0;
        while(n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
    @Test
    public void test06(){
        System.out.println(isPowerOfTwo(1));
    }
    public boolean isPowerOfTwo(int n) {
        if (n<=0){
            return false;
        }
        HashMap<Boolean, Integer> map = new HashMap<Boolean, Integer>();
        while (n > 0) {
            int m = n & 1;
            if (m == 1) {
                if (!map.containsKey(true)) {
                    map.put(true, 1);
                } else{
                    return false;
                }
            }
            n = n>>2;
        }
        return true;
    }
    @Test
    public void test07(){
        String str="esbtzjaaijqkgmtaajpsdfiqtvxsgfvijpxrvxgfumsuprzlyvhclgkhccmcnquukivlpnjlfteljvykbddtrpmxzcrdqinsnlsteonhcegtkoszzonkwjevlasgjlcquzuhdmmkhfniozhuphcfkeobturbuoefhmtgcvhlsezvkpgfebbdbhiuwdcftenihseorykdguoqotqyscwymtjejpdzqepjkadtftzwebxwyuqwyeegwxhroaaymusddwnjkvsvrwwsmolmidoybsotaqufhepinkkxicvzrgbgsarmizugbvtzfxghkhthzpuetufqvigmyhmlsgfaaqmmlblxbqxpluhaawqkdluwfirfngbhdkjjyfsxglsnakskcbsyafqpwmwmoxjwlhjduayqyzmpkmrjhbqyhongfdxmuwaqgjkcpatgbrqdllbzodnrifvhcfvgbixbwywanivsdjnbrgskyifgvksadvgzzzuogzcukskjxbohofdimkmyqypyuexypwnjlrfpbtkqyngvxjcwvngmilgwbpcsseoywetatfjijsbcekaixvqreelnlmdonknmxerjjhvmqiztsgjkijjtcyetuygqgsikxctvpxrqtuhxreidhwcklkkjayvqdzqqapgdqaapefzjfngdvjsiiivnkfimqkkucltgavwlakcfyhnpgmqxgfyjziliyqhugphhjtlllgtlcsibfdktzhcfuallqlonbsgyyvvyarvaxmchtyrtkgekkmhejwvsuumhcfcyncgeqtltfmhtlsfswaqpmwpjwgvksvazhwyrzwhyjjdbphhjcmurdcgtbvpkhbkpirhysrpcrntetacyfvgjivhaxgpqhbjahruuejdmaghoaquhiafjqaionbrjbjksxaezosxqmncejjptcksnoq";
        String s = longestPalindrome(str);
        System.out.println(s);

    }
    public String longestPalindrome(String s) {
        if (s==null||s.length()==0){
            return null;
        }
        if (s.length()==1){
            return s;
        }
        ArrayList<String> list=new ArrayList<>();
        StringBuilder str1=new StringBuilder();
        for (int i=0;i<s.length();++i) {
            str1.append(s.charAt(i));
            for (int j = i + 1; j < s.length(); ++j) {
                str1.append(s.charAt(j));
                String str = str1.toString();
                if (str.equals(str1.reverse().toString())) {
                    list.add(str);
                    str1.reverse();
                    continue;
                }
                str1.reverse();
            }
            int end=str1.length();
            str1.delete(0,end);
        }
        //System.out.println(list);
        if (list.size()==0){
            return null;
        }
        ArrayList<Integer> lenOfString=new ArrayList<>();
        for (int i=0;i<list.size();++i){
            lenOfString.add(list.get(i).length());
        }
        int maxlen=Collections.max(lenOfString);
        for (int j=0;j<list.size();++j){
            if (list.get(j).length()==maxlen){
                return list.get(j);
            }
        }
        return null;
    }
    @Test
    public void test08() {
        String s="91283472332";
        int myAtoi = myAtoi(s);
        System.out.println(myAtoi);
    }
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        StringBuilder str = new StringBuilder();
        String[] words = s.split(" ");
        String s2="";
        for (String s1 : words) {
            if (!s1.equals("")) {
                s2 = s1;
                break;
            }
        }
        if (s2.equals("")){
            return 0;
        }
        char c = s2.charAt(0);
        if (isNumber(c)) {
            for (int i = 0; i < s2.length(); ++i) {
                char ch = s2.charAt(i);
                if (!isNumber(ch)) {
                    break;
                }
                str.append(ch);
            }
        } else {
            if (c == '+') {
                str.append(c);
                char c2;
                for (int i = 1; i < s2.length(); ++i) {
                    c2 = s2.charAt(i);
                    if (!isNumber(c2)) {
                        break;
                    }
                    str.append(c2);
                }
            } else if (c == '-') {
                str.append(c);
                char c3;
                for (int i = 1; i < s2.length(); ++i) {
                    c3 = s2.charAt(i);
                    if (!isNumber(c3)) {
                        break;
                    }
                    str.append(c3);
                }
            }else{
                return 0;
            }
        }
        if (str.charAt(0) == '-') {
            String ss = str.substring(1, str.length());
            int res = Integer.parseInt(ss);
            res = 0 - res;
            if (res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                return res;
            }
        } else {
            String sss = str.substring(0, str.length());
            int ress = Integer.parseInt(sss);
            if (ress > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return ress;
            }
        }
    }
    private boolean isNumber(char c) {
        if (c - '0' >= 0 && c - '9' <= 0) {
            return true;
        } else {
            return false;
        }
    }
    @Test
    public void test09(){
        int[] nums={-3,-2,-5,3,-4};
        int i = threeSumClosest(nums, -1);
        System.out.println(i);
    }
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        Arrays.sort(nums);
        //这里不能要
       /* int len = nums.length - 1;
        if (nums[0] >= target) {
            return nums[0] + nums[1] + nums[2];
        }*/
        int min = 10000000;
        for (int i = 0; i < nums.length; ++i) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(target - sum) < Math.abs(min - target)) {
                    min = sum;
                }
                if (sum > target) {
                    right--;
                }else{
                    left++;
                }
            }
        }
        return min;
    }
    @Test
    public void test10(){
        List<List<Integer>> permute = permute(new int[]{1, 2, 3});

    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums.length < 2) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(nums[0]);
            res.add(new ArrayList<Integer>(list));
            return res;
        }
        int len = nums.length;
        List<Integer> path = new ArrayList<Integer>();
        boolean[] used = new boolean[len];//初始时全是false
        backTrack(res,path,nums,len,0,used);
        return res;
    }
    private void backTrack(List<List<Integer>> res, List<Integer> path,int[] nums, int len, int index, boolean[] used) {
        if (len == index) {
            res.add(new ArrayList<>(path));//深拷贝
            return;
        }
        // 在非叶子结点处，产生不同的分支，这一操作的语义是：
        // 在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; ++i) {
            if (!used[i]){
                path.add(nums[i]);
                used[i]=true;
                System.out.println("递归前=>"+path);
                backTrack(res,path,nums,len,index+1, used);
                //// 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                used[i]=false;
                path.remove(path.size()-1);
                System.out.println("递归后=>"+path);
            }
        }
    }
    @Test
    public void test11(){
        int[] nums={1,2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
    public void nextPermutation(int[] nums) {
        if (nums.length<1){
            return;
        }
        for (int i=0;i<nums.length-1;++i) {
            int left=i;
            int right=0;
            if (i+1==nums.length-1){
                right=nums.length-1;
            }else{
                right=i+1;
            }
            while (left <=right && right < nums.length) {
                if (nums[left] <nums[right]) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    return;
                }
                right++;
            }
        }
        Arrays.sort(nums);

    }
    @Test
    public void test12(){
        int[] nums={5,7,7,8,8,10};
        int[] ints = searchRange(nums, 8);
        System.out.println(Arrays.toString(ints));
    }
    public int[] searchRange(int[] nums, int target) {
        int[] indexes=new int[]{-1,-1};
        if (nums==null||nums.length<1){
            return indexes;
        }
        int len=nums.length;
        if (len==1){
            if (nums[0]==target){
                return new int[]{0,0};
            }else{
                return nums;
            }
        }
        binarySearch(nums,0,len-1,target,indexes);
        return indexes;
    }
    private void binarySearch(int[] nums, int l, int r, int target,int[] indexes) {
        int left = l;
        int right = r;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                int i=mid;
                while (i>=left&&nums[i]==target){
                    i--;
                }
                indexes[0]=++i;
                int j=mid;
                while (j<=right&&nums[j]==target){
                    j++;
                }
                indexes[1]=--j;
                return;
            } else if (nums[mid] > target) {
                right = mid - 1;
                continue;
            } else {
                left = mid + 1;
                continue;
            }
        }
    }
    @Test
    public void test13(){
        int[] arr={2,3,6,7};
        int target=7;
        List<List<Integer>> list = combinationSum(arr, target);
        System.out.println(list);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
    @Test
    public void test14(){
        int[] nums={3,2,1,0,4};
        boolean b = canJump(nums);
        System.out.println(b);

    }
    public boolean canJump(int[] nums) {
        List<Integer> list=new ArrayList<>();//存储每个位置可以到达的最远的位置的下标

        for(int i=0;i<nums.length;++i){
            list.add(i+nums[i]);
        }
        int maxJump=list.get(0);//存储当前可到达的最远位置
        int index=0;//存储当前所在位置
        while (index<nums.length&&index<=maxJump){
            if (maxJump<list.get(index)){
                maxJump=list.get(index);
            }
            index++;
        }
        if (index>=nums.length){
            return true;
        }
        return false;

    }
    @Test
    public void test15(){
        double v = myPow(2, 5);
        System.out.println(v);
    }
    public double myPow(double x, int n) {
       long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }
    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
