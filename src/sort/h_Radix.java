package sort;

import util.PrintArray;

/**
 * 基数排序
 * 计数排序的一个重要性质就是它是稳定的：
 * 具有相同值的元素在输出数组中的相对次序与它们在输入数组中的次序相同。
 * 即在输入数组中先出现的，在输出数组中也位于前面。
 * 之所以说计数排序的稳定性非常重要，是因为计数排序经常用作基数排序算法的一个子过程。
 * 计数排序的稳定性对基数排序的正确性来说，是非常关键的。
 * Created by zhaoshiqiang on 2017/7/15.
 */
public class h_Radix {
    //d为数组中最大位数
    public static int[] radix_sort(int[] a,int d){
        for (int i=0; i<d; i++){
            //依照第i位数据对a进行排序
            a=countsorting(a,i);
//            PrintArray.printfArray(a);
        }
        return a;
    }
    //利用计数排序和data中对应位对data进行排序
    private static int[] countsorting(int[] data,int expindex){

        int[] result = new int[data.length];
        int[] temp = new int[9+1];  //数的每一位最多为9，最小为0

        for (int i=0; i<data.length; i++){
            int d = getBitData(data[i],expindex);
            temp[d]++;
        }
        for (int i=1; i<temp.length; i++){
            temp[i] = temp[i] + temp[i-1];
        }
        //必须从data.length-1开始递减，这是保证计数排序稳定性的关键语句
        for (int i=data.length-1; i>=0; i--){
            int d = getBitData(data[i],expindex);
            result[temp[d]-1] = data[i];
            temp[d]--;
        }
        return result;
    }

    //获取data指定位数的数字，从0开始
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
