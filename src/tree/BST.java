package tree;

/**
 * ���������(Binary Search Tree)�������������ʵĶ�������
 * ���������������ǿգ��������������н���ֵ��С�ڸ�����ֵ��
 * ���������������ǿգ��������������н���ֵ�����ڸ�����ֵ��
 * ���������������ָ���һ�ö����������
 * ͨ�׵Ľ���������������������ϵĽ�㲻�ȸ������������ϵĽ�㲻�ȸ����С��
 * ���˱���֮�⣬BST�����в���һ�㶼ΪO(lgn)��
 * ��BST�˻���һ�ž���n���ڵ��������������Щ���������ʱ��ΪO(n)
 * Created by zhaoshiqiang on 2016/12/13.
 */
public class BST {

    /**
     * BSTǰ�����
     * @param root Ҫ�������ĸ�
     */
    public void preOrder_recur(BSTnode root){
        if (root != null){
            System.out.print(root.getKey() + "  ");
            preOrder_recur(root.getLeft());
            preOrder_recur(root.getRight());
        }
    }
    /**
     * BST�������
     * @param root Ҫ�������ĸ�
     */
    public void inOrder_recur(BSTnode root){
        if (root != null){
            inOrder_recur(root.getLeft());
            System.out.print(root.getKey() + "  ");
            inOrder_recur(root.getRight());
        }
    }
    /**
     * BST�������
     * @param root Ҫ�������ĸ�
     */
    public void postOrder_recur(BSTnode root){
        if (root != null){
            postOrder_recur(root.getLeft());
            postOrder_recur(root.getRight());
            System.out.print(root.getKey() + "  ");
        }
    }

    /**
     * BST�ݹ�����
     * @param root ���Ŀ�������ֵ
     * @param key Ҫ�����ֵ
     * @return �Ѿ�����ֵ����
     */
    public BSTnode insert_recur(BSTnode root,int key, BSTnode parent){
        //����Ϊ�գ����½�һ��
        if (root == null)
            return new BSTnode(key,parent);
        if (root.getKey() > key){
            root.setLeft(insert_recur(root.getLeft(),key,root));
        }else {
            root.setRight(insert_recur(root.getRight(), key, root));
        }
        return root;
    }

    public BSTnode insert_recur(BSTnode root,int key){
        return insert_recur(root,key,null);
    }

    /**
     * BST�ǵݹ�����
     * @param root ���Ŀ�������ֵ
     * @param key Ҫ�����ֵ
     * @return �Ѿ�����ֵ����
     */
    public BSTnode insert_unrecur(BSTnode root,int key){

        if (root == null)
            return new BSTnode(key,null);
        BSTnode p = root;
        BSTnode parent = null;

        while (p != null){
            parent = p;
            if (p.getKey() > key){
                p = p.getLeft();
            }else {
                p = p.getRight();
            }
        }
        if (parent.getKey() > key){
            parent.setLeft(new BSTnode(key,parent));
        }else {
            parent.setRight(new BSTnode(key,parent));
        }
        return root;
    }

