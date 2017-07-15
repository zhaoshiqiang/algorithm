package sort;

import util.PrintArray;
import util.Swap;

/**
 * 冒泡排序
 * 基本思想是：比较+交换
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class b_bubble {
    public static int[] Bubble_sort(int[] a){
        for (int i = a.length; i >0; i--) {
            for (int j=0; j<i-1 ; j++){
                if (a[j] > a[j+1]){
                    Swap.SwapTwoitem(a,j,j+1);
                }
            }
        }
        return a;
    }

    public static int[] Bubble_sortII(int[] a){
        boolean flag = false; //标识是否已经排好序
        for (int i = a.length; i >0; i--) {
            if (flag == true){
                return a;
            }
            flag = true;
            for (int j=0; j<i-1 ; j++){
                if (a[j] > a[j+1]){
                    Swap.SwapTwoitem(a,j,j+1);
                    flag = false;
                }
            }
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {10,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(Bubble_sort(a));
        PrintArray.printfArray(Bubble_sortII(a));
    }
}
