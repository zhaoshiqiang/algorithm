package sort;

import util.PrintArray;

/**
 * 计数排序
 * Created by zhaoshiqiang on 2016/12/12.
 */
public class g_count {

    public static int[] count_sort(int[]data,int size){
        //存放临时数据数组
        int[] temp = new int[size+1];
        int[] result = new int[data.length];
        //将临时数组初始化为0，其实初始化时默认都为0
        for (int i=0; i<temp.length; i++){
            temp[i]=0;
        }
        // 计算数组中每个元素i出现的次数，存入数组temp中的第i项，即原数组中的元素值为temp数组中的下标
        for (int i=0; i<data.length; i++){
            temp[data[i]]++;
        }
        // 计算数组中小于等于每个元素的个数,即从temp中的第一个元素开始，每一项和前一项相加
        for (int i=1; i<temp.length; i++){
            temp[i] = temp[i]+temp[i-1];
        }
        //必须从data.length-1开始递减，这是保证计数排序稳定性的关键语句
        for (int i=data.length-1; i>=0; i--){
            //这里-1是为了防止数组越界
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
