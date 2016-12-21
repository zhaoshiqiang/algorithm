package graph;

import com.sun.scenario.effect.impl.prism.PrImage;
import util.MinPriorityQueue;

import java.util.*;

/**
 * 图的邻接表表示
 * Created by zhaoshiqiang on 2016/12/17.
 */
public class Adjacency {

    private final int V;    //顶点数目
    private int E;  //边的数目
    private Map<Integer,List<adjNode>> adj; //邻接表，用map来表示邻接表是亮点

    public Adjacency(int v) {
        V = v;
        this.E=0;
        this.adj=new HashMap<Integer, List<adjNode>>();   //创建邻接表

        for (int i=1 ; i<= v; i++){
            this.adj.put(i,null);
        }
    }
    //获取顶点数目
    public int getV() {
        return V;
    }
    //获取边的数目
    public int getE() {
        return E;
    }

    public Map<Integer, List<adjNode>> getAdj() {
        return adj;
    }

    /**
     *
     * @param v1 图的点
     * @param v2 图的点
     * @return 返回v1到v2的权值，两点不链接则返回100，表示最大值
     */
    public int getWeight(int v1,int v2){
        List<adjNode> adjNodeList = adj.get(v1);
        int weight = 100;
        for (adjNode node : adjNodeList){
            if (node.getAdjvex() == v2){
                weight = node.getWeight();
            }
        }
        return weight;
    }
    /**
     * 添加无向无权图的边
     * @param v1 边的一个顶点
     * @param v2 另一个顶点
     */
    public void add_Undirect_Unweight_Edge(int v1,int v2){
        List<adjNode> v1list = adj.get(v1);
        if (v1list == null){
            v1list = new ArrayList<adjNode>();
            v1list.add(new adjNode(v2));
            adj.put(v1,v1list);
        }else {
            v1list.add(new adjNode(v2));
        }

        List<adjNode> v2list = adj.get(v2);
        if (v2list == null){
            v2list = new ArrayList<adjNode>();
            v2list.add(new adjNode(v1));
            adj.put(v2,v2list);
        }else {
            v2list.add(new adjNode(v1));
        }

        E++;
    }

    /**
     * 添加无向有权图的边
     * @param v1 一个顶点
     * @param v2 另一个顶点
     * @param weight 权重
     */
    public void add_Undirect_weight_Edge(int v1,int v2,int weight){
        List<adjNode> v1list = adj.get(v1);
        if (v1list == null){
            v1list = new ArrayList<adjNode>();
            v1list.add(new adjNode(v2,weight));
            adj.put(v1,v1list);
        }else {
            v1list.add(new adjNode(v2,weight));
        }

        List<adjNode> v2list = adj.get(v2);
        if (v2list == null){
            v2list = new ArrayList<adjNode>();
            v2list.add(new adjNode(v1,weight));
            adj.put(v2,v2list);
        }else {
            v2list.add(new adjNode(v1,weight));
        }

        E++;
    }

    /**
     * 添加有向有权图的边
     * @param v1 一个顶点
     * @param v2 另一个顶点
     * @param weight 权重
     */
    public void add_direct_weight_Edge(int v1,int v2,int weight){
        List<adjNode> v1list = adj.get(v1);
        if (v1list == null){
            v1list = new ArrayList<adjNode>();
            v1list.add(new adjNode(v2,weight));
            adj.put(v1,v1list);
        }else {
            v1list.add(new adjNode(v2,weight));
        }

        E++;
    }
    /**
     * 添加有向无权图的边
     * @param v1 一个顶点
     * @param v2 另一个顶点
     */
    public void add_direct_unweight_Edge(int v1,int v2){
        List<adjNode> v1list = adj.get(v1);
        if (v1list == null){
            v1list = new ArrayList<adjNode>();
            v1list.add(new adjNode(v2));
            adj.put(v1,v1list);
        }else {
            v1list.add(new adjNode(v2));
        }

        E++;
    }

    /**
     *
     * @param v1 要计算度的节点
     * @return v1的度
     */
    public int degree(int v1){
        return adj.get(v1) == null ? 0 : adj.get(v1).size();
    }

