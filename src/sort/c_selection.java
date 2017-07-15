package sort;

import util.PrintArray;
import util.Swap;

/**
 * 选择排序
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class c_selection {
    public static int[] select_sort(int[] a){
        for (int i=0; i<a.length ; i++){
            int min = i;
            for (int j=i+1; j<a.length ; j++){
                if (a[min] > a[j]){
                    min = j;
                }
            }
            Swap.SwapTwoitem(a,min,i);
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {10,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(select_sort(a));
    }
}
