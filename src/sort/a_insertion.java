package sort;

import util.PrintArray;

/**
 * 插入排序
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class a_insertion {
    public static int[] insertion_sort(int[] a){
        for (int i = 1; i < a.length; i++) {
            int j = i;
            int temp = a[i];
            while (j > 0 && temp < a[j-1]){
                a[j] = a[j-1];
                j--;
            }
            a[j] = temp;
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(insertion_sort(a));
    }
}
