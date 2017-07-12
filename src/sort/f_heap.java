package sort;

import util.PrintArray;
import util.Swap;

/**
 * 堆排序
 * Created by zhaoshiqiang on 2016/12/11.
 */
public final class f_heap {

    private static int heapSize = 0;
    public static int[] heap_sort(int[] a){
        heapSize = a.length-1;

        /*
        * 最大堆建立好后，最大的元素在a[1]。因为我们的需求是从小到大排序，希望最大的放在最后。
        * 因此我们将a[1]和a[i]交换，此时a[i]就是数组中的最大的元素。
        * 请注意，交换后还需将a[1]向下调整以保持堆的特性。
        * OK现在最大的元素已经归位，需要将堆的大小减1即heapSize--，
        * 然后再将a[1]和h[i]交换，并将a[1]向下调整。
        * 如此反复，直到堆的大小变成1为止。
        * */
        for (int i=heapSize ; i > 1; i--){
            Swap.SwapTwoitem(a,i,1);
            heapSize--;
//            recursive_max_heapify(a, 1);
            max_heapify(a,1);
        }

        return a;
    }

    //以a[i]为根建立最大堆（递归版）
    public static void recursive_max_heapify(int[] a, int i){
        int largest = i;
        int left = 2*i; //左孩子节点
        int right = 2*i+1;  //右孩子节点
        //若其有左节点(left <= heapSize)，且左节点的值大于父节点，则标记最大节点为左节点
        if (left <= heapSize && a[largest] < a[left]){
            largest = left;
        }
        //与上面步骤一样，只是这里是判断右节点
        if (right <= heapSize && a[largest] < a[right]){
            largest = right;
        }
        if (largest != i){
            //使父节点为最大节点
            Swap.SwapTwoitem(a,i,largest);
            //调整与父节点对调的子节点，使其为最大子堆
            recursive_max_heapify(a, largest);
        }
    }

    /**
     * 以a[i]为根建立最大堆（非递归版），这是由上向下调整
     * @param a 要建立堆的数组
     * @param i 从哪里开始建立堆
     */
    public static void max_heapify(int[] a,int i){
        boolean flag = false;   //标记是否已经建好堆
        int max=0;  //用于标记最大的下标
        while ( i*2 <= heapSize && flag == false){
            if (a[i] < a[i*2]){
                max = i*2;
            }else {
                max = i;
            }

            if ( i*2+1 <= heapSize){
                if ( a[max] < a[i*2+1]){
                    max = i*2+1;
                }
            }

            if (max != i){
                Swap.SwapTwoitem(a,max,i);
                i = max;
            }else {
                flag = true;
            }
        }
    }
    /**
     * 以a[i]为根建立最小堆（非递归版），这是由上向下调整
     * @param a 要建立堆的数组
     * @param i 从哪里开始建立堆
     */
    public static void min_heapify(int[] a,int i){
        boolean flag = false;   //标记是否已经建好堆
        int min = i;    //标记最小的下标
        while (i*2 < heapSize && flag == true){

            if (a[i] > a[2*i]){
                min = 2*i;
            }
            if (2*i+1 < heapSize){
                if (a[min] > a[2*i+1]){
                    min = 2*i+1;
                }
            }
            if (min != i){
                Swap.SwapTwoitem(a,min,i);
                i = min;
            }else {
                flag = true;
            }
        }
    }
    public static void siftup(int[] a,int i){
        boolean flag = false;   //标记是否已经建好堆
        if (i == 1)
            return;
        while (i != 1 && flag == false){
            if (a[i] > a[i/2]){
                Swap.SwapTwoitem(a,i,i/2);
            }else {
                flag = true;
            }
            i = i/2;
        }
    }

    //建堆的时候，是由底向上建立的
    public static void build_max_heap(int[] a){
        heapSize = a.length-1;
        //叶子节点不管，直接从倒数第二层开始构建最大堆
        for (int i= heapSize/2; i >= 1; i--){
//            recursive_max_heapify(a, i);
            max_heapify(a,i);
        }
    }

    //建最小堆
    public static void build_min_heap(int[] a){
        heapSize = a.length-1;
        //叶子节点不管，直接从倒数第二层开始构建最大堆
        for (int i= heapSize/2; i >= 1; i--){
//            recursive_max_heapify(a, i);
            min_heapify(a,i);
        }
    }


    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
        build_max_heap(a);
        PrintArray.printfArray(heap_sort(a));
    }
}