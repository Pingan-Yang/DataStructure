package graph.test;

import java.util.ArrayList;

/**
 * @ClassName Node
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/17 9:48
 * @Version:1.0
 */
public class Node {
    //节点的值
    public int value;
    //节点的入度
    public int in;
    //节点的出度
    public int out;
    //与该节点直接相邻的其他节点
    public ArrayList<Node> nexts;
    //与该节点直接相连的边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in=0;
        out=0;
        nexts=new ArrayList<>();
        edges=new ArrayList<>();
    }
}
