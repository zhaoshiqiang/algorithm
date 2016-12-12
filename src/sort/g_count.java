package sort;

import util.PrintArray;

/**
 * ��������
 * Created by zhaoshiqiang on 2016/12/12.
 */
public class g_count {

    public static int[] count_sort(int[]data,int size){
        //�����ʱ��������
        int[] temp = new int[size+1];
        int[] result = new int[data.length];
        //����ʱ�����ʼ��Ϊ0����ʵ��ʼ��ʱĬ�϶�Ϊ0
        for (int i=0; i<temp.length; i++){
            temp[i]=0;
        }
        // ����������ÿ��Ԫ��i���ֵĴ�������������temp�еĵ�i���ԭ�����е�Ԫ��ֵΪtemp�����е��±�
        for (int i=0; i<data.length; i++){
            temp[data[i]]++;
        }
        // ����������С�ڵ���ÿ��Ԫ�صĸ���,����temp�еĵ�һ��Ԫ�ؿ�ʼ��ÿһ���ǰһ�����
        for (int i=1; i<temp.length; i++){
            temp[i] = temp[i]+temp[i-1];
        }
        //�����data.length-1��ʼ�ݼ������Ǳ�֤���������ȶ��ԵĹؼ����
        for (int i=data.length-1; i>=0; i--){
            //����-1��Ϊ�˷�ֹ����Խ��
            result[temp[data[i]]-1]=data[i];
            temp[data[i]]--;
        }
        return result;
    }

    public static void main(String[] args){
        int[] a = {0,5,2,2,9,9,1,3,4,8};
        PrintArray.printfArray(count_sort(a,9));
    }
}
