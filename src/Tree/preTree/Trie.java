package Tree.preTree;


import org.junit.Test;

/**
 * @ClassName Trie
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/19 16:12
 * @Version:1.0
 */
public class Trie {
    private TriedNode root;

    public Trie() {
        this.root = new TriedNode();
    }

    /**
     * 建立前缀树的过程
     *
     * @param word
     */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        //将字符串转化为字符数组
        char[] chs = word.toCharArray();
        //从根节点开始
        TriedNode node = root;
        //根节点的遍历数加1
        node.pass++;
        //记录字符的下标
        int index = 0;
        for (int i = 0; i < chs.length; ++i) {
            index = chs[i] - 'a';
            //如果node[index]==null代表没有这个字符，则新建这个节点
            if (node.nexts[index] == null) {
                node.nexts[index] = new TriedNode();
            }
            //此时node来到现在这个字符的位置
            node = node.nexts[index];
            //当前节点的遍历数加1
            node.pass++;
        }
        //整个字符数组走完，代表字符串结束。则当前这个字符是字符串的结尾.
        //以当前字符结尾的遍历树加1
        node.end++;
    }

    /**
     * 返回字符串数组中 以pre字符串做前缀的数量
     *
     * @param pre
     * @return
     */
    public int prfixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        //将字符串转化为字符数组
        char[] chs = pre.toCharArray();
        //从根节点开始搜索
        TriedNode node = root;
        //记录字符的下标
        int index = 0;
        for (int i = 0; i < chs.length; ++i) {
            //找到当前字符所在的位置
            index = chs[i] - 'a';
            //如果没找到 则证明没有以pre作为前缀的字符串，返回0；
            if (node.nexts[index] == null) {
                return 0;
            }
            //节点不断移动，直到移动到最后一个字符的位置
            node = node.nexts[index];
        }
        //返回最后一个字符pass
        return node.pass;
    }

    /**
     * 搜索一个字符串在这个字符数组中出现了多少次
     * @param word
     * @return
     */
    public int search(String word){
        if (word==null){
            return 0;
        }
        //将字符串转化为字符数组
        char[] chs = word.toCharArray();
        //从根节点开始搜索
        TriedNode node=root;
        //记录字符出现的位置
        int index=0;
        for (int i=0;i<chs.length;++i){
            index=chs[i]-'a';
            //如果node.nexts[index]==null，表示该字符从未出现，则表示这个字符串也没有出现，返回0
            if (node.nexts[index]==null){
                return 0;
            }
            //节点不断后移 直到最后一个字符
            node=node.nexts[index];
        }
        //返回以当前节点结束的字符串的数量
        return node.end;
    }

    /**
     * 删除一个字符串
     * @param word
     */
    public void delete(String word){
        if (word==null){
            return;
        }
        //首先要确保这个字符串在这个前缀树中
        if (search(word)!=0){
            //将字符串转化为字符数组
            char[] chs = word.toCharArray();
            //从根节点开始遍历
            TriedNode node=root;
            //根节点的遍历数减1
            node.pass--;
            //记录字符出现的位置
            int index=0;
            for (int i=0;i<chs.length;++i){
                index=chs[i]-'a';
                //如果当前字符的遍历数已经是0，则代表就不会有字符串在经过后面的字符，需要置空
                if (--node.nexts[index].pass==0){
                    node.nexts[index]=null ;
                    return;
                }
                //节点后移
               node=node.nexts[index];
            }
            //如果到最后的节点的pass仍然大于0，则将它的end值减1，代表该字符已被删除
            node.end--;
        }
    }


    @Test
    public void test01(){
        String[]strings={"abc","bce","mnhgkld","grdfs","abcfdrc","dsdawefa","abc","abc"};
        Trie trie = new Trie();
        for (String s:strings){
            trie.insert(s);
        }
        int num = trie.search("abc");
        System.out.println("numbers of abc is "+num);
        int num2 = trie.prfixNumber("ab");
        System.out.println("numbers of ab's prrfix is "+num2);
        trie.delete("abc");
        num = trie.search("abc");
        System.out.println("numbers of abc is "+num);
    }
}