    /**
     * 深度优先遍历
     * @param v1 要遍历的节点
     * @param visited 已经访问过的节点列表
     */
    public void DFSTraval(Integer v1,List<Integer> visited){
        if (visited.contains(v1))
            return;
        visited.add(v1);
        System.out.println("开始遍历节点： " + v1);
        List<adjNode> adjNodes = adj.get(v1);
        for (int i=0; i< adjNodes.size() ; i++){
            DFSTraval(adjNodes.get(i).getAdjvex(),visited);
        }
        System.out.println("结束遍历节点： " + v1);
    }

    /**
     * 广度优先遍历
     * @param v1 要遍历的节点
     * @param visited 已经访问过的节点列表
     */
    public void BFSTraval(Integer v1,List<Integer> visited){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(v1);
        while (!q.isEmpty()){
            int v = q.poll();
            if (!visited.contains(v)){
                visited.add(v);
                System.out.println("遍历节点： " + v);
                List<adjNode> adjNodes = adj.get(v);
                for (int i=0; i< adjNodes.size(); i++){
                    q.offer(adjNodes.get(i).getAdjvex());
                }
            }
        }
    }

    /**
     * 拓扑排序说需要的深度优先遍历
     * @param stack 栈
     * @param visited 用于标记对应节点是否已经访问过来
     * @param node 访问的节点
     */
    private void DFSTraval_sort(Stack<Integer> stack,boolean[] visited,int node){

        List<adjNode> list = adj.get(node);
        if (list !=null){
            //采用DFS的思想，根据递归过程，很容易看出入度为0的点是最后被加入到栈中的。
            //因为只有该点的所有邻接点都加入到栈之后，这个点才会被加入到栈
            for (adjNode adjNode : list){
                if (visited[adjNode.getAdjvex()] == false){
                    //深度优先遍历
                    DFSTraval_sort(stack,visited,adjNode.getAdjvex());
                }
            }
        }
        visited[node] = true;
        //将入栈操作放到最后，这样就使得对每一条有向边(u, v)，u都在v的上面，
        // 这样打印时，直接出栈操作就是拓扑排序后的结果
        stack.push(node);
    }