    /**
     * BST�ݹ���ѯ
     * @param root Ҫ��ѯ����
     * @param key Ҫ��ѯ��ֵ
     * @return ���ҵ��򷵻ض�Ӧ�ڵ㣬�Ҳ����򷵻�null
     */
    public BSTnode check_recur(BSTnode root,int key){
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
     * BST�ǵݹ���ѯ
     * @param root Ҫ��ѯ����
     * @param key Ҫ��ѯ��ֵ
     * @return ���ҵ��򷵻ض�Ӧ�ڵ㣬�Ҳ����򷵻�null
     */
    public BSTnode check_unrecur(BSTnode root,int key){
        BSTnode p = root;
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
     * BST�ݹ�淵�����ڵ�
     * @param root Ҫ���ҵ���
     * @return ���ֵ��Ӧ�Ľڵ�
     */
    public BSTnode max_node_recur(BSTnode root){

        if (root.getRight() == null)
            return root;
        else
            return max_node_recur(root.getRight());

    }

    /**
     * BST�ǵݹ�淵�����ڵ�
     * @param root Ҫ���ҵ���
     * @return ���ֵ��Ӧ�Ľڵ�
     */
    public BSTnode max_node_unrecur(BSTnode root){

        BSTnode p = root;
        BSTnode parent = null;

        while (p != null){
            parent = p;
            p = p.getRight();
        }
        return parent;
    }

    /**
     * BST�ݹ�淵����С�ڵ�
     * @param root Ҫ���ҵ���
     * @return ��Сֵ��Ӧ�Ľڵ�
     */
    public BSTnode min_node_recur(BSTnode root){

        if (root.getLeft() == null)
            return root;
        else
            return min_node_recur(root.getLeft());

    }

    /**
     * BST�ǵݹ�淵����С�ڵ�
     * @param root Ҫ���ҵ���
     * @return ��Сֵ��Ӧ�Ľڵ�
     */
    public BSTnode min_node_unrecur(BSTnode root){

        BSTnode p = root;
        BSTnode parent = null;

        while (p != null){
            parent = p;
            p = p.getLeft();
        }
        return parent;

    }

    /*
    * �����ǰ���ͺ�̵ĺ��塣
        �ڵ�key��ǰ���������������ʱ����keyС�����нڵ��������Ǹ��ڵ㡣
        �ڵ�key�ĺ�̣������������ʱ����keyС�����нڵ��������Ǹ��ڵ㡣
      ���׿������ڵ�key��ǰ��һ��û���Ҷ��ӣ���̽ڵ�һ��û�������
    * */

    /**
     * ��root�в���key��Ӧ��ǰ���ڵ㣬��������ֵС��key�����ڵ�
     * @param root Ҫ�������ĸ��ڵ�
     * @param key Ҫ����ǰ���ڵ��ֵ
     * @return keyֵ��Ӧ��ǰ���ڵ�
     */
    public BSTnode preNode(BSTnode root,int key){

        BSTnode p = check_recur(root,key);
        if (p == null){
            return null;
        }
        //���p�������ӣ���"p��ǰ�����"Ϊ "��������Ϊ���������������"��
        if (p.getLeft() != null){
            return max_node_recur(p.getLeft());
        }
        //���pû�����ӣ���p���������ֿ��ܣ�
        //(1) p��һ���Һ��ӣ���"p��ǰ�����"Ϊp�ĸ��ڵ�
        //(2) p��һ�����ӣ���"p��ǰ�����"Ϊp��ĳһ�����ȵĸ��ڵ㣬�Ҹ����Ƚڵ����丸�ڵ���Һ���
        BSTnode parent = p.getParent();
        while (parent !=null && parent.getLeft() == p){
            p = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * ��root�в���key��Ӧ�ĺ�̽ڵ㣬��������ֵ����key����С�ڵ�
     * @param root Ҫ�������ĸ��ڵ�
     * @param key Ҫ���Һ�̽ڵ��ֵ
     * @return keyֵ��Ӧ�ĺ�̽ڵ�
     */
    public BSTnode postNode(BSTnode root,int key){

        BSTnode p = check_recur(root,key);
        if (p == null){
            return null;
        }
        // ���p�����Һ��ӣ���"p�ĺ�̽��"Ϊ "�����Һ���Ϊ������������С���"��
        if (p.getRight() != null){
            return min_node_recur(p.getRight());
        }
        // ���pû���Һ��ӡ���p���������ֿ��ܣ�
        //(1) p��"һ������"����"p�ĺ�̽��"Ϊ "���ĸ����"��
        //(2) p��"һ���Һ���"���� ǰ���ڵ�Ϊp��ĳһ�����Ƚڵ�ĸ��ڵ㣬���Ҹ����Ƚڵ�����Ϊ�丸�ڵ�������
        BSTnode parent =p.getParent();
        while (parent != null && parent.getRight() == p){
            p = parent;
            parent = parent.getParent();
        }
        return parent;
    }
    /**
     * BSTɾ���ڵ�
     * @param root Ҫ��������
     * @param key Ҫɾ���ڵ��ֵ
     * @return ɾ���ڵ�����
     */
    /*
    * �����������ɾ����������������д���
        1.pΪҶ�ӽڵ㣬ֱ��ɾ���ýڵ㣬���޸��丸�ڵ��ָ�루����Ҫע�������Ǹ��ڵ�Ͳ��Ǹ��ڵ㣩
        2.pΪ��֧�ڵ㣨��ֻ����������������������p��������p�ĸ��׽ڵ�������ɾ��p���ɣ���ע����Ǹ��ڵ�Ͳ��Ǹ��ڵ㣩��
        3.���������ӵ��������ǰ�����������������Ԫ�ؽ�����Ȼ��ɾ����ǰ��㡣����������Ԫ��һ����Ҷ�ӽ�㣬������
            ��ǰ��㼴ΪҶ�ӽ�㣬ɾ���ο�û�к��ӵ��������һ�ַ����ǣ���ǰ���������������С��Ԫ�ؽ�����Ȼ��ɾ����ǰ��㡣
    * */
    public BSTnode remove(BSTnode root, int key){
        if (root == null)
            return null;
        BSTnode p = check_recur(root, key);
        //��ɾ���ڵ���Ҷ�ӽڵ�
        if (p.getRight() == null && p.getLeft() == null){
            BSTnode parent = p.getParent();
            if (parent == null){
                //���ڵ�
                p=null;
            }else {
                //Ҷ�ӽڵ�
                //�ȴ����丸�ڵ�
                if (parent.getLeft() == p){
                    parent.setLeft(null);
                }else {
                    parent.setRight(null);
                }
                //�ٴ���Ҷ�ӽڵ�
                p =null;
            }
        }else if (p.getLeft() != null && p.getRight() == null){
            //��ɾ���ڵ�ֻ������
            BSTnode parent = p.getParent();
            if (parent == null){
                root = p.getLeft();
                p=null;
            }else {
                //Ҷ�ӽڵ�
                //�ȴ����丸�ڵ�
                if (parent.getLeft() == p){
                    parent.setLeft(p.getLeft());
                }else {
                    parent.setRight(p.getLeft());
                }
                //�ٴ���Ҷ�ӽڵ�
                p =null;
            }
        }else if (p.getRight() != null && p.getLeft() == null){
            //��ɾ���ڵ�ֻ���Һ���
            BSTnode parent = p.getParent();
            if (parent == null){
                root = p.getRight();
                p=null;
            }else {
                //Ҷ�ӽڵ�
                //�ȴ����丸�ڵ�
                if (parent.getLeft() == p){
                    parent.setLeft(p.getRight());
                }
                //�ٴ���Ҷ�ӽڵ�
                p =null;
            }
        }else {
            //��ɾ���Ľڵ�������������Һ���
            //�ҵ���ɾ���ڵ�ĺ�̽ڵ㣬�˺�̽ڵ�һ��û������
            BSTnode postnode = postNode(p,key);
            //�ú�̽ڵ�ֱֵ�Ӹ���Ҳ���ԣ�ֻ������������Ƚ���ȷ
//            p.setKey(postnode.getKey());
            //������̽ڵ�ʹ�ɾ���ڵ�ֵ
            int value = p.getKey();p.setKey(postnode.getKey());postnode.setKey(value);
            //ɾ����̽ڵ�λ�õ�ֵ������ɾ���ڵ��ֵ
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
        BSTnode root = bst.insert_recur(null,26);
        //����
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
        //��ѯ
        BSTnode check = bst.check_recur(root,13);
        System.out.println(check.getKey());
        check = bst.check_unrecur(root,51);
        System.out.println(check.getKey());
        System.out.println("\n"+"------------------------------");
        //��������Сֵ
        System.out.println(bst.max_node_recur(root).getKey());
        System.out.println(bst.max_node_unrecur(root).getKey());
        System.out.println(bst.min_node_recur(root).getKey());
        System.out.println(bst.min_node_unrecur(root).getKey());
        System.out.println("\n"+"------------------------------");
        //���ǰ������׺
        System.out.println(bst.preNode(root, 13).getKey());
        System.out.println(bst.postNode(root, 13).getKey());
        System.out.println("\n" + "------------------------------");
        //ɾ��
        bst.inOrder_recur(root);
        System.out.println("\n");

        root = bst.remove(root, 29);
        bst.inOrder_recur(root);
        System.out.println("\n");

        root = bst.remove(root, 42);
        bst.inOrder_recur(root);
        System.out.println("\n");

        root = bst.remove(root, 9);
        bst.inOrder_recur(root);
        System.out.println("\n");
    }

}

/**
 * BST�ڵ�
 */
class BSTnode {
    private int key;
    private BSTnode left;
    private BSTnode right;
    private BSTnode parent;

    public BSTnode(int key, BSTnode parent) {
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

    public BSTnode getLeft() {
        return left;
    }

    public void setLeft(BSTnode left) {
        this.left = left;
    }

    public BSTnode getRight() {
        return right;
    }

    public void setRight(BSTnode right) {
        this.right = right;
    }

    public BSTnode getParent() {
        return parent;
    }

    public void setParent(BSTnode parent) {
        this.parent = parent;
    }
}
