package sort;

import util.PrintArray;
import util.Swap;

/**
 * —°‘Ò≈≈–Ú
 * Created by zhaoshiqiang on 2016/12/9.
 */
public final class c_selection {

    public static int[] select_sort(int[] a){
        int n = a.length;
        for (int i=1; i<n-1 ; i++){
            int min = i;
            for (int j = i+1; j<n; j++){
                if (a[min] > a[j]){
                    min = j;
                }
            }
            Swap.SwapTwoitem(a,min,i);
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(select_sort(a));
    }
}
