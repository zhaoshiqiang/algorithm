package dynamic_programming;

import util.PrintArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 钢条切割
 * Created by zhaoshiqiang on 2016/12/17.
 */
public class cut_rod {
    //对应最佳收益
    public static int[] r;
    //对应最佳切割方式
    public static int[] s;

    public static void botton_up_cutrod(int[] p,int n){
        r=new int[n+1];
        s=new int[n+1];
        r[0] = 0;
        for (int i = 1; i <= n ; i++){
            int q = -1;
            for (int j=1 ; j<=i; j++){
                //松弛操作
                if ( q < p[j] + p[i-j]){
                    q = p[j] + p[i-j];
                    s[i]=j;
                }
                r[i] = q;
            }
        }
    }

    public static void main(String[] args){
        int[] p = {0,1,5,8,9,10,17,17,20,24,30};
        botton_up_cutrod(p,10);
        for (int i=1; i < p.length; i++){
            System.out.println("长度： "+ i +" 对应最佳切割为： "+ s[i] +" ；对应最大收益为： "+ r[i]);
        }

    }
}
