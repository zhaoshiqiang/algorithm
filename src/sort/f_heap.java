package sort;

import util.PrintArray;
import util.Swap;

/**
 * ������
 * Created by zhaoshiqiang on 2016/12/11.
 */
public final class f_heap {

    private static int heapSize = 0;
    public static int[] heap_sort(int[] a){
        heapSize = a.length-1;

        /*
        * ���ѽ����ú�����Ԫ����a[1]����Ϊ���ǵ������Ǵ�С��������ϣ�����ķ������
        * ������ǽ�a[1]��a[i]��������ʱa[i]���������е�����Ԫ�ء�
        * ��ע�⣬�������轫a[1]���µ����Ա��ֶѵ����ԡ�
        * OK��������Ԫ���Ѿ���λ����Ҫ���ѵĴ�С��1��heapSize--��
        * Ȼ���ٽ�a[1]��h[i]����������a[1]���µ�����
        * ��˷�����ֱ���ѵĴ�С���1Ϊֹ��
        * */
        for (int i=heapSize ; i > 1; i--){
            Swap.SwapTwoitem(a,i,1);
            heapSize--;
//            recursive_max_heapify(a, 1);
            max_heapify(a,1);
        }

        return a;
    }

    //��a[i]Ϊ���������ѣ��ݹ�棩
    public static void recursive_max_heapify(int[] a, int i){
        int largest = i;
        int left = 2*i; //���ӽڵ�
        int right = 2*i+1;  //�Һ��ӽڵ�
        //��������ڵ�(left <= heapSize)������ڵ��ֵ���ڸ��ڵ㣬�������ڵ�Ϊ��ڵ�
        if (left <= heapSize && a[largest] < a[left]){
            largest = left;
        }
        //�����沽��һ����ֻ���������ж��ҽڵ�
        if (right <= heapSize && a[largest] < a[right]){
            largest = right;
        }
        if (largest != i){
            //ʹ���ڵ�Ϊ���ڵ�
            Swap.SwapTwoitem(a,i,largest);
            //�����븸�ڵ�Ե����ӽڵ㣬ʹ��Ϊ����Ӷ�
            recursive_max_heapify(a, largest);
        }
    }

    /**
     * ��a[i]Ϊ���������ѣ��ǵݹ�棩�������������µ���
     * @param a Ҫ�����ѵ�����
     * @param i �����￪ʼ������
     */
    public static void max_heapify(int[] a,int i){
        boolean flag = false;   //����Ƿ��Ѿ����ö�
        int max=0;  //���ڱ�������±�
        while ( i*2 <= heapSize && flag == false){
            if (a[i] < a[i*2]){
                max = i*2;
            }else {
                max = i;
            }

            if ( i*2+1 <= heapSize){
                if ( a[max] < a[i*2+1]){
                    max = i*2+1;
                }
            }

            if (max != i){
                Swap.SwapTwoitem(a,max,i);
                i = max;
            }else {
                flag = true;
            }
        }
    }
    /**
     * ��a[i]Ϊ��������С�ѣ��ǵݹ�棩�������������µ���
     * @param a Ҫ�����ѵ�����
     * @param i �����￪ʼ������
     */
    public static void min_heapify(int[] a,int i){
        boolean flag = false;   //����Ƿ��Ѿ����ö�
        int min = i;    //�����С���±�
        while (i*2 < heapSize && flag == true){

            if (a[i] > a[2*i]){
                min = 2*i;
            }
            if (2*i+1 < heapSize){
                if (a[min] > a[2*i+1]){
                    min = 2*i+1;
                }
            }
            if (min != i){
                Swap.SwapTwoitem(a,min,i);
                i = min;
            }else {
                flag = true;
            }
        }
    }
    public static void siftup(int[] a,int i){
        boolean flag = false;   //����Ƿ��Ѿ����ö�
        if (i == 1)
            return;
        while (i != 1 && flag == false){
            if (a[i] > a[i/2]){
                Swap.SwapTwoitem(a,i,i/2);
            }else {
                flag = true;
            }
            i = i/2;
        }
    }

    //���ѵ�ʱ�����ɵ����Ͻ�����
    public static void build_max_heap(int[] a){
        heapSize = a.length-1;
        //Ҷ�ӽڵ㲻�ܣ�ֱ�Ӵӵ����ڶ��㿪ʼ��������
        for (int i= heapSize/2; i >= 1; i--){
//            recursive_max_heapify(a, i);
            max_heapify(a,i);
        }
    }

    //����С��
    public static void build_min_heap(int[] a){
        heapSize = a.length-1;
        //Ҷ�ӽڵ㲻�ܣ�ֱ�Ӵӵ����ڶ��㿪ʼ��������
        for (int i= heapSize/2; i >= 1; i--){
//            recursive_max_heapify(a, i);
            min_heapify(a,i);
        }
    }


    public static void main(String[] args){
        int[] a = {0,5,2,6,9,7,1,3,4,8};
        build_max_heap(a);
        PrintArray.printfArray(heap_sort(a));
    }
}