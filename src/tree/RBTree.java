package tree;

/**�������һ�ֶ��������������ÿ�����������һ���洢λ��ʾ������ɫ��������Red��Black��
 * �������5�����ƣ�
 * 1. ÿ�����Ҫô�Ǻ��Ҫô�Ǻڵġ�
 * 2. ������Ǻڵġ�
 * 3. ÿ��Ҷ��㶼�Ǻڵġ� [ע�⣺����Ҷ�ӽڵ㣬��ָΪ�յ�Ҷ�ӽڵ㣡]
 * 4. ���һ������Ǻ�ģ���ô�����������Ӷ��Ǻڵġ���or ÿ����ڵ㶼��һ����ɫ�ĸ��ڵ�)
 * 5. ������������ԣ��䵽Ҷ����ÿ��·����������ͬ��Ŀ�ĺڽ�㡣
 * ͨ����5�����ƣ������ȷ��û��һ��·���������·����������������ǽӽ�ƽ��ġ�
 * Created by zhaoshiqiang on 2016/12/15.
 */
public class RBTree {

    /**
     * BSTǰ�����
     * @param root Ҫ�������ĸ�
     */
    public void preOrder_recur(RBnode root){
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
    public void inOrder_recur(RBnode root){
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
    public void postOrder_recur(RBnode root){
        if (root != null){
            postOrder_recur(root.getLeft());
            postOrder_recur(root.getRight());
            System.out.print(root.getKey() + "  ");
        }
    }

    /**
     * BST�ǵݹ�����
     * @param root ���Ŀ�������ֵ
     * @param node Ҫ�����ֵ
     * @return �Ѿ�����ֵ����
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
     * BST�ݹ���ѯ
     * @param root Ҫ��ѯ����
     * @param key Ҫ��ѯ��ֵ
     * @return ���ҵ��򷵻ض�Ӧ�ڵ㣬�Ҳ����򷵻�null
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
     * BST�ǵݹ���ѯ
     * @param root Ҫ��ѯ����
     * @param key Ҫ��ѯ��ֵ
     * @return ���ҵ��򷵻ض�Ӧ�ڵ㣬�Ҳ����򷵻�null
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
     * BST�ݹ�淵�����ڵ�
     * @param root Ҫ���ҵ���
     * @return ���ֵ��Ӧ�Ľڵ�
     */
    public RBnode max_node_recur(RBnode root){

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
     * BST�ݹ�淵����С�ڵ�
     * @param root Ҫ���ҵ���
     * @return ��Сֵ��Ӧ�Ľڵ�
     */
    public RBnode min_node_recur(RBnode root){

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
    public RBnode preNode(RBnode root,int key){

        RBnode p = check_recur(root,key);
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
        RBnode parent = p.getParent();
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
    public RBnode postNode(RBnode root,int key){

        RBnode p = check_recur(root,key);
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
        RBnode parent =p.getParent();
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
     * @return ȡ��ɾ���ڵ�λ�õĽڵ�
     */
    /*
    * �����������ɾ����������������д���
        1.pΪҶ�ӽڵ㣬ֱ��ɾ���ýڵ㣬���޸��丸�ڵ��ָ�루����Ҫע�������Ǹ��ڵ�Ͳ��Ǹ��ڵ㣩
        2.pΪ��֧�ڵ㣨��ֻ����������������������p��������p�ĸ��׽ڵ�������ɾ��p���ɣ���ע����Ǹ��ڵ�Ͳ��Ǹ��ڵ㣩��
        3.���������ӵ��������ǰ�����������������Ԫ�ؽ�����Ȼ��ɾ����ǰ��㡣����������Ԫ��һ����Ҷ�ӽ�㣬������
            ��ǰ��㼴ΪҶ�ӽ�㣬ɾ���ο�û�к��ӵ��������һ�ַ����ǣ���ǰ���������������С��Ԫ�ؽ�����Ȼ��ɾ����ǰ��㡣
    * */
    public RBnode remove(RBnode root, int key){
        if (root == null)
            return null;
        RBnode replace = null;


        return replace;
    }
    /**
     *
     * �Ժ�����Ľڵ�(x)��������ת
     *
     * ����ʾ��ͼ(�Խڵ�x��������)��
     *
     *	    /							 /
     *	   x							y
     *   /  \	  -(����)->.		       / \
     *  lx   y						  x  ry
     *	   /  \					    /  \
     *	  ly  ry				   lx  ly
     *
     */
    public RBnode LeftRotate(RBnode x){
        RBnode root = null; //Ҫ���صĸ��ڵ�
        RBnode y = x.getRight(); //����x���Һ���Ϊy
        root = y;   //��y����ΪҪ���صĽڵ�
        //���£�һ��Ҫ��������֦
        //1 ��y����������Ϊx���Һ��ӣ����y�����ӷǿգ�������xΪy���ӵĸ���
        x.setRight(y.getLeft());
        if (y.getLeft() != null){
            y.getLeft().setParent(x);
        }
        //2 ��y�ĸ��ڵ�����Ϊx�ĸ��ڵ㣬���x�ĸ��ڵ�ǿգ������ö�Ӧ����Ϊy
        y.setParent(x.getParent());
        if (x.getParent() != null){
            if (x.getParent().getLeft() == x){
                x.getParent().setLeft(y);
            }else {
                x.getParent().setRight(y);
            }
        }
        //3 ��y����Ϊx�ĸ��ڵ㣬x����Ϊy������
        x.setParent(y);
        y.setLeft(x);
        return root;
    }
    /**
     *
     * �Ժ�����Ľڵ�(y)��������ת
     *
     * ����ʾ��ͼ(�Խڵ�y��������)��
     *
     *		   /						   /
     *		  y							  x
     *		 /  \	  -(����)->.			/  \
     *		x   ry					   lx   y
     *	   / \							   / \
     *	  lx  rx						  rx  ry
     */
    public RBnode RightRotate(RBnode y){
        RBnode root = null; //Ҫ���صĸ��ڵ�
        RBnode x = y.getLeft(); //����y���Һ���Ϊx
        root = x;   //��y����ΪҪ���صĽڵ�
        //���£�һ��Ҫ��������֦
        //1 ��x���Һ�������Ϊy�����ӣ����x���Һ��ӷǿգ�������yΪx���Һ��ӵĸ���
        y.setLeft(x.getRight());
        if (x.getRight() != null){
            x.getRight().setParent(y);
        }
        //2 ��y�ĸ��ڵ�����Ϊx�ĸ��ڵ㣬���y�ĸ��ڵ�ǿգ������ö�Ӧ����Ϊx
        x.setParent(y.getParent());
        if (y.getParent() != null){
            if (y.getParent().getLeft() == y){
                y.getParent().setLeft(x);
            }else {
                y.getParent().setRight(x);
            }
        }
        //3 ��x����Ϊy�ĸ��ڵ㣬y����Ϊx���Һ���
        y.setParent(x);
        x.setRight(y);
        return root;
    }

    /**
     * �����������������
     * @param node ����Ľڵ�
     */
    private void insertFixUp(RBnode node){
        RBnode parent ;
        RBnode gparent;
        //������ڵ�ĸ��ڵ�Ϊ��ɫ������������4
        while ( node.getParent() != null && node.getParent().getColor() == RBnode.RED){
            parent = node.getParent();
            gparent = parent.getParent();   //���ڵ�Ϊ�죬��ش���һ����ɫ�����Ƚڵ�
            //�����ڵ������Ƚڵ������
            if (parent == gparent.getLeft()){
                //���1��node����ڵ��Ǻ�ɫ��
                RBnode uncle = gparent.getRight();
                if (uncle != null && uncle.getColor() == RBnode.RED){
                    //�����ڵ����ڵ�Ⱦ�ɺ�ɫ�����������Ľڵ���������4��
                    parent.setColor(RBnode.BLCAK);
                    uncle.setColor(RBnode.BLCAK);
                    //�������п����ƻ�����5���ʽ����Ƚڵ�Ⱦ�ɺ�ɫ������µ�node����������
                    gparent.setColor(RBnode.RED);
                    node = gparent;
                    continue;
                }
                //���2����ڵ��Ǻ�ɫ�ҵ�ǰ�ڵ����Һ���
                if (parent.getRight() == node){
                    LeftRotate(parent); //ͨ����תת��Ϊ���3
                    RBnode temp = parent;
                    parent = node;
                    node = temp;
                }
                //���3����ڵ��Ǻ�ɫ���ҵ�ǰ�ڵ�������
                parent.setColor(RBnode.BLCAK);
                gparent.setColor(RBnode.RED);
                RightRotate(gparent);
            }else { //�����ڵ������Ƚڵ���Һ���
                RBnode uncle = gparent.getLeft();
                //���1����ڵ��Ǻ�ɫ
                if (uncle!= null && uncle.getColor() == RBnode.RED){
                    uncle.setColor(RBnode.BLCAK);
                    parent.setColor(RBnode.BLCAK);
                    gparent.setColor(RBnode.RED);
                    node = gparent;
                    continue;
                }

                //���2����ڵ��Ǻ�ɫ�ҵ�ǰ�ڵ�������
                if (parent.getLeft() == node){
                    RightRotate(parent);    //ͨ����תת��Ϊ���3
                    RBnode temp = parent;
                    parent = node;
                    node = temp;
                }
                //���3����ڵ��Ǻ�ɫ���ҵ�ǰ�ڵ����Һ���
                parent.setColor(RBnode.BLCAK);
                gparent.setColor(RBnode.RED);
                LeftRotate(gparent);
            }
        }
    }
    /**
     * ������Ĳ�����������������裺
     * 1. �Զ����£����½ڵ���룺������������һ�Ŷ�������������ڵ����󣬸�����Ȼ��һ�Ŷ����������
     *              Ҳ����ζ�ţ����ļ�ֵ��Ȼ������ġ����⣬������������������������ת֮ǰ������Ƕ����������
     *              ��ת֮����һ�����Ƕ������������Ҳ����ζ�ţ��κε���ת��������ɫ������
     *              ������ı�����Ȼ��һ�Ŷ������������ʵ��
     * 2. ���������ɫΪ��ɫ��������Ľڵ���ɫΪ��ɫ������Υ��"����(5)"����Υ��һ�����ԣ�����ζ��������Ҫ��������Խ�١�
     *                       ����������ҪŬ����������������������ʼ��ɣ������˵Ļ�����������һ�ź�����ˡ�
     * 3. �Ե����ϣ�����ת����ɫ�������ֺ���������ʣ�ֻ��"����(4)"�п���Υ�������ǵĲ�����Ҫ����ʹ��������
     * �����������ת�������ִ��2��
     * @param root Ҫ�����ĺ����
     * @param node Ҫ�����ֵ
     * @return �����ĺ����
     */
    public RBnode insert(RBnode root, RBnode node){
        //1.�Զ����£������½ڵ㣬�����������һ�Ŷ�����
        root = insert_unrecur(root,node);
        //2.���ýڵ���ɫΪ��ɫ
        node.setColor(RBnode.RED);
        //3.������������Ϊһ�ź����
        insertFixUp(node);
        root.setColor(RBnode.BLCAK);    //���ø��ڵ�Ϊ��ɫ
        return root;
    }


    
    /**
     * �������ɾ����������2�����裺
     * 1. �Զ����£����½ڵ�ɾ����������������һ�Ŷ�������������ڵ����󣬸�����Ȼ��һ�Ŷ����������
     *              Ҳ����ζ�ţ����ļ�ֵ��Ȼ������ġ����⣬������������������������ת֮ǰ������Ƕ����������
     *              ��ת֮����һ�����Ƕ������������Ҳ����ζ�ţ��κε���ת��������ɫ������
     *              ������ı�����Ȼ��һ�Ŷ������������ʵ��
     * 2. �Ե����ϣ�����ת����ɫ�������ֺ���������ʣ�ֻ��"����(4)"�п���Υ�������ǵĲ�����Ҫ����ʹ��������
     * @param root Ҫ�����ĺ����
     * @param node Ҫɾ���Ľڵ�
     * @return ɾ����ĺ����
     */
    public RBnode remove(RBnode root,RBnode node){

        RBnode p = check_recur(root, node.getKey());
        //��ɾ���ڵ���Ҷ�ӽڵ�
        if (p.getRight() == null && p.getLeft() == null){
            RBnode parent = p.getParent();
            if (parent == null){
                //���ڵ�
                p=null;
                root = null;
            }else {
                //Ҷ�ӽڵ�
                //�ȴ����丸�ڵ�
                if (parent.getLeft() == p){
                    parent.setLeft(null);
                }else {
                    parent.setRight(null);
                }
                //�ٴ����ɾ���ڵ�
                if (p.getColor() == RBnode.BLCAK)
                    removeFixUp(null, parent,root);
                p=null;
            }
        }else if (p.getLeft() != null && p.getRight() == null){
            //��ɾ���ڵ�ֻ������
            RBnode parent = p.getParent();
            if (parent == null){
                root = p.getLeft();
                //ֱ�ӽ����ڵ�Ⱦ�ɺ�ɫ
                root.setColor(RBnode.BLCAK);
                p=null;
            }else {
                //Ҷ�ӽڵ�
                //�ȴ����丸�ڵ�
                if (parent.getLeft() == p){
                    parent.setLeft(p.getLeft());
                }else {
                    parent.setRight(p.getLeft());
                }
                //�ٴ����ɾ���ڵ�
                if (p.getColor() == RBnode.BLCAK)
                    removeFixUp(p.getLeft(), parent,root);
                p=null;
            }
        }else if (p.getRight() != null && p.getLeft() == null){
            //��ɾ���ڵ�ֻ���Һ���
            RBnode parent = p.getParent();
            if (parent == null){
                root = p.getRight();
                root.setColor(RBnode.BLCAK);
                p=null;
            }else {
                //Ҷ�ӽڵ�
                //�ȴ����丸�ڵ�
                if (parent.getLeft() == p){
                    parent.setLeft(p.getRight());
                }else {
                    parent.setRight(p.getRight());
                }
                //�ٴ����ɾ���ڵ�
                if (p.getColor() == RBnode.BLCAK)
                    removeFixUp(p.getRight(), parent,root);
                p = null;
            }
        }else {
            //��ɾ���Ľڵ�������������Һ���
            //�ҵ���ɾ���ڵ�ĺ�̽ڵ㣬�˺�̽ڵ�һ��û������
            RBnode postnode = postNode(p,node.getKey());
            //�ú�̽ڵ�ֱֵ�Ӹ���Ҳ���ԣ�ֻ������������Ƚ���ȷ
//            p.setKey(postnode.getKey());
            //������̽ڵ�ʹ�ɾ���ڵ�ֵ
            swap(p,postnode);
            //ɾ����̽ڵ�λ�õ�ֵ������ɾ���ڵ��ֵ
            if (p.getParent().getLeft() == p){
                p.getParent().setLeft(p.getRight());
            }
            //�ٴ����ɾ���ڵ�
            if (p.getColor() == RBnode.BLCAK)
                removeFixUp(p.getRight(), p.getParent(),root);
            p=null;
        }
        return root;
    }

    /**
     * �����ɾ����������
     * @param node ɾ��λ�õ��½ڵ�
     * @param parent node�ĸ��ڵ�
     * @param root ������ĸ�
     */
    private void removeFixUp(RBnode node,RBnode parent,RBnode root){
        RBnode other;
        while ((node == null || node.getColor() == RBnode.BLCAK) && (node.getParent() != null)){
            if (parent.getLeft() == node){
                other = parent.getRight();
                //���1;node���ֵܽڵ�other�Ǻ�ɫ��ͨ����ɫ��һ����ת��ת��Ϊ���2,3,4
                if (other.getColor() == RBnode.RED){
                    other.setColor(RBnode.BLCAK);
                    parent.setColor(RBnode.RED);
                    LeftRotate(parent);
                    other = parent.getRight();
                }
                //���2��node���ֵܽڵ�other�Ǻ�ɫ�ģ�����other����������Ҳ���Ǻ�ɫ
                //���ͨ�����1�������2����whileѭ����������Ϊ�µĽڵ�node�Ǻ�ɫ��
                if ((other.getLeft() == null || other.getLeft().getColor() == RBnode.BLCAK) &&
                        (other.getRight() == null || other.getRight().getColor() == RBnode.BLCAK)){
                    other.setColor(RBnode.RED);
                    node = parent;
                    parent = node.getParent();
                }else {
                    //���3��node���ֵܽڵ�other�Ǻ�ɫ�ģ���other�������Ǻ�ɫ���Һ����Ǻ�ɫ
                    //ͨ��������ɫ��һ����ת�ɽ����3ת��Ϊ���4
                    if (other.getRight() == null || other.getRight().getColor() == RBnode.BLCAK){
                        other.getLeft().setColor(RBnode.BLCAK);
                        other.setColor(RBnode.RED);
                        other = RightRotate(other);
                    }
                    //���4��node���ֵܽڵ�ohter�Ǻ�ɫ�ģ���other���Һ����Ǻ�ɫ�ģ�������������ɫ
                    //ͨ��������ɫ��һ����ת��ȥ��x�Ķ����ɫ����x����Ϊ���ڵ����ֹѭ��
                    other.setColor(parent.getColor());
                    parent.setColor(RBnode.BLCAK);
                    other.getRight().setColor(RBnode.BLCAK);
                    LeftRotate(other);
                    node = root;
                    break;
                }
            }else {
                //���������һ����ֻ������Ҫ����
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