    /**
     * 拓扑排序（利用深度优先遍历）
     * 拓扑排序是对有向无环图的顶点进行排序，使得对每一条有向边(u, v)，均有u（在排序记录中）比v先出现。
     * 可以将图的拓扑排序看做是将图的所有节点在一条线上排开，图的所有有向边都从左到右
     */
    public void Topological_sort(){
        boolean[] visited = new boolean[this.V+1];  //标记节点是否访问
        //初始化都未访问
        for (int i=1; i<=this.V; i++){
            visited[i]=false;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i=1 ; i<=this.V; i++){
            if (visited[i] == false){
                DFSTraval_sort(stack,visited,i);
            }
        }
        //打印栈中的节点
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+"   ");
        }
    }

    /**
     * Prim和 Dijkstra算法异同点：
     * 异：
     *   差别只在于最后循环体内的松弛操作。
     *       最小生成树（Prim）只关心所有边的和最小，所以有v.key = w(u, v)，即每个点直连其他点的最小值（最多只有两个节点之间的权值和）
     *       最短路径树（Dijkstra）只搜索权值最小，所以有v.key = w(u, v) + u.key，即每个点到其他点的最小值（最少是两个节之间的权值和）
     *   简单总结就是，Dijkstra的松弛操作加上了到起点的距离，而Prim只有相邻节点的权值
     * 同：
     *   1、思想相同：
     *       都是用贪婪思想
     *   2、时间复杂度：
     *       Time = θ( V * T1 + E * T2)
     *       其中T1为取出键值最小点的时间，T2为降低键值和调整队列的时间，取决于数据结构。
     *           数组
     *           T1= O(V), T2 = O(1), TIME = O(V * V + E) = O(V * V)
     *           二叉堆
     *           T1 = O(lgV), T2 = O(lgV), TIME = O(V * lgV + E * lgV)
     *           斐波那契堆
     *           T1 = O(lgV), T2 = O(1), TIME = O(V * lgV + E) = O(V * lgV)
     *       对于稀疏图来说，E远小于V*V，所以二叉堆比较好；
     *       而对于密集图来说，E=V*V，所以数组比较好；
     *       斐波那契堆是最好的情况。
     * */
    private void initialize(int start,MinPriorityQueue<auxNode> queue){
        //对最小优先队列初始化
        Map<Integer, List<adjNode>> map = getAdj();
        for (Integer vertex : map.keySet()){
            if (vertex == start){
                //初始节点要设置为0
                queue.offer(new auxNode(vertex, 0, null));
            }else {
                //其他节点全部初始化为无穷大，这里用1000表示
                queue.offer(new auxNode(vertex, 1000, null));
            }
        }
    }

    private void printfVisited(List<auxNode> visitedList){
        //打印最小生成树
        for (int i=0; i< visitedList.size(); i++){
            auxNode p = visitedList.get(i);
            System.out.println("节点：" + p.getAdjvex()
                    + " 权值为：" + p.getKey() +
                    " 父节点为： " + (p.getParent() != null ? p.getParent().getAdjvex() : 0));
        }
    }
    /**
     * prim构建最小生成树，即树中所有边的权值之和最小，只能用于无向图
     * @param start
     */
    public void MST_PRIM(int start){
        List<auxNode> visitedList = new ArrayList<auxNode>(); //最小生成树列表，按照先后顺序添加
        //按照权重大小构建最小优先队列
        MinPriorityQueue<auxNode> queue = new MinPriorityQueue<>(getV(),
                new Comparator<auxNode>() {
                    @Override
                    public int compare(auxNode o1, auxNode o2) {
                        if (o1.getKey() < o2.getKey()){
                            return -1;
                        }else if (o1.getKey() == o2.getKey()){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                });
        initialize(start,queue);

        while ( !queue.isEmpty()){
            //从优先队列中获取最小值队友的节点
            auxNode node = queue.poll();
            //添加到生成树中
            visitedList.add(node);
            //获取与与node相连的节点，遍历每个节点然后对其做松弛操作
            List<adjNode> adjNodeList = getAdj().get(node.getAdjvex());
            for (adjNode v : adjNodeList){
                int index = queue.indexOf(new auxNode(v.getAdjvex(),1000,null));
                //该节点在优先队列中
                if (index != -1){
                    Object[] objects = queue.getQueue();
                    //获取优先队列中的与node相连的节点
                    auxNode temp = (auxNode)objects[index];
                    int weight = getWeight(node.getAdjvex(),temp.getAdjvex());
                    //如果temp的权重大于weight，则更新
                    if (temp.getKey() > weight){
                        temp.setKey(weight);
                        temp.setParent(node);
                        //重新调整优先队列
                        queue.siftUp(index,temp);
                    }
                }
            }
        }

        printfVisited(visitedList);
    }
    /*
    * 当边的权值都为1的时候，可以用DFS（广度优先搜索）优化时间复杂度。
    * 使用FIFO（先进先出）队列代替优先队列，优化了降低键值T2的操作为O(1)
    * 松弛操作改为
    *   if d[v] = +∞ {
            d[v] = d[u] + 1
            enqueue(Q, v)
        }
      优化了取出键值最小点的时间T1 = O(1)
      总的时间复杂度：TIME = V + E
    * */
    /**
     * 迪杰斯特拉算法用于构建单源点的最短路径树(MST)——即树中某个点到任何其他点的距离都是最短的
     * 可以用于有向图，但是不能存在负权值
     * @param start
     */
    public void Dijkstra(int start){
        List<auxNode> visitedList = new ArrayList<auxNode>(); //最小生成树列表，按照先后顺序添加
        //按照权重大小构建最小优先队列
        MinPriorityQueue<auxNode> queue = new MinPriorityQueue<>(getV(),
                new Comparator<auxNode>() {
                    @Override
                    public int compare(auxNode o1, auxNode o2) {
                        if (o1.getKey() < o2.getKey()){
                            return -1;
                        }else if (o1.getKey() == o2.getKey()){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                });
        initialize(start,queue);

        while ( !queue.isEmpty()){
            //从优先队列中获取最小值队友的节点
            auxNode node = queue.poll();
            //添加到生成树中
            visitedList.add(node);
            //获取与与node相连的节点，遍历每个节点然后对其做松弛操作
            List<adjNode> adjNodeList = getAdj().get(node.getAdjvex());
            for (adjNode v : adjNodeList){
                int index = queue.indexOf(new auxNode(v.getAdjvex(),1000,null));
                //该节点在优先队列中
                if (index != -1){
                    Object[] objects = queue.getQueue();
                    //获取优先队列中的与node相连的节点
                    auxNode temp = (auxNode)objects[index];
                    //这是Dijkstra和prim算法的唯一区别，可以与上面的prim算法进行对比
                    int weight = getWeight(node.getAdjvex(),temp.getAdjvex())+node.getKey();
                    //如果temp的权重大于weight，则更新
                    if (temp.getKey() > weight){
                        temp.setKey(weight);
                        temp.setParent(node);
                        //重新调整优先队列
                        queue.siftUp(index,temp);
                    }

                }
            }
        }

        printfVisited(visitedList);
    }

    /**
     * 适用条件和范围：
     *      单源最短路径;有向图or无向图;边权可正可负(如有负权回路输出错误提示);
     *      差分约束系统;
     * 时间复杂度为O（VE）
     * @param start 起点
     * @param auxNodes 节点的辅助列表，更新的权值便是存放在这里
     * @return
     */
    public void Bellman_Ford(int start,List<auxNode> auxNodes){
        List<Edge> edges = new ArrayList<Edge>();   //用来存邻接表中的所有边，方便后面操作
        //初始化操作
        for (int i=1; i<=this.V; i++){
            if (i == start){
                auxNodes.add(new auxNode(i,0,null));
            }else {
                auxNodes.add(new auxNode(i,1000,null));
            }
        }
        //将边缓存起来
        for (int i=1; i<=this.V; i++){
            List<adjNode> adjNodes = getAdj().get(i);
            for (adjNode node : adjNodes){
                edges.add(new Edge(i,node.getAdjvex(),node.getWeight()));
            }
        }
        /**
         * 由于最短路径一定是一条不含有环的简单路径 1.如果有正环，一定不是最短路径 2.如果是0环，可以去掉改环多余部分也不影响
         * 3.如果是负环，则表示不存在最短路径
         *
         * 简单路径中，数量为V的元素个数形成的最短路径有为V-1个边，对 所有边 进行V-1次更新，会得到所有点的最短路径
         */
        for (int i=0; i<this.V-1; i++){
            for (Edge edge : edges){
                //auxNodes是从0开始存，顶点是从1开始的，所以这里要-1
                auxNode sourceAux = auxNodes.get(edge.getSource()-1);
                auxNode aimAux = auxNodes.get(edge.getAim()-1);
                //松弛操作
                if (aimAux.getKey() > sourceAux.getKey()+edge.getWeight()){
                    aimAux.setKey(sourceAux.getKey()+edge.getWeight());
                    aimAux.setParent(sourceAux);
                }
            }
        }
        boolean flag = true;    //用来标记是否有负环，默认没有
        /**
         * 在完成了v-1次全部边更新之后，理应无法继续更新，因为已经找到了s距离每个点的最短路径，如果还能继续更新，说明该图中含有负环。
         * 一旦该图含有负环，则表示可以无限次更新下去，也就是说该图根本不存在最短路径
         */
        for (Edge edge : edges){
            auxNode sourceAux = auxNodes.get(edge.getSource()-1);
            auxNode aimAux = auxNodes.get(edge.getAim()-1);
            //松弛操作
            if (aimAux.getKey() > sourceAux.getKey()+edge.getWeight()){
                flag = false;
                break;
            }
        }

        //打印链表
        if (flag){
            printfVisited(auxNodes);
        }else {
            System.out.println("have negative circle");
        }
    }

    public static void main(String[] args){
        Adjacency adjacency1 = new Adjacency(9);
        adjacency1.add_Undirect_weight_Edge(1, 2, 4);
        adjacency1.add_Undirect_weight_Edge(1, 8, 8);
        adjacency1.add_Undirect_weight_Edge(2, 3, 8);
        adjacency1.add_Undirect_weight_Edge(2, 8, 11);
        adjacency1.add_Undirect_weight_Edge(3, 4, 7);
        adjacency1.add_Undirect_weight_Edge(3, 6, 4);
        adjacency1.add_Undirect_weight_Edge(3, 9, 2);
        adjacency1.add_Undirect_weight_Edge(4, 5, 9);
        adjacency1.add_Undirect_weight_Edge(4, 6, 14);
        adjacency1.add_Undirect_weight_Edge(5, 6, 10);
        adjacency1.add_Undirect_weight_Edge(6, 7, 2);
        adjacency1.add_Undirect_weight_Edge(7, 8, 1);
        adjacency1.add_Undirect_weight_Edge(7, 9, 6);
        adjacency1.add_Undirect_weight_Edge(8, 9, 7);
        System.out.println("最小生成树为：");
        adjacency1.MST_PRIM(1);
        System.out.println("------------------------------");
        System.out.println("Dijkstra最短路径树为：");
        adjacency1.Dijkstra(1);
        System.out.println("------------------------------");
        System.out.println("广度优先遍历结果为：");
        adjacency1.BFSTraval(1, new ArrayList<Integer>());
        System.out.println("------------------------------");
        System.out.println("深度优先遍历结果为：");
        adjacency1.DFSTraval(1, new ArrayList<Integer>());
        System.out.println("------------------------------");
        System.out.println("Bellman_Ford最短路径树为：");
        Adjacency adjacency2 = new Adjacency(5);
        adjacency2.add_direct_weight_Edge(1, 2, 6);
        adjacency2.add_direct_weight_Edge(1, 5, 7);
        adjacency2.add_direct_weight_Edge(2, 3, 5);
        adjacency2.add_direct_weight_Edge(2, 4, -4);
        adjacency2.add_direct_weight_Edge(2, 5, 8);
        adjacency2.add_direct_weight_Edge(3, 2, -2);
        adjacency2.add_direct_weight_Edge(4, 1, 2);
        adjacency2.add_direct_weight_Edge(4, 3, 7);
        adjacency2.add_direct_weight_Edge(5, 4, 9);
        adjacency2.add_direct_weight_Edge(5, 3, -3);
        adjacency2.Bellman_Ford(1, new ArrayList<auxNode>());
        System.out.println("------------------------------");
        System.out.println("有向无环图的拓扑排序为：");
        Adjacency adjacency3 = new Adjacency(9);
        adjacency3.add_direct_unweight_Edge(1,4);
        adjacency3.add_direct_unweight_Edge(2,3);
        adjacency3.add_direct_unweight_Edge(2,4);
        adjacency3.add_direct_unweight_Edge(3,4);
        adjacency3.add_direct_unweight_Edge(3,7);
        adjacency3.add_direct_unweight_Edge(6,7);
        adjacency3.add_direct_unweight_Edge(6,8);
        adjacency3.add_direct_unweight_Edge(7,9);
        adjacency3.add_direct_unweight_Edge(8,9);
        adjacency3.Topological_sort();
    }
}

class adjNode {
    private int adjvex;
    private int weight;
    //有权图
    public adjNode(int adjvex, int weight) {
        this.adjvex = adjvex;
        this.weight = weight;
    }
    //无权图
    public adjNode(int adjvex) {
        this.adjvex = adjvex;
        this.weight = 1;
    }

    public int getAdjvex() {
        return adjvex;
    }

    public void setAdjvex(int adjvex) {
        this.adjvex = adjvex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
//弧
class Edge {
    private Integer source;
    private Integer aim;
    private Integer weight;

    public Edge(Integer source, Integer aim, Integer weight) {
        this.source = source;
        this.aim = aim;
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getSource() {
        return source;
    }

    public Integer getAim() {
        return aim;
    }

    public Integer getOtherSize(Integer vertex){
        if (vertex == source){
            return aim;
        }else if (vertex == aim){
            return source;
        }else {
            return null;
        }
    }
}

class auxNode {
    private Integer adjvex;
    private Integer key;
    private auxNode parent;

    public auxNode(Integer adjvex, Integer key, auxNode parent) {
        this.adjvex = adjvex;
        this.key = key;
        this.parent = parent;
    }

    public Integer getAdjvex() {
        return adjvex;
    }

    public void setAdjvex(Integer adjvex) {
        this.adjvex = adjvex;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public auxNode getParent() {
        return parent;
    }

    public void setParent(auxNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof auxNode){
            return adjvex == ((auxNode) obj).getAdjvex();
        }
        return false;
    }

}