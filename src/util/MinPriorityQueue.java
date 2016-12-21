package util;

import java.util.Comparator;

/**
 * 最小权值优先队列
 * Created by zhaoshiqiang on 2016/12/19.
 */
public class MinPriorityQueue<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private Object[] queue;
    private int size = 0;
    private final Comparator<? super E> comparator;

    public MinPriorityQueue(){
        this(DEFAULT_INITIAL_CAPACITY,null);
    }
    public MinPriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }
    public MinPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 1){
            throw new IllegalArgumentException();
        }
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    public boolean offer(E e){
        if (size == 0){
            queue[0] = e;
        }else {
            siftUp(size,e);
        }
        size++;
        return true;
    }

    /**
     * 由下到上调整
     * @param k 开始调整的位置
     * @param x 调整的值
     */
    public void siftUp(int k, E x){
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

    private void siftUpUsingComparator(int k, E x) {
        while (k >0){
            int parent = (k-1)>>>1; //因为是从0开始，所以这里k要减1再除以2
            Object e = queue[parent];
            //如果新加的节点比父节点大，则退出
            if (comparator.compare(x, (E) e) > 0){
                break;
            }
            queue[k]=e;
            k = parent;
        }
        queue[k]=x;
    }

    private void siftUpComparable(int k, E x){
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0){
            int parent = (k-1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e)> 0){
                break;
            }
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    public E poll() {
        if (size == 0)
            return null;
        int s = --size; //维护的队列数组是从0开始，而size是从1开始的，所以这里是先--
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x);
        return result;
    }

    /**
     * 由上到下调整
     * @param k
     * @param x
     */
    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size && ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;   //因为是从0开始，所以这里的左孩子的计算要*2+1
            Object c = queue[child];
            Object min = c;
            int right = child + 1;
            //如果左孩子比右孩子大
            if (right < size && comparator.compare((E) c, (E) queue[right]) > 0)
                min = queue[child = right];
            //如果父节点比孩子都小，则退出
            if (comparator.compare(x, (E) min) <= 0)
                break;
            queue[k] = min;
            k = child;
        }
        queue[k] = x;
    }

    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++)
                if (o.equals(queue[i]))
                    return i;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public Comparator<? super E> comparator() {
        return comparator;
    }

    public Object[] getQueue() {
        return queue;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }
}
