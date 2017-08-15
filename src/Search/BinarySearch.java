package Search;

/**
 * Created by zhaoshiqiang on 2017/8/15.
 */
public class BinarySearch {

    /**
     * * 二分查找
     * @param srcArray
     *            有序数组 *
     * @param des
     *            查找元素 *
     * @return des的数组下标，没找到返回-1
     */
    public static int binarySearch(int[] srcArray, int des){
        int low = 0;
        int high = srcArray.length-1;
        while (low <= high){
            int mid = (low + high) / 2;
            if (srcArray[mid] == des){
                return mid;
            }else if (srcArray[mid] > des){
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return -1;
    }

    /**
     * 二分查找特定整数在整型数组中的位置(递归)
     * @param dataset
     * @param data
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static int binarySearch(int[] dataset,int data,int beginIndex,int endIndex){
        int mid = (beginIndex + endIndex) / 2;
        if (dataset[mid] == data){
            return dataset[mid];
        }else if (dataset[mid] > data){
            return binarySearch(dataset,data,beginIndex,mid-1);
        }else {
            return binarySearch(dataset,data,mid+1,endIndex);
        }
    }

    public static void main(String[] args) {
        int[] src = new int[] {1, 3, 5, 7, 8, 9};
        System.out.println(binarySearch(src, 3));
        System.out.println(binarySearch(src,3,0,src.length-1));
    }
}
