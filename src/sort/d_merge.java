package sort;

import util.PrintArray;

/**
 * �鲢����
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
            //���
            recursive_mergesort(a, min, mid);
            //�ұ�
            recursive_mergesort(a, mid + 1, max);
            //�鲢
            merge(a,min,mid,max);
        }
    }

    public static void merge(int[] a,int min, int mid, int max){
        int[] temp =new int[max-min+1];
        int i=min;  //��ָ��
        int j=mid+1;    //��ָ��
        int k=0;

        //�ѽ�С�����Ƶ�temp��
        while (i<=mid && j<=max){
            if (a[i]<a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }
        //�����ʣ��������Ƶ�temp��
        while (i<=mid){
            temp[k++] = a[i++];
        }
        //���ұ�ʣ��������Ƶ�temp��
        while (j<=max){
            temp[k++] = a[j++];
        }
        //����a�ж�Ӧλ������
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