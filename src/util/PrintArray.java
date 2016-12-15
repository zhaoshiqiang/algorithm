package util;

/**
 * Created by zhaoshiqiang on 2016/12/9.
 */
public final class PrintArray {

    public static void printfArray(int[] b){

        System.out.print("[");
        for (int i = 1; i < b.length ; i++ ){
            System.out.print(b[i]+"  ");
        }
        System.out.println("]");
    }
    public static void printfArray(double[] b){

        System.out.print("[");
        for (int i = 1; i < b.length ; i++ ){
            System.out.print(b[i]+"  ");
        }
        System.out.println("]");
    }
}
