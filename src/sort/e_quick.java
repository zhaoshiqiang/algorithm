package sort;

import util.PrintArray;
import util.Swap;

/**
 * 快速排序
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class e_quick {
    public static int[] quick_sort(int[] a){
        recursive_quicksort(a,0,a.length-1);
        return a;
    }

    public static void recursive_quicksort(int[] a, int min, int max){
        if (min < max){
            int p = partition(a,min,max);
            recursive_quicksort(a, min, p-1);
            recursive_quicksort(a, p+1, max);
        }
    }

    //返回基数位置
    public static int partition(int[] a,int min,int max){
        int pivot = a[max]; //选择最后一个数为基数
        int position = min;
        //从数组开始扫到倒数第二个
        for (int j = min ; j < max; j++){
            //将小于基数的数都放到基数的左边（将来基数将要放到数组中position的位置）
            if (a[j] <= pivot){
                Swap.SwapTwoitem(a,j,position);
                position++;
            }
        }
        //将基数归位
        Swap.SwapTwoitem(a,max,position);
        return position;
    }
    public static void main(String[] args){
        int[] a = {10,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(quick_sort(a));
    }
}
