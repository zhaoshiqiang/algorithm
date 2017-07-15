package sort;

import util.PrintArray;
import util.Swap;

/**
 * 堆排序
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class f_heap {
    //在数组范围内[0, max_index]进行建堆操作
    private static int max_index = 0;

    public static int[] heap_sort(int[] a) {
        /**
        * 最大堆建立好后，最大的元素在a[1]。因为我们的需求是从小到大排序，希望最大的放在最后。
        * 因此我们将a[1]和a[i]交换，此时a[i]就是数组中的最大的元素。
        * 请注意，交换后还需将a[1]向下调整以保持堆的特性。
        * OK现在最大的元素已经归位，需要将堆的大小减1即heapSize--，
        * 然后再将a[1]和h[i]交换，并将a[1]向下调整。
        * 如此反复，直到堆的大小变成1为止。
        * */
        do {
            Swap.SwapTwoitem(a,0,max_index--);
            max_heapify(a,0);
        }while (max_index >= 0);
        return a;
    }

    /**
     * 建堆的时候，是由底向上建立的
     * @param a
     */
    public static void build_max_heap(int[] a){
        max_index = a.length - 1;
        //数组是从0开始，所以这里要-1
        //叶子节点不管，直接从倒数第二层开始构建最大堆
        for (int i = (max_index - 1)/2 ; i>=0; i--){
            max_heapify(a,i);
        }
    }

    /**
     * 以a[i]为根建立最大堆（递归版）
     * @param a
     * @param i
     */
    public static void recursive_max_heapify(int[] a, int i){
        int max = i;
        int left = i*2+1;   //左孩子节点，这里是以0开始的，所以要+1，下同
        int right = i*2+2;  //右孩子节点

        //若其有左节点(left <= heapSize)，且左节点的值大于父节点，则标记最大节点为左节点
        if (left <= max_index && a[left] > a[max]){
            max = left;
        }
        //与上面步骤一样，只是这里是判断右节点
        if (right <= max_index && a[right] > a[max]){
            max = right;
        }
        if (max != i){
            //使父节点为最大节点
            Swap.SwapTwoitem(a,max,i);
            //调整与父节点对调的子节点，使其为最大子堆
            recursive_max_heapify(a,max);
        }
    }

    /**
     * 以a[i]为根建立最大堆（非递归版），这是由上向下调整
     * @param a
     * @param i
     */
    public static void max_heapify(int[] a, int i){
        int max = i;
        while (max < max_index){
            int temp = max;
            int left = max*2+1;
            int right = max*2+2;

            if (left <= max_index && a[left] > a[max]){
                max = left;
            }
            if (right <= max_index && a[right] > a[max]){
                max = right;
            }
            //在这个节点上不需要调整，则在其下面的节点也是符合最大堆的要求，直接跳出
            if (temp == max){
                break;
            }
            //注意这里是temp和min位置上的数字对调
            Swap.SwapTwoitem(a,max,temp);
        }
    }
    public static int[] heap_sort_min(int[] a) {
        do {
            Swap.SwapTwoitem(a,0,max_index--);
            recursive_min_heapify(a,0);
        }while (max_index >= 0);
        return a;
    }

    //建最小堆
    public static void build_min_heap(int[] a){
        max_index = a.length - 1;
        //数组是从0开始，所以这里要-1
        //叶子节点不管，直接从倒数第二层开始构建最小堆
        for (int i = (max_index - 1)/2 ; i>=0; i--){
            recursive_min_heapify(a,i);
        }
    }

    public static void min_heapify(int[] a,int i){
        int min = i;
        while (min < max_index){
            int temp = min;
            int left = min*2+1;
            int right = min*2+2;

            if (left <= max_index && a[left] < a[min]){
                min = left;
            }
            if (right <= max_index && a[right] < a[min]){
                min = right;
            }
            //在这个节点上不需要调整，则在其下面的节点也是符合最小堆的要求，直接跳出
            if (temp == min){
                break;
            }
            //注意这里是temp和min位置上的数字对调
            Swap.SwapTwoitem(a,min,temp);
        }
    }

    /**
     * 以a[i]为根建立最小堆（递归版）
     * @param a
     * @param i
     */
    public static void recursive_min_heapify(int[] a, int i){
        int min = i;
        int left = i*2+1;   //左孩子节点，这里是以0开始的，所以要+1，下同
        int right = i*2+2;  //右孩子节点

        //若其有左节点(left <= heapSize)，且左节点的值小于父节点，则标记最小节点为左节点
        if (left <= max_index && a[left] < a[min]){
            min = left;
        }
        //与上面步骤一样，只是这里是判断右节点
        if (right <= max_index && a[right] < a[min]){
            min = right;
        }
        if (min != i){
            //使父节点为最小节点
            Swap.SwapTwoitem(a,min,i);
            //调整与父节点对调的子节点，使其为最小子堆
            recursive_min_heapify(a,min);
        }
    }

    public static void main(String[] args){
        int[] a = {10,5,2,6,9,7,1,3,4,8};
        //堆排序时，第一步是对原始数组建立最大堆，然后再开始调整，排序
        build_max_heap(a);
        PrintArray.printfArray(heap_sort(a));
        build_min_heap(a);
        PrintArray.printfArray(heap_sort_min(a));

    }


}
