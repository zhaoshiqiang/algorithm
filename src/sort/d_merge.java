package sort;

import util.PrintArray;

/**
 * 归并排序
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class d_merge {

    public static int[] merge_sort(int[] a){
        recursive_mergesort(a,0,a.length-1);
        return a;
    }

    public static void recursive_mergesort(int[] a, int min, int max){
        if (min < max){
            int mid = (min + max) / 2;
            //左边
            recursive_mergesort(a,min,mid);
            //右边
            recursive_mergesort(a,mid+1,max);
            //归并
            merge(a,min,mid,max);
        }
    }
    public static void merge(int[] a, int min, int mid, int max){
        int[] temp = new int[max - min + 1];
        int i=0;
        int p = min;    //左指针
        int q = mid+1;  //右指针

        //把较小的先移到temp中
        while (p <= mid && q <= max){
            if (a[p]<a[q]){
                temp[i++] = a[p++];
            }else {
                temp[i++] = a[q++];
            }
        }
        //把左边剩余的数组移到temp中
        while (p <= mid){
            temp[i++] = a[p++];
        }
        //把右边剩余的数组移到temp中
        while (q <= max){
            temp[i++] = a[q++];
        }
        //覆盖a中对应位置数据
        for (int j=0 ; j< temp.length ; j++){
            a[j+min] = temp[j];
        }

    }
    public static void main(String[] args){
        int[] a = {10,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(merge_sort(a));
    }
}
