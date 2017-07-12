package sort;

import util.PrintArray;
import util.Swap;

/**
 * 冒泡排序
 * Created by zhaoshiqiang on 2016/12/9.
 */
public final class b_bubble {

    public static int[] Bubble_sort(int[] a){
        int n = a.length;
        for (int i=1 ; i < n ; i++){
//            System.out.println(i);
            for (int j=n-1 ; j>i ; j--){
                if (a[j] < a[j-1]){
                    Swap.SwapTwoitem(a,j,j-1);
                }
            }
        }
        return a;
    }

    public static int[] Bubble_sortII(int[] a){
        int n = a.length;
        boolean sortflag = false;   //是否已经排好序，已经排好则为true
        for (int i=1 ; i < n ; i++){
            if (sortflag == true)
                return a;
            sortflag = true;
//            System.out.println(i);
            for (int j=n-1 ; j>i ; j--){
                if (a[j] < a[j-1]){
                    int k = a[j];
                    a[j] = a[j-1];
                    a[j-1] = k;
                    sortflag = false;
                }
            }
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
//        PrintArray.printfArray(Bubble_sort(a));
        PrintArray.printfArray(Bubble_sortII(a));
    }
}