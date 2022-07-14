package graph.test;

/**
 * @ClassName Edge
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/17 9:49
 * @Version:1.0
 */

/**
 * 边的信息：
 */
public class Edge {
    //from是边的出发点
    public Node from;
    //to是边的结束点
    public Node to;
    //weight 边上的权重
    public int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
