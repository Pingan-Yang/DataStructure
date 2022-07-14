package Tree.preTree;

import graph.test.Node;

import java.util.HashMap;

/**
 * @ClassName TriedNode
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/19 16:06
 * @Version:1.0
 */
public class TriedNode {
    public int pass;//记录有少字符串经过这个节点
    public int end;//记录有多少字符串以这个节点结尾
    //HashMap<Character, Node>;//当字符较多时，用hash表记录
    public TriedNode[] nexts;//记录这个节点的下一个节点有哪些

    public TriedNode() {
        this.pass = 0;
        this.end = 0;
        this.nexts = new TriedNode[26];//定义26个字符的节点 如果某个字符为空则代表没有经过这个节点
    }
}
