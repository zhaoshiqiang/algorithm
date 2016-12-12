package sort;

import util.PrintArray;

/**
 * ≤Â»Î≈≈–Ú
 * Created by zhaoshiqiang on 2016/12/9.
 */
public final class a_insertion {


    public static int[] insertion_sort(int[] a){
        int n = a.length;
        for (int i=2; i < n; i++){
            int j = i -1;
            int k = a[i];
            while ( j > 0 && a[j] > k){
                a[j+1]=a[j];
                j--;
            }
            a[j+1] = k;
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(insertion_sort(a));
    }
}
