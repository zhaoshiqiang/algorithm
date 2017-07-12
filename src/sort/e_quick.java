package sort;

import util.PrintArray;
import util.Swap;

/**
 * ��������
 * Created by zhaoshiqiang on 2016/12/9.
 */
public final class e_quick {

    public static int[] quick_sort(int[] a){
        recursive_quicksort(a,1,a.length-1);
        return a;
    }
    public static void recursive_quicksort(int[] a,int min,int max){
        if (min < max){
            int position = partition(a,min,max);
            recursive_quicksort(a,min,position-1);
            recursive_quicksort(a,position+1,max);
        }
    }
    //���ػ���λ��
    public static int partition(int[] a,int min,int max){
        int pivot = a[max]; //ѡ�����һ����Ϊ����
        int position = min;
        //�����鿪ʼɨ�������ڶ���
        for (int j = min ; j < max; j++){
            //��С�ڻ����������ŵ���������ߣ�����������Ҫ�ŵ�������position��λ�ã�
            if (a[j] <= pivot){
                Swap.SwapTwoitem(a,j,position);
                position++;
            }
        }
        //��������λ
        Swap.SwapTwoitem(a,max,position);
//        PrintArray.printfArray(a);
        return position;
    }

    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
        PrintArray.printfArray(quick_sort(a));
    }
}