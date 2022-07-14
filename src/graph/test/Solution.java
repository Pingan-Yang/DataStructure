package graph.test;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.junit.Test;

import java.awt.event.FocusEvent;
import java.util.*;

/**
 * @ClassName Solution
 * @Description: TODO
 * @Author 15368
 * @Date 2022/1/17 10:09
 * @Version:1.0
 */
public class Solution {
    /**
     * matrix是多行三列的二维矩阵
     * 0列：表示出发的点
     * 1列：到达的点
     * 2列：边的权重
     *
     * @param matrix
     * @return
     */
    public Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; ++i) {
            int from = matrix[i][0];
            int to = matrix[i][1];
            int weight = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(fromNode, toNode, weight);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }

    /**
     * 图的广度优先遍历
     * ①：利用队列实现
     * ②：从源节点开始依次按照宽度进入队列，然后弹出
     * ③：在弹出一个点，把该节点所有没有进入过队列的邻接点放入到队列中
     * ④：直至队列为空
     *
     * @param node
     */
    public void bfs(Node node) {
        if (node == null) {
            return;
        }
        //用于存放节点的队列
        Queue<Node> queue = new LinkedList<>();
        //用于记录已经便遍历过的节点，防止出现环的时候有死循环
        Set<Node> set = new HashSet<>();
        //将源节点入对
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            //这里对弹出的节点做相应的处理
            Node cur = queue.poll();
            System.out.print(cur.value + " ");
            //获取该节点的所有邻接节点
            for (Node next : cur.nexts) {
                //如果该邻接节点已经遍历过，则不在放入队列
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

    @Test
    public void test01() {
        Integer[][] matirx = new Integer[12][3];
        matirx[0] = new Integer[]{1, 2, 10};
        matirx[1] = new Integer[]{1, 4, 11};
        matirx[2] = new Integer[]{1, 5, 12};
        matirx[3] = new Integer[]{2, 3, 13};
        matirx[4] = new Integer[]{4, 3, 14};
        matirx[5] = new Integer[]{5, 3, 15};

        matirx[6] = new Integer[]{2, 1, 10};
        matirx[7] = new Integer[]{4, 1, 11};
        matirx[8] = new Integer[]{5, 1, 12};
        matirx[9] = new Integer[]{3, 2, 13};
        matirx[10] = new Integer[]{3, 4, 14};
        matirx[11] = new Integer[]{3, 5, 15};
        Graph graph = createGraph(matirx);
        // System.out.println(graph.nodes.get(1).value);
      /*  for (Map.Entry<Integer,Node> node:graph.nodes.entrySet()){
            System.out.print(node.getValue().value+"  ");
        }*/

        Iterator<Edge> iterator = graph.edges.iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            System.out.println(edge.from.value + "==>" + edge.to.value);
        }
    }

    @Test
    public void test02() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.nexts.add(node2);
        node1.nexts.add(node4);
        node1.nexts.add(node5);
        node2.nexts.add(node3);
        node4.nexts.add(node3);
        node5.nexts.add(node3);

        //bfs(node1);
        dfs(node1);
    }


    /**
     * 图的深度优先遍历
     * ①：利用栈实现
     * ②：从源节点开始把节点按照深度放入站，然后弹出
     * ③：每弹出一个节点，把该节点下一个没有进入过栈的邻接节点放入栈
     * ④：直到栈空
     *
     * @param node
     */
    public void dfs(Node node) {
        if (node == null) {
            return;
        }
        //用于存放节点信息
        Stack<Node> stack = new Stack<>();
        //记录已经遍历过的点，避免在环中出现死循环
        Set<Node> set = new HashSet<>();
        //将源节点入栈
        stack.add(node);
        //将已经遍历过的节点，放入到set中
        set.add(node);
        //处理数据
        System.out.print(node.value + " ");
        while (!stack.isEmpty()) {
            //弹出栈顶元素
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                //遍历当前节点的所有邻接节点，当遇到没有遍历过的元素的时候，才会压栈
                if (!set.contains(next)) {
                    //因为stack记录的是深度遍历的顺序，所以要记得把弹出栈顶的元素再次压入栈中
                    stack.add(cur);
                    //将cur的邻接节点压入栈中
                    stack.add(next);
                    //记录next以访问
                    set.add(next);
                    //处理数据
                    System.out.print(next.value + " ");
                    break;
                }
            }
        }
    }

    /**
     * 图的拓扑排序
     * 适用范围：要求有向图，且有入度为0的节点，且无环
     * 算法步骤
     * ①：从图中选择一个入度为0的节点输出。
     * ②：删除此节点和从这条点出发的边
     * ③：重复上述两个步骤，直至不存在入度为0的节点为止
     *
     * @param graph
     * @return
     */
    public List<Node> sortTopology(Graph graph) {
        //用于记录节点编号及其剩余的入度
        //key:节点编号
        //value:节点的剩余入度
        Map<Node, Integer> inMap = new HashMap<>();
        //记录入度为0的节点
        Queue<Node> zeroInQueue = new LinkedList<>();
        //获取当前整张图所有节点的入度信息以及入度为0的节点的信息
        for (Node cur : graph.nodes.values()) {
            inMap.put(cur, cur.in);
            if (cur.in == 0) {
                zeroInQueue.add(cur);
            }
        }
        //最终返回的结果，即拓扑排序的顺序
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            //弹出队首入度为0的第一个节点
            Node cur = zeroInQueue.poll();
            //将该节点加入到结果中
            result.add(cur);
            //“删除”当前节点，即让当前节点的邻接节点的入度都减少1
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    //如果当中某个节点的入度为0，则入队
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

    @Test
    public void test03() {
        Integer[][] matirx = new Integer[6][3];
        matirx[0] = new Integer[]{1, 2, 10};
        matirx[1] = new Integer[]{1, 3, 11};
        matirx[2] = new Integer[]{1, 5, 12};
        matirx[3] = new Integer[]{2, 3, 13};
        matirx[4] = new Integer[]{3, 4, 14};
        matirx[5] = new Integer[]{4, 5, 15};
        // matirx[6]=new Integer[]{5,3,15};

        Graph graph = createGraph(matirx);
        List<Node> result = sortTopology(graph);
        for (Node cur : result) {
            System.out.print(cur.value + " ");
        }
    }

    //---------------------最小生成树算法---------------------------------------

    /**
     * 最小生成树：将构造连通网的最小代价生成树称为最小生成树（即保证连通性，且所有边的权值之和是最小的）
     * kruska算法适用范围无向图。
     * 算法思想：
     * kruskalMST找最小的边，同时要考虑是否会生成环的问题。
     * 步骤：
     * ①：找到权值最小的边的顶点。
     * ②：加入边之后是否形成环，若没有，则加上（难点：如何在考察加上一个边之后会不会形成环）
     * ③：重复上述步骤
     * <p>
     * 如何为判断加入一个边之后会不会形成环：
     * ①：假设所有的点自己是一个集合（即所有的点都不连通）
     * ②：依次考察最小的边，若边的from和to不在一个集合中则不会形成环，则加上，并且将边上的两个顶点的集合合在一起，反之，则不加
     * ③：重复②
     *
     * @param graph
     * @return
     */
    public Set<Edge> kruskalMST(Graph graph) {
        //获取图中所有的节点信息
        Collection<Node> values = graph.nodes.values();
        //初始化Myset，使得每个节点的所在的集合都仅含它本身
        MySets mySets = new MySets(values);
        //初始化小根堆，存放边的权重（从小到大）
        PriorityQueue<Edge> queue = new PriorityQueue<>(((o1, o2) -> o1.weight - o2.weight));
        //将每条边放入到小根堆中
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        //要返回的结果，即最小生成树的边
        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()) {
            //求出权重最小的边
            Edge cur = queue.poll();
            //检查边的两个节点是否在同一个集合中
            if (!mySets.isSameSet(cur.from, cur.to)) {
                //不在同一个集合中，则将该边加入到result集合中
                result.add(cur);
                //并且将from节点和to节点所指向的集合，合并为一个
                mySets.union(cur.from, cur.to);
            }
        }
        //返回结果
        return result;
    }


    /**
     * 自己写的一个结构用来代替并查集结构
     */
    class MySets {
        //记录节点所对应的集合
        public Map<Node, List<Node>> setMap = new HashMap<>();

        //初始化每个节点，所应一个的集合（即自己对应自己）
        public MySets(Collection<Node> nodes) {
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        /**
         * 查看两个节点对应的集合是否是同一个集合
         *
         * @param from
         * @param to
         * @return
         */
        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        /**
         * 将两个节点的集合合并成同一个集合
         *
         * @param from
         * @param to
         */
        public void union(Node from, Node to) {
            //获取from节点的集合
            List<Node> fromSet = setMap.get(from);
            //获取to节点的集合
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                //将to节点集合中的每一个元素都放入到from节点所对应的集合中
                fromSet.add(toNode);
                //将to集合的每一个节点都对应的集合都指向from节点所对应的集合中
                setMap.put(toNode, fromSet);
            }
        }
    }


    /**
     * Prim算法：要求无向图
     * ①：从任意一个点开始，选择一个点，解锁该点和它的所有边
     * ②：从①中挑选一个权值最小的边，并且解锁与该边直接相连的点
     * ③：再次在已经解锁的点和边中挑选最小的边，若该边连接的两个点已经被选择过了，则不能再选，选过的边也不能再选
     * ④：重复以上步骤，直到所有点都被解锁
     *
     * @param graph
     * @return
     */
    public Set<Edge> primMST(Graph graph) {
        //新建一个小根堆，将边的权值从小到大排列
        PriorityQueue<Edge> queue = new PriorityQueue<>(((o1, o2) -> o1.weight - o2.weight));
        //记录已经遍历过的节点
        Set<Node> set = new HashSet<>();
        //存放最小生成树的边
        Set<Edge> result = new HashSet<>();
        //为了处理森林问题，如果图已经是连通的，则可去掉for循环
        for (Node node : graph.nodes.values()) {//随便挑选一个点
            //node是开始点
            if (!set.contains(node)) {
                set.add(node);
                //将当前节点的所有边放入到小根堆中
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()) {
                    //取出当前边中，权值最小的边
                    Edge edge = queue.poll();
                    //找到这条边的to节点
                    Node toNode = edge.to;
                    if (!set.contains(toNode)) {
                        //将该节点加入到已经访问的hash表中证明被访问过了
                        set.add(toNode);
                        //将该边加入到结果中
                        result.add(edge);
                        //解锁to节点的所有边 ，并加入到小根堆中
                        for (Edge nextEdge : toNode.edges) {
                            queue.add(nextEdge);
                        }
                    }
                }
            }
            //break;
        }
        return result;
    }


    @Test
    public void test04() {
        Integer[][] matirx = new Integer[14][3];
        matirx[0] = new Integer[]{1, 2, 10};
        matirx[1] = new Integer[]{1, 3, 21};
        matirx[2] = new Integer[]{1, 5, 12};
        matirx[3] = new Integer[]{2, 3, 13};
        matirx[4] = new Integer[]{3, 4, 1};
        matirx[5] = new Integer[]{4, 5, 15};
        matirx[6] = new Integer[]{5, 3, 7};


        matirx[7] = new Integer[]{2, 1, 10};
        matirx[8] = new Integer[]{3, 1, 21};
        matirx[9] = new Integer[]{5, 1, 12};
        matirx[10] = new Integer[]{3, 2, 13};
        matirx[11] = new Integer[]{4, 3, 1};
        matirx[12] = new Integer[]{5, 4, 15};
        matirx[13] = new Integer[]{3, 5, 7};


        Graph graph = createGraph(matirx);
        //Set<Edge> edges = kruskalMST(graph);
        Set<Edge> edges = primMST(graph);
        //System.out.println(edges);
        for (Edge cur : edges) {
            System.out.print(cur.weight + " ");
        }
    }

    //------------------最短路径---------------------

    /**
     * 迪杰特斯拉算法（dijkstra）：
     * 从原点出发到各个节点的最小距离
     * 适用范围：没有权值和为负数的环（一般要求没有权值为负数的边）
     * 步骤：
     * ①：初始时，只有到自己的距离为0，到其他的点的距离初始化为正无穷
     * ②：在已经初始化号的表中选择距离最短的点
     * ③：看②中已经选好的点的边能不能使从原点出发到各个节点的距离变少 ，如果有就更新表。
     * ④: ②中的点的边的信息被使用完之后，这个点就被锁死，以后再也不用这个点的信息
     * ⑤：在剩下的未被锁死的记录中再挑选一个距离最短的边，重复上述过程，直至所有记录被锁死。
     *
     * @param head
     * @return
     */
    public HashMap<Node, Integer> dijkstar1(Node head) {
        //记录从head开始到node节点的最短距离
        //key：从head出发要到达的node节点
        //value：从head出发到node节点的最小距离
        //节点中没有记录代表无限大
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        //初始化 从头节点到自己的距离为0;
        distanceMap.put(head, 0);
        //用于记录已经被锁死的记录节点
        Set<Node> selectedNodes = new HashSet<>();
        //在表中选择未锁死的记录的最小值
        Node minNode = getMinNodeAndUnselected(distanceMap, selectedNodes);
        //查看选择的点的最小距离和它的边的权值
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    //如果记录中不含有这个节点，则证明是无穷大，则需要更新当前节点
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    //如果有记录，并且当前的节点的边的权重能是距离变得更小，也要跟新
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            //该节点处理完毕，锁死记录
            selectedNodes.add(minNode);
            //获取未锁死的点的最小距离
            minNode = getMinNodeAndUnselected(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private Node getMinNodeAndUnselected(HashMap<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        Integer min = Integer.MAX_VALUE;
        Node minNode = null;
        for (Map.Entry<Node, Integer> cur : distanceMap.entrySet()) {
            Node node = cur.getKey();
            Integer distance = cur.getValue();
            if (!selectedNodes.contains(node) && min > distance) {
                min = distance;
                minNode = node;
            }
        }
        return minNode;
    }

    @Test
    public void test05() {
        Integer[][] matirx = new Integer[16][3];
        matirx[0] = new Integer[]{1, 2, 3};
        matirx[1] = new Integer[]{1, 3, 15};
        matirx[2] = new Integer[]{1, 4, 9};
        matirx[3] = new Integer[]{2, 3, 2};
        matirx[4] = new Integer[]{2, 5, 200};
        matirx[5] = new Integer[]{3, 4, 7};
        matirx[6] = new Integer[]{3, 5, 14};
        matirx[7] = new Integer[]{4, 5, 16};

        matirx[8] = new Integer[]{2, 1, 3};
        matirx[9] = new Integer[]{3, 1, 15};
        matirx[10] = new Integer[]{4, 1, 9};
        matirx[11] = new Integer[]{3, 2, 2};
        matirx[12] = new Integer[]{5, 2, 200};
        matirx[13] = new Integer[]{4, 3, 7};
        matirx[14] = new Integer[]{5, 3, 14};
        matirx[15] = new Integer[]{5, 4, 16};

        Graph graph = createGraph(matirx);
        Node node = graph.nodes.get(1);
        HashMap<Node, Integer> distanceMap = dijkstar1(node);
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            System.out.println(node.value+"-->"+entry.getKey().value+"  "+entry.getValue() + " ");
        }
    }
}
