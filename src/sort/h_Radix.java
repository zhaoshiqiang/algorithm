package sort;

import util.PrintArray;

/**
 * ��������
 * ���������һ����Ҫ���ʾ��������ȶ��ģ�
 * ������ֵͬ��Ԫ������������е���Դ��������������������еĴ�����ͬ��
 * ���������������ȳ��ֵģ������������Ҳλ��ǰ�档
 * ֮����˵����������ȶ��Էǳ���Ҫ������Ϊ�������򾭳��������������㷨��һ���ӹ��̡�
 * ����������ȶ��ԶԻ����������ȷ����˵���Ƿǳ��ؼ��ġ�
 * Created by zhaoshiqiang on 2016/12/12.
 */
public class h_Radix {

    //dΪ���������λ��
    public static int[] radix_sort(int[] a,int d){
        for (int i=0; i<d; i++){
            //���յ�iλ���ݶ�a��������
            a=countsorting(a,i);
//            PrintArray.printfArray(a);
        }
        return a;
    }
    //���ü��������data�ж�Ӧλ��data��������
    private static int[] countsorting(int[] data,int expindex){

        int[] result = new int[data.length];
        int[] temp = new int[9+1];  //����ÿһλ���Ϊ9����СΪ0

        for (int i=0; i<data.length; i++){
            int d = getBitData(data[i],expindex);
            temp[d]++;
        }
        for (int i=1; i<temp.length; i++){
            temp[i] = temp[i] + temp[i-1];
        }
        //�����data.length-1��ʼ�ݼ������Ǳ�֤���������ȶ��ԵĹؼ����
        for (int i=data.length-1; i>=0; i--){
            int d = getBitData(data[i],expindex);
            result[temp[d]-1] = data[i];
            temp[d]--;
        }
        return result;
    }

    //��ȡdataָ��λ�������֣���0��ʼ
    private static int getBitData(int data,int expindex){
        for (int i=expindex ; i>0; i--){
            data /=10;
        }
        return data%10;
    }
    public static void main(String[] args){
        int[] a = {0,532,211,22,98,4391,231,311,221,128};
        PrintArray.printfArray(radix_sort(a, 4));
    }
}
