package graph;

import java.util.*;

/**
 * @ClassName Graph
 * @Description:
 * @Author:ypa
 * @Date 2021/5/31 20:57
 * @Version:1.0
 */
public class  Graph {
    public static void main(String[] args) {
        String[] vertex = new String[]{"A", "B", "C", "D", "E"};
        Graph graph = new Graph(vertex.length);
        for (int i = 0; i < vertex.length; ++i) {
            graph.add(vertex[i]);
        }
        graph.weightOfEdges(0, 1, 1);
        graph.weightOfEdges(0, 2, 1);
        graph.weightOfEdges(1, 2, 1);
        graph.weightOfEdges(1, 3, 1);
        graph.weightOfEdges(1, 4, 1);
        /*System.out.println("图的顶点个数是：" + graph.getNumsOfVertex());
        System.out.println("图对应的权重矩阵是：");
        graph.showEdges();
        System.out.println("A-B的权重是：" + graph.getWeightByIndex(0, 1));
        System.out.println("B-A的权重是：" + graph.getWeightByIndex(1, 0));*/
        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    private List<String> vertexLsit;//用于保存顶点
    private int[][] edges;//用于保存边的权值
    private int numsOfVertex;//保存顶点的个数
    private boolean[] isVisited;//用于保存节点是否被访问过

    public Graph(int n) {
        vertexLsit = new ArrayList<String>(n);
        edges = new int[n][n];
        numsOfVertex = 0;
    }

    public void add(String vertex) {
        /**
         * @Description: 添加顶点
         * @Param: [vertex]---顶点信息
         * @return: void
         * @Author: ypa
         * @Date: 2021/5/31
         */
        vertexLsit.add(vertex);
        numsOfVertex++;
    }

    public void weightOfEdges(int v1, int v2, int weight) {
        /**
         * @Description:
         * @Param: [v1, v2, weight]=[顶点1，顶点2，权值]
         * @return: void
         * @Author: ypa
         * @Date: 2021/5/31
         */
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

    public int getNumsOfVertex() {
        /**
         * @Description: 获取顶点的个数
         * @Param: []
         * @return: int
         * @Author: ypa
         * @Date: 2021/5/31
         */
        return numsOfVertex;
    }

    public int getWeightByIndex(int v1, int v2) {
        /**
         * @Description: 获取两个顶点之间的边的权重
         * @Param: [v1, v2]
         * @return: int
         * @Author: ypa
         * @Date: 2021/5/31
         */
        return edges[v1][v2];
    }

    public String getValueByIndex(int index) {
        /**
         * @Description: 根据下标返回顶点
         * @Param: [index]
         * @return: java.lang.String
         * @Author: ypa
         * @Date: 2021/6/3
         */
        return vertexLsit.get(index);
    }

    public void showEdges() {
        /**
         * @Description: 显示图的矩阵信息
         * @Param: []
         * @return: void
         * @Author: ypa
         * @Date: 2021/5/31
         */
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /*
      图的深度优先搜索(Depth First Search) 。
        深度优先遍历，从初始访问结点出发，初始访问结点可能有多个邻接结点
        深度优先遍历的策略就是首先访问第一个邻接结点，然后再以这个被访问的邻接结点作为初始结点,访问它的第一个邻接结点
        可以这样理解：每次都在访问完当前结点后首先访问当前结点的第一个邻接结点。
        我们可以看到，这样的访问策略是优先往纵向挖掘深入，而不是对一个结点的所有邻接结点进行横向访问。
        显然，深度优先搜索是一个递归的过程

        深度优先遍历算法步骤
            ①：访问初始结点v，并标记结点v为已访问。
            ②：查找结点v的第一个邻接结点w。
            ③：若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续。
            ④：若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）。
            ⑤：查找结点v的w邻接结点的下一个邻接结点，转到步骤3。
    * */
    private int getNeighbor(int index) {
        /**
         * @Description: 得到第一个邻接节点的下标w
         * @Param: [index] 当前访问的节点下标
         * @return: int 若当前节点存在邻接节点，则返回当前节点的邻接节点，否则返回-1
         * @Author: ypa
         * @Date: 2021/6/3
         */
        for (int j = 0; j < vertexLsit.size(); ++j) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    private int getNextNeighbor(int v1, int v2) {
        /**
         * @Description: 根据前一个节点的邻接下标，来获取下一个节点的邻接下标
         * @Param: [v1, v2] v1是前一个节点的下标，v2是前一个节点的邻接下标
         * @return: int
         * @Author: ypa
         * @Date: 2021/6/3
         */
        for (int j = v2 + 1; j < vertexLsit.size(); ++j) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    private void dfs(boolean[] isVisited, int i) {
        /**
         * @Description: 对一个节点进行深度优先遍历
         * @Param: [isVisited, i] 存放该节点被访问的状态，节点下标
         * @return: void
         * @Author: ypa
         * @Date: 2021/6/3
         */
        //1、首先访问该节点并输出
        System.out.print(getValueByIndex(i) + "->");
        //2、将该节点设置成已访问
        isVisited[i] = true;
        //3、查询节点i的第一个邻接节点
        int w = getNeighbor(i);
        while (w != -1) {//说明第一个邻接节点存在
            if (isVisited[w] != true) {//说明该节点没有被访问
                dfs(isVisited, w);
            } else {//说明该邻接节点已被访问
                w = getNextNeighbor(i, w);//获取当前节点的下一个连接点
            }
        }
    }

    public void dfs() {
        isVisited = new boolean[numsOfVertex];
        /**
         * @Description: 遍历所有的结点，进行dfs[回溯]
         * @Param: []
         * @return: void
         * @Author: ypa
         * @Date: 2021/6/3
         */
        for (int i = 0; i < vertexLsit.size(); ++i) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /*
        广度优先遍历基本思想
            图的广度优先搜索(Broad First Search) 。
            类似于一个分层搜索的过程，广度优先遍历需要使用一个队列以保持访问过的结点的顺序，
            以便按这个顺序来访问这些结点的邻接结点

        广度优先遍历算法步骤
            1、访问初始结点v并标记结点v为已访问。
            2、结点v入队列
            3、当队列非空时，继续执行，否则算法结束。
            4、出队列，取得队头结点u。
            5、查找结点u的第一个邻接结点w。
            6、若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
                6.1 若结点w尚未被访问，则访问结点w并标记为已访问。
                6.2 结点w入队列
                6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6。
    * */
    private void bfs(boolean[] isVisited, int i) {
        int u;//记录节点的下标
        int w;//当前节点的下一个邻接节点
        Deque queue = new LinkedList();//用于记录访问节点的顺序

        System.out.print(getValueByIndex(i) + "=>");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst();
            w = getNeighbor(u);
            if (w != -1) {
                if (isVisited[w] != true) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                } else {
                    //以u为前驱  找w的下一个邻接点-------这里体现广度优先遍历
                    getNextNeighbor(u, w);
                }
            }
        }

    }
    public void bfs(){
        isVisited = new boolean[numsOfVertex];
        for (int i=0;i<vertexLsit.size();++i){
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

}
