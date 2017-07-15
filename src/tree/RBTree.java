package tree;

/**红黑树，一种二叉查找树，但在每个结点上增加一个存储位表示结点的颜色，可以是Red或Black。
 * 红黑树有5条限制：
 * 1. 每个结点要么是红的要么是黑的。
 * 2. 根结点是黑的。
 * 3. 每个叶结点都是黑的。 [注意：这里叶子节点，是指为空的叶子节点！]
 * 4. 如果一个结点是红的，那么它的两个儿子都是黑的。（or 每个红节点都有一个黑色的父节点)
 * 5. 对于任意结点而言，其到叶结点的每条路径都包含相同数目的黑结点。
 * 通过这5条限制，红黑树确保没有一条路径会比其他路径长出俩倍，因而是接近平衡的。
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class RBTree {

    /**
     * BST前序遍历
     * @param root 要遍历树的根
     */
    public void preOrder_recur(RBnode root){
        if (root != null){
            System.out.print(root.getKey() + "  ");
            preOrder_recur(root.getLeft());
            preOrder_recur(root.getRight());
        }
    }
    /**
     * BST中序遍历
     * @param root 要遍历树的根
     */
    public void inOrder_recur(RBnode root){
        if (root != null){
            inOrder_recur(root.getLeft());
            System.out.print(root.getKey() + "  ");
            inOrder_recur(root.getRight());
        }
    }
    /**
     * BST后序遍历
     * @param root 要遍历树的根
     */
    public void postOrder_recur(RBnode root){
        if (root != null){
            postOrder_recur(root.getLeft());
            postOrder_recur(root.getRight());
            System.out.print(root.getKey() + "  ");
        }
    }

    /**
     * BST非递归版插入
     * @param root 向哪颗树插入值
     * @param node 要插入的值
     * @return 已经插入值得树
     */
    public RBnode insert_unrecur(RBnode root,RBnode node){

        if (root == null)
            return node;
        RBnode p = root;
        RBnode parent = null;

        while (p != null){
            parent = p;
            if (p.getKey() > node.getKey()){
                p = p.getLeft();
            }else {
                p = p.getRight();
            }
        }
        node.setParent(parent);
        if (parent.getKey() > node.getKey()){
            parent.setLeft(node);
        }else {
            parent.setRight(node);
        }
        return root;
    }

    /**
     * BST递归版查询
     * @param root 要查询的树
     * @param key 要查询的值
     * @return 若找到则返回对应节点，找不到则返回null
     */
    public RBnode check_recur(RBnode root,int key){
        if (root == null)
            return null;
        if (root.getKey() > key){
            return check_recur(root.getLeft(),key);
        }else if (root.getKey() < key){
            return check_recur(root.getRight(),key);
        }else {
            return root;
        }
    }

    /**
     * BST非递归版查询
     * @param root 要查询的树
     * @param key 要查询的值
     * @return 若找到则返回对应节点，找不到则返回null
     */
    public RBnode check_unrecur(RBnode root,int key){
        RBnode p = root;
        if (p == null)
            return null;

        while (p != null){
            if (p.getKey() > key){
                p = p.getLeft();
            }else if (p.getKey() < key){
                p = p.getRight();
            }else {
                return p;
            }
        }

        return null;
    }

    /**
     * BST递归版返回最大节点
     * @param root 要查找的树
     * @return 最大值对应的节点
     */
    public RBnode max_node_recur(RBnode root){

        if (root.getRight() == null)
            return root;
        else
            return max_node_recur(root.getRight());

    }

    /**
     * BST非递归版返回最大节点
     * @param root 要查找的树
     * @return 最大值对应的节点
     */
    public RBnode max_node_unrecur(RBnode root){

        RBnode p = root;
        RBnode parent = null;

        while (p != null){
            parent = p;
            p = p.getRight();
        }
        return parent;
    }

    /**
     * BST递归版返回最小节点
     * @param root 要查找的树
     * @return 最小值对应的节点
     */
    public RBnode min_node_recur(RBnode root){

        if (root.getLeft() == null)
            return root;
        else
            return min_node_recur(root.getLeft());

    }

    /**
     * BST非递归版返回最小节点
     * @param root 要查找的树
     * @return 最小值对应的节点
     */
    public RBnode min_node_unrecur(RBnode root){

        RBnode p = root;
        RBnode parent = null;

        while (p != null){
            parent = p;
            p = p.getLeft();
        }
        return parent;

    }

    /*
    * 先理解前驱和后继的含义。
        节点key的前驱，就是中序遍历时，比key小的所有节点中最大的那个节点。
        节点key的后继，就是中序遍历时，比key小的所有节点中最大的那个节点。
      容易看出，节点key的前驱一定没有右儿子；后继节点一定没有左儿子
    * */

    /**
     * 在root中查找key对应的前驱节点，即查找数值小于key的最大节点
     * @param root 要查找树的根节点
     * @param key 要查找前驱节点的值
     * @return key值对应的前驱节点
     */
    public RBnode preNode(RBnode root,int key){

        RBnode p = check_recur(root,key);
        if (p == null){
            return null;
        }
        //如果p存在左孩子，则"p的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (p.getLeft() != null){
            return max_node_recur(p.getLeft());
        }
        //如果p没有左孩子，则p有以下两种可能：
        //(1) p是一个右孩子，则"p的前驱结点"为p的父节点
        //(2) p是一个左孩子，则"p的前驱结点"为p的某一个祖先的父节点，且该祖先节点是其父节点的右孩子
        RBnode parent = p.getParent();
        while (parent !=null && parent.getLeft() == p){
            p = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * 在root中查找key对应的后继节点，即查找数值大于key的最小节点
     * @param root 要查找树的根节点
     * @param key 要查找后继节点的值
     * @return key值对应的后继节点
     */
    public RBnode postNode(RBnode root,int key){

        RBnode p = check_recur(root,key);
        if (p == null){
            return null;
        }
        // 如果p存在右孩子，则"p的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (p.getRight() != null){
            return min_node_recur(p.getRight());
        }
        // 如果p没有右孩子。则p有以下两种可能：
        //(1) p是"一个左孩子"，则"p的后继结点"为 "它的父结点"。
        //(2) p是"一个右孩子"，则 前驱节点为p的某一个祖先节点的父节点，而且该祖先节点是作为其父节点的左儿子
        RBnode parent =p.getParent();
        while (parent != null && parent.getRight() == p){
            p = parent;
            parent = parent.getParent();
        }
        return parent;
    }
    /**
     * BST删除节点
     * @param root 要操作的树
     * @param key 要删除节点的值
     * @return 取代删除节点位置的节点
     */
    /*
    * 二叉查找树的删除，分三种情况进行处理：
        1.p为叶子节点，直接删除该节点，再修改其父节点的指针（这里要注意区别是根节点和不是根节点）
        2.p为单支节点（即只有左子树或右子树）。让p的子树与p的父亲节点相连，删除p即可；（注意分是根节点和不是根节点）；
        3.有两个孩子的情况，当前结点与左子树中最大的元素交换，然后删除当前结点。左子树最大的元素一定是叶子结点，交换后，
            当前结点即为叶子结点，删除参考没有孩子的情况。另一种方法是，当前结点与右子树中最小的元素交换，然后删除当前结点。
    * */
    public RBnode remove(RBnode root, int key){
        if (root == null)
            return null;
        RBnode replace = null;


        return replace;
    }
    /**
     *
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *
     *      /                            /
     *     x                            y
     *   /  \     -(左旋)->.             / \
     *  lx   y                        x  ry
     *     /  \                     /  \
     *    ly  ry                   lx  ly
     *
     */
    public RBnode LeftRotate(RBnode x){
        RBnode root = null; //要返回的根节点
        RBnode y = x.getRight(); //设置x的右孩子为y
        root = y;   //将y设置为要返回的节点
        //如下，一共要操作三条枝
        //1 将y的左孩子设置为x的右孩子，如果y的左孩子非空，则设置x为y左孩子的父亲
        x.setRight(y.getLeft());
        if (y.getLeft() != null){
            y.getLeft().setParent(x);
        }
        //2 将y的父节点设置为x的父节点，如果x的父节点非空，则设置对应孩子为y
        y.setParent(x.getParent());
        if (x.getParent() != null){
            if (x.getParent().getLeft() == x){
                x.getParent().setLeft(y);
            }else {
                x.getParent().setRight(y);
            }
        }
        //3 将y设置为x的父节点，x设置为y的左孩子
        x.setParent(y);
        y.setLeft(x);
        return root;
    }
    /**
     *
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行右旋)：
     *
     *         /                           /
     *        y                           x
     *       /  \     -(右旋)->.          /  \
     *      x   ry                     lx   y
     *     / \                             / \
     *    lx  rx                          rx  ry
     */
    public RBnode RightRotate(RBnode y){
        RBnode root = null; //要返回的根节点
        RBnode x = y.getLeft(); //设置y的右孩子为x
        root = x;   //将y设置为要返回的节点
        //如下，一共要操作三条枝
        //1 将x的右孩子设置为y的左孩子，如果x的右孩子非空，则设置y为x的右孩子的父亲
        y.setLeft(x.getRight());
        if (x.getRight() != null){
            x.getRight().setParent(y);
        }
        //2 将y的父节点设置为x的父节点，如果y的父节点非空，则设置对应孩子为x
        x.setParent(y.getParent());
        if (y.getParent() != null){
            if (y.getParent().getLeft() == y){
                y.getParent().setLeft(x);
            }else {
                y.getParent().setRight(x);
            }
        }
        //3 将x设置为y的父节点，y设置为x的右孩子
        y.setParent(x);
        x.setRight(y);
        return root;
    }

    /**
     * 红黑树插入修正函数
     * @param node 插入的节点
     */
    private void insertFixUp(RBnode node){
        RBnode parent ;
        RBnode gparent;
        //若插入节点的父节点为红色，则不满足性质4
        while ( node.getParent() != null && node.getParent().getColor() == RBnode.RED){
            parent = node.getParent();
            gparent = parent.getParent();   //父节点为红，则必存在一个黑色的祖先节点
            //若父节点是祖先节点的左孩子
            if (parent == gparent.getLeft()){
                //情况1：node的叔节点是红色的
                RBnode uncle = gparent.getRight();
                if (uncle != null && uncle.getColor() == RBnode.RED){
                    //将父节点和叔节点染成黑色，这样父辈的节点满足性质4，
                    parent.setColor(RBnode.BLCAK);
                    uncle.setColor(RBnode.BLCAK);
                    //但现在有可能破坏性质5，故将祖先节点染成红色，变成新的node，继续迭代
                    gparent.setColor(RBnode.RED);
                    node = gparent;
                    continue;
                }
                //情况2：叔节点是黑色且当前节点是右孩子
                if (parent.getRight() == node){
                    LeftRotate(parent); //通过左转转换为情况3
                    RBnode temp = parent;
                    parent = node;
                    node = temp;
                }
                //情况3：叔节点是黑色的且当前节点是左孩子
                parent.setColor(RBnode.BLCAK);
                gparent.setColor(RBnode.RED);
                RightRotate(gparent);
            }else { //若父节点是祖先节点的右孩子
                RBnode uncle = gparent.getLeft();
                //情况1：叔节点是红色
                if (uncle!= null && uncle.getColor() == RBnode.RED){
                    uncle.setColor(RBnode.BLCAK);
                    parent.setColor(RBnode.BLCAK);
                    gparent.setColor(RBnode.RED);
                    node = gparent;
                    continue;
                }

                //情况2：叔节点是黑色且当前节点是左孩子
                if (parent.getLeft() == node){
                    RightRotate(parent);    //通过右转转换为情况3
                    RBnode temp = parent;
                    parent = node;
                    node = temp;
                }
                //情况3：叔节点是黑色的且当前节点是右孩子
                parent.setColor(RBnode.BLCAK);
                gparent.setColor(RBnode.RED);
                LeftRotate(gparent);
            }
        }
    }
    /**
     * 红黑树的插入操作包括三个步骤：
     * 1. 自顶向下，将新节点插入：红黑树本身就是一颗二叉查找树，将节点插入后，该树仍然是一颗二叉查找树。
     *              也就意味着，树的键值仍然是有序的。此外，无论是左旋还是右旋，若旋转之前这棵树是二叉查找树，
     *              旋转之后它一定还是二叉查找树。这也就意味着，任何的旋转和重新着色操作，
     *              都不会改变它仍然是一颗二叉查找树的事实。
     * 2. 将插入点着色为红色：将插入的节点着色为红色，不会违背"特性(5)"！少违背一条特性，就意味着我们需要处理的情况越少。
     *                       接下来，就要努力的让这棵树满足其它性质即可；满足了的话，它就又是一颗红黑树了。
     * 3. 自底向上，做旋转和着色操作保持红黑树的性质：只有"特性(4)"有可能违背，我们的操作主要就是使数满足它
     * 插入操作的旋转操作最多执行2次
     * @param root 要操作的红黑树
     * @param node 要插入的值
     * @return 插入后的红黑树
     */
    public RBnode insert(RBnode root, RBnode node){
        //1.自顶而下，插入新节点，将红黑树当做一颗二叉树
        root = insert_unrecur(root,node);
        //2.设置节点颜色为红色
        node.setColor(RBnode.RED);
        //3.将其重新修正为一颗红黑树
        insertFixUp(node);
        root.setColor(RBnode.BLCAK);    //设置根节点为黑色
        return root;
    }



    /**
     * 红黑树的删除操作包括2个步骤：
     * 1. 自顶向下，将新节点删除：红黑树本身就是一颗二叉查找树，将节点插入后，该树仍然是一颗二叉查找树。
     *              也就意味着，树的键值仍然是有序的。此外，无论是左旋还是右旋，若旋转之前这棵树是二叉查找树，
     *              旋转之后它一定还是二叉查找树。这也就意味着，任何的旋转和重新着色操作，
     *              都不会改变它仍然是一颗二叉查找树的事实。
     * 2. 自底向上，做旋转和着色操作保持红黑树的性质：只有"特性(4)"有可能违背，我们的操作主要就是使数满足它
     * @param root 要操作的红黑树
     * @param node 要删除的节点
     * @return 删除后的红黑树
     */
    public RBnode remove(RBnode root,RBnode node){

        RBnode p = check_recur(root, node.getKey());
        //待删除节点是叶子节点
        if (p.getRight() == null && p.getLeft() == null){
            RBnode parent = p.getParent();
            if (parent == null){
                //根节点
                p=null;
                root = null;
            }else {
                //叶子节点
                //先处理其父节点
                if (parent.getLeft() == p){
                    parent.setLeft(null);
                }else {
                    parent.setRight(null);
                }
                //再处理带删除节点
                if (p.getColor() == RBnode.BLCAK)
                    removeFixUp(null, parent,root);
                p=null;
            }
        }else if (p.getLeft() != null && p.getRight() == null){
            //待删除节点只有左孩子
            RBnode parent = p.getParent();
            if (parent == null){
                root = p.getLeft();
                //直接将根节点染成黑色
                root.setColor(RBnode.BLCAK);
                p=null;
            }else {
                //叶子节点
                //先处理其父节点
                if (parent.getLeft() == p){
                    parent.setLeft(p.getLeft());
                }else {
                    parent.setRight(p.getLeft());
                }
                //再处理带删除节点
                if (p.getColor() == RBnode.BLCAK)
                    removeFixUp(p.getLeft(), parent,root);
                p=null;
            }
        }else if (p.getRight() != null && p.getLeft() == null){
            //待删除节点只有右孩子
            RBnode parent = p.getParent();
            if (parent == null){
                root = p.getRight();
                root.setColor(RBnode.BLCAK);
                p=null;
            }else {
                //叶子节点
                //先处理其父节点
                if (parent.getLeft() == p){
                    parent.setLeft(p.getRight());
                }else {
                    parent.setRight(p.getRight());
                }
                //再处理带删除节点
                if (p.getColor() == RBnode.BLCAK)
                    removeFixUp(p.getRight(), parent,root);
                p = null;
            }
        }else {
            //待删除的节点既有左孩子又有右孩子
            //找到带删除节点的后继节点，此后继节点一定没有左孩子
            RBnode postnode = postNode(p,node.getKey());
            //用后继节点值直接覆盖也可以，只不过交换意义比较明确
//            p.setKey(postnode.getKey());
            //交换后继节点和待删除节点值
            swap(p,postnode);
            //删除后继节点位置的值，即待删除节点的值
            if (p.getParent().getLeft() == p){
                p.getParent().setLeft(p.getRight());
            }
            //再处理带删除节点
            if (p.getColor() == RBnode.BLCAK)
                removeFixUp(p.getRight(), p.getParent(),root);
            p=null;
        }
        return root;
    }

    /**
     * 红黑树删除修正函数
     * @param node 删除位置的新节点
     * @param parent node的父节点
     * @param root 红黑树的根
     */
    private void removeFixUp(RBnode node,RBnode parent,RBnode root){
        RBnode other;
        while ((node == null || node.getColor() == RBnode.BLCAK) && (node.getParent() != null)){
            if (parent.getLeft() == node){
                other = parent.getRight();
                //情况1;node的兄弟节点other是红色，通过着色和一次旋转可转换为情况2,3,4
                if (other.getColor() == RBnode.RED){
                    other.setColor(RBnode.BLCAK);
                    parent.setColor(RBnode.RED);
                    LeftRotate(parent);
                    other = parent.getRight();
                }
                //情况2：node的兄弟节点other是黑色的，而且other的两个孩子也都是黑色
                //如果通过情况1进入情况2，则while循环结束，因为新的节点node是红色的
                if ((other.getLeft() == null || other.getLeft().getColor() == RBnode.BLCAK) &&
                        (other.getRight() == null || other.getRight().getColor() == RBnode.BLCAK)){
                    other.setColor(RBnode.RED);
                    node = parent;
                    parent = node.getParent();
                }else {
                    //情况3：node的兄弟节点other是黑色的，且other的左孩子是红色，右孩子是黑色
                    //通过两次着色和一次旋转可将情况3转换为情况4
                    if (other.getRight() == null || other.getRight().getColor() == RBnode.BLCAK){
                        other.getLeft().setColor(RBnode.BLCAK);
                        other.setColor(RBnode.RED);
                        other = RightRotate(other);
                    }
                    //情况4：node的兄弟节点ohter是黑色的，且other的右孩子是红色的，左孩子是任意颜色
                    //通过进行着色和一次旋转可去掉x的额外黑色，将x设置为根节点后，终止循环
                    other.setColor(parent.getColor());
                    parent.setColor(RBnode.BLCAK);
                    other.getRight().setColor(RBnode.BLCAK);
                    LeftRotate(other);
                    node = root;
                    break;
                }
            }else {
                //与上面情况一样，只是左右要交换
                other = parent.getLeft();
                if (other.getColor() == RBnode.RED){
                    other.setColor(RBnode.BLCAK);
                    parent.setColor(RBnode.RED);
                    RightRotate(parent);
                    other = parent.getLeft();
                }
                if ((other.getLeft() == null || other.getLeft().getColor() == RBnode.BLCAK) &&
                        (other.getRight() == null || other.getRight().getColor() == RBnode.BLCAK)){
                    other.setColor(RBnode.RED);
                    node = parent;
                    parent = node.getParent();
                }else {
                    if (other.getLeft() == null || other.getLeft().getColor() == RBnode.BLCAK){
                        other.getRight().setColor(RBnode.BLCAK);
                        other.setColor(RBnode.RED);
                        other = LeftRotate(other);
                    }
                    other.setColor(parent.getColor());
                    parent.setColor(RBnode.BLCAK);
                    other.getLeft().setColor(RBnode.BLCAK);
                    RightRotate(other);
                    node = root;
                    break;
                }
            }
        }
        if (node != null){
            node.setColor(RBnode.BLCAK);
        }
    }
    private void swap (RBnode x,RBnode y){
        int key = x.getKey();x.setKey(y.getKey());y.setKey(key);
        int color = x.getColor();x.setColor(y.getColor());y.setColor(color);
        RBnode ptemp = x.getParent();x.setParent(y.getParent());y.setParent(ptemp);
        RBnode ltemp = x.getLeft();x.setLeft(y.getLeft());y.setLeft(ltemp);
        RBnode rtemp = x.getRight();x.setRight(y.getRight());y.setRight(rtemp);
    }

    class RBnode{
        public static final int RED = 0;
        public static final int BLCAK = 1;

        private int color;
        private int key;
        private RBnode parent;
        private RBnode left;
        private RBnode right;

        public RBnode(int color, int key, RBnode parent) {
            this.color = color;
            this.key = key;
            this.parent = parent;
            this.right = null;
            this.left = null;
        }

        public RBnode(int color, int key) {
            this.color = color;
            this.key = key;
            this.parent = null;
            this.right = null;
            this.left = null;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public RBnode getParent() {
            return parent;
        }

        public void setParent(RBnode parent) {
            this.parent = parent;
        }

        public RBnode getLeft() {
            return left;
        }

        public void setLeft(RBnode left) {
            this.left = left;
        }

        public RBnode getRight() {
            return right;
        }

        public void setRight(RBnode right) {
            this.right = right;
        }

    }

}