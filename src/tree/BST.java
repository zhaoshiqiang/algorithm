package tree;

/**
 * 二叉查找树(Binary Search Tree)是满足如下性质的二叉树：
 * ①若它的左子树非空，则左子树上所有结点的值均小于根结点的值；
 * ②若它的右子树非空，则右子树上所有结点的值均大于根结点的值；
 * ③左、右子树本身又各是一棵二叉查找树。
 * 通俗的讲，二叉查找树的左子树上的结点不比父结点大，右子树上的结点不比父结点小
 * Created by zhaoshiqiang on 2016/12/13.
 */
public class BST {

    /**
     * BST前序遍历
     * @param root 要遍历树的根
     */
    public void preOrder_recur(node root){
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
    public void inOrder_recur(node root){
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
    public void postOrder_recur(node root){
        if (root != null){
            postOrder_recur(root.getLeft());
            postOrder_recur(root.getRight());
            System.out.print(root.getKey() + "  ");
        }
    }

    /**
     * BST递归版插入
     * @param root 向哪颗树插入值
     * @param key 要插入的值
     * @return 已经插入值的树
     */
    public node insert_recur(node root,int key,node parent){
        //该树为空，则新建一个
        if (root == null)
            return new node(key,parent);
        if (root.getKey() > key){
            root.setLeft(insert_recur(root.getLeft(),key,root));
        }else {
            root.setRight(insert_recur(root.getRight(), key, root));
        }
        return root;
    }

    public node insert_recur(node root,int key){
        return insert_recur(root,key,null);
    }

    /**
     * BST非递归版插入
     * @param root 向哪颗树插入值
     * @param key 要插入的值
     * @return 已经插入值得树
     */
    public node insert_unrecur(node root,int key){

        if (root == null)
            return new node(key,null);
        node p = root;
        node parent = null;

        while (p != null){
            parent = p;
            if (p.getKey() > key){
                p = p.getLeft();
            }else {
                p = p.getRight();
            }
        }
        if (parent.getKey() > key){
            parent.setLeft(new node(key,parent));

        }else {
            parent.setRight(new node(key,parent));
        }
        return root;
    }

    /**
     * BST递归版查询
     * @param root 要查询的树
     * @param key 要查询的值
     * @return 若找到则返回对应节点，找不到则返回null
     */
    public node check_recur(node root,int key){
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
    public node check_unrecur(node root,int key){
        node p = root;
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
    public node max_node_recur(node root){

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
    public node max_node_unrecur(node root){

        node p = root;
        node parent = null;

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
    public node min_node_recur(node root){

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
    public node min_node_unrecur(node root){

        node p = root;
        node parent = null;

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
    public node preNode(node root,int key){

        node p = check_recur(root,key);
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
        node parent = p.getParent();
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
    public node postNode(node root,int key){

        node p = check_recur(root,key);
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
        node parent =p.getParent();
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
     * @return 删除节点后的树
     */
    /*
    * 二叉查找树的删除，分三种情况进行处理：
        1.p为叶子节点，直接删除该节点，再修改其父节点的指针（这里要注意区别是根节点和不是根节点）
        2.p为单支节点（即只有左子树或右子树）。让p的子树与p的父亲节点相连，删除p即可；（注意分是根节点和不是根节点）；
        3.有两个孩子的情况，当前结点与左子树中最大的元素交换，然后删除当前结点。左子树最大的元素一定是叶子结点，交换后，
            当前结点即为叶子结点，删除参考没有孩子的情况。另一种方法是，当前结点与右子树中最小的元素交换，然后删除当前结点。
    * */
    public node erase(node root,int key){
        if (root == null)
            return null;
        node p = check_recur(root, key);
        //待删除节点是叶子节点
        if (p.getRight() == null && p.getLeft() == null){
            node parent = p.getParent();
            if (parent == null){
                //根节点
                p=null;
            }else {
                //叶子节点
                //先处理其父节点
                if (parent.getLeft() == p){
                    parent.setLeft(null);
                }else {
                    parent.setRight(null);
                }
                //再处理叶子节点
                p =null;
            }
        }else if (p.getLeft() != null && p.getRight() == null){
            //待删除节点只有左孩子
            node parent = p.getParent();
            if (parent == null){
                root = p.getLeft();
                p=null;
            }else {
                //叶子节点
                //先处理其父节点
                if (parent.getLeft() == p){
                    parent.setLeft(p.getLeft());
                }else {
                    parent.setRight(p.getLeft());
                }
                //再处理叶子节点
                p =null;
            }
        }else if (p.getRight() != null && p.getLeft() == null){
            //待删除节点只有右孩子
            node parent = p.getParent();
            if (parent == null){
                root = p.getRight();
                p=null;
            }else {
                //叶子节点
                //先处理其父节点
                if (parent.getLeft() == p){
                    parent.setLeft(p.getRight());
                }else {
                    parent.setRight(p.getRight());
                }
                //再处理叶子节点
                p =null;
            }
        }else {
            //待删除的节点既有左孩子又有右孩子
            //找到带删除节点的后继节点，此后继节点一定没有左孩子
            node postnode = postNode(p,key);
            //用后继节点值直接覆盖也可以，只不过交换意义比较明确
//            p.setKey(postnode.getKey());
            //交换后继节点和待删除节点值
            int value = p.getKey();p.setKey(postnode.getKey());postnode.setKey(value);
            //删除后继节点位置的值，即待删除节点的值
            if (postnode.getParent().getLeft() == postnode){
                postnode.getParent().setLeft(postnode.getRight());
                postnode=null;
            }else {
                postnode.getParent().setRight(postnode.getRight());
                postnode=null;
            }
        }

        return root;
    }

    public static void main(String[] args){
        BST bst = new BST();
        node root = bst.insert_recur(null,26);
        //插入
        root = bst.insert_unrecur(root,33);
        root = bst.insert_unrecur(root,68);
        root = bst.insert_unrecur(root,42);
        root = bst.insert_unrecur(root,51);
        root = bst.insert_recur(root,5);
        root = bst.insert_recur(root,9);
        root = bst.insert_recur(root,19);
        root = bst.insert_recur(root,29);
        root = bst.insert_recur(root,13);
        bst.inOrder_recur(root);
        System.out.println("\n"+"------------------------------");
        //查询
        node check = bst.check_recur(root,13);
        System.out.println(check.getKey());
        check = bst.check_unrecur(root,51);
        System.out.println(check.getKey());
        System.out.println("\n"+"------------------------------");
        //输出最大最小值
        System.out.println(bst.max_node_recur(root).getKey());
        System.out.println(bst.max_node_unrecur(root).getKey());
        System.out.println(bst.min_node_recur(root).getKey());
        System.out.println(bst.min_node_unrecur(root).getKey());
        System.out.println("\n"+"------------------------------");
        //输出前驱，后缀
        System.out.println(bst.preNode(root, 13).getKey());
        System.out.println(bst.postNode(root, 13).getKey());
        System.out.println("\n" + "------------------------------");
        //删除
        bst.inOrder_recur(root);
        System.out.println("\n");

        root = bst.erase(root,29);
        bst.inOrder_recur(root);
        System.out.println("\n");

        root = bst.erase(root,42);
        bst.inOrder_recur(root);
        System.out.println("\n");

        root = bst.erase(root,9);
        bst.inOrder_recur(root);
        System.out.println("\n");
    }

}

/**
 * BST节点
 */
class node {
    private int key;
    private node left;
    private node right;
    private node parent;

    public node(int key,node parent) {
        this.key = key;
        left=null;
        right=null;
        this.parent=parent;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public node getLeft() {
        return left;
    }

    public void setLeft(node left) {
        this.left = left;
    }

    public node getRight() {
        return right;
    }

    public void setRight(node right) {
        this.right = right;
    }

    public node getParent() {
        return parent;
    }

    public void setParent(node parent) {
        this.parent = parent;
    }
}
