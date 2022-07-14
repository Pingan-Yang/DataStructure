package graph.test;


import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Graph
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/17 9:44
 * @Version:1.0
 */
public class Graph {
    //存放节点的详细信息
    //key：表示点的编号
    //value:代表节点的详细信息（值、入度、出度、直接相邻的节点、边）
    public HashMap<Integer, Node> nodes;
    //存放边的信息。
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
