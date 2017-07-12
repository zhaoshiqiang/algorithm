package sort;

import util.PrintArray;

/**
 * 归并排序
 * Created by zhaoshiqiang on 2016/12/9.
 */
public final class d_merge {
    public static int[] merge_sort(int[] a){
        recursive_mergesort(a, 1, a.length - 1);
        return a;
    }

    public static void recursive_mergesort(int[] a, int min, int max){
        if (min < max){
            int mid = (min + max)/2;
            //左边
            recursive_mergesort(a, min, mid);
            //右边
            recursive_mergesort(a, mid + 1, max);
            //归并
            merge(a,min,mid,max);
        }
    }

    public static void merge(int[] a,int min, int mid, int max){
        int[] temp =new int[max-min+1];
        int i=min;  //左指针
        int j=mid+1;    //右指针
        int k=0;

        //把较小的先移到temp中
        while (i<=mid && j<=max){
            if (a[i]<a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }
        //把左边剩余的数组移到temp中
        while (i<=mid){
            temp[k++] = a[i++];
        }
        //把右边剩余的数组移到temp中
        while (j<=max){
            temp[k++] = a[j++];
        }
        //覆盖a中对应位置数据
        for (int p = 0 ; p<temp.length ;p++){
            a[min+p]=temp[p];
        }
        System.out.println(min);
        System.out.println(mid);
        System.out.println(max);
        PrintArray.printfArray(a);
    }

    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(merge_sort(a));
    }
}