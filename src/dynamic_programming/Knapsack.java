package dynamic_programming;

/**
 * 01背包问题
 * Created by zhaoshiqiang on 2016/12/20.
 */
public class Knapsack {
    private int maxWeight;
    private Stuff[] stuffs;
    private Record[][] record;
    private Record[] recordII;

    private Knapsack(int maxWeight, Stuff[] stuffs) {
        this.maxWeight = maxWeight;
        this.stuffs = stuffs;
        int n = stuffs.length+1;    // 这里是从0到stuffs.length,所以要加1
        int m = maxWeight+1;    //同上，这里是0到maxWeight，所以也要加1
        record = new Record[n][m];
        recordII = new Record[m];
        for (int i=0; i<n; i++){
            for (int j=0; j< m; j++){
                record[i][j]=new Record(0);
            }
        }

        for (int i=0; i<recordII.length;i++){
            recordII[i]=new Record(0);
        }
    }
    //01背包构造函数
    public static Knapsack Knapsack_01(int maxWeight,Stuff[] stuffs){
        return new Knapsack(maxWeight,stuffs);
    }
    //完全背包构造函数
    public static Knapsack Knapsack_Complete(int maxWeight, Stuff[] stuffs){
        return new Knapsack(maxWeight,stuffs);
    }
    //多重背包构造函数
    public static Knapsack Knapsack_Multiple(int maxWeight, Stuff[] stuffs){
        return new Knapsack(maxWeight,stuffs);
    }
    public int getMaxWeight() {
        return maxWeight;
    }

    public Stuff[] getStuffs() {
        return stuffs;
    }

    public Record[][] getRecord() {
        return record;
    }

    //这个的空间复杂度是O(nm)
    public void knapsack01_solve(){
        for (int i=1; i<stuffs.length; i++){
            int stuffweight = stuffs[i].getWeight();
            System.out.printf("第%d行： ", i);
            for (int j=stuffweight; j<=maxWeight; j++){
                if (record[i-1][j].getValue() < record[i-1][j-stuffweight].getValue()+stuffs[i].getValue()){
                    record[i][j].setValue(record[i - 1][j - stuffweight].getValue() + stuffs[i].getValue());
                    record[i][j].setX_parent(i - 1);
                    record[i][j].setY_parent(j -stuffweight);
                }else {
                    record[i][j].setValue( record[i-1][j].getValue());
                    record[i][j].setX_parent(i-1);
                    record[i][j].setY_parent(j);
                }
                System.out.printf("[%2d,%2d]-->%3d(%2d,%2d)   ", i, j, record[i][j].getValue(), record[i][j].getX_parent(), record[i][j].getY_parent());
            }
            System.out.print("\n");
        }
    }
    //这个空间复杂度为O(m)
    public void knapsack01_solveii(){
        for (int i=1; i<stuffs.length; i++){

            //这里要求在每次主循环中我们以maxWeight...1的递减顺序计算recordII，
            // 这样才能保证计算recordII时，recordII[j - stuffweight]保存的是状态record[i - 1][j - stuffweight]的值
            ZeroOnePack(recordII, stuffs[i].getWeight(), stuffs[i].getValue(), i);
            System.out.print("\n");
        }
    }

    private void ZeroOnePack(Record[] f,int weight,int value,int i){
        System.out.printf("第%d件： ",i);
        for (int j=maxWeight; j>=weight; j--){
            if (f[j].getValue() < f[j-weight].getValue()+value){
                f[j].setValue(f[j - weight].getValue() + value);
                f[j].setX_parent(i-1);
                f[j].setY_parent(j -weight);
            }else {
                f[j].setValue( f[j].getValue());
                f[j].setX_parent(i-1);
                f[j].setY_parent(j);
            }
            System.out.printf("[%2d,%2d]-->%3d(%2d,%2d)   ", i, j, f[j].getValue(), f[j].getX_parent(), f[j].getY_parent());
        }
    }
    //完全背包问题
    public void CompleteKnapsack_solve(){
        for (int i=1; i<stuffs.length;i++){
            CompletePack(recordII, stuffs[i].getWeight(), stuffs[i].getValue(), i);
            System.out.print("\n");
        }
    }

    private void CompletePack(Record[] f,int weight,int value,int i){
        System.out.printf("第%d件： ",i);
        for (int j=weight; j<=maxWeight;j++){
            if (f[j].getValue() < f[j-weight].getValue()+value){
                f[j].setValue(f[j - weight].getValue() + value);
                f[j].setX_parent(i);
                f[j].setY_parent(j -weight);
            }else {
                f[j].setValue( f[j].getValue());
                f[j].setX_parent(i-1);
                f[j].setY_parent(j);
            }
            System.out.printf("[%2d,%2d]-->%3d(%2d,%2d)   ", i, j, f[j].getValue(), f[j].getX_parent(), f[j].getY_parent());
        }
    }

    public void MultipleKnapsack_solve(){
        for (int i=1; i<stuffs.length;i++){
            MultiplePack(recordII, stuffs[i].getWeight(), stuffs[i].getValue(), stuffs[i].getMaxnum(), i);
        }
    }

    private void MultiplePack(Record[] f,int weight,int value,int num,int i){
        if (weight*num >= maxWeight){
            //物品的重量大于包最大重量，则属于完全背包问题
            CompletePack(f,weight,value,i);
            System.out.print("\n");
        }else {
            //这里用二进制的思想将这个多重背包问题转化为多个01背包问题
            /*
            * 将第i种物品分成若干件01背包中的物品，其中每件物品有一个系数。
            * 这件物品的费用和价值均是原来的费用和价值乘以这个系数k。令这些系数分别为1,2,2^2，...，2^k-1,num-2^k+1，且k是满足num-2^k+1>0的最大整数。
            * 例如，如果num为13，则相应的k = 3，这种最多取13件的物品应被分成系数分别为1,2,4,6的四件物品。
            * */
            for (int k=1; k<num; k<<=1){
                ZeroOnePack(f,k*weight,k*value,i);
                System.out.print("\n");
                num-=k;
            }
            ZeroOnePack(f,num*weight,num*value ,i);
            System.out.print("\n");
        }

    }
    public static void main(String args[]) {
        Stuff stuffs_01[] = {
                new Stuff(0,0),
                new Stuff(2, 3),
                new Stuff(1, 2),
                new Stuff(3, 4),
                new Stuff(2, 2)
        };
        Knapsack knapsack01 = Knapsack.Knapsack_01(5, stuffs_01);
        knapsack01.knapsack01_solve();
        System.out.println("--------------------------------------------------------------------------------------");
        knapsack01.knapsack01_solveii();
        System.out.println("--------------------------------------------------------------------------------------");
        Stuff stuff_complete[]={
                new Stuff(0,0),
                new Stuff(3,6),
                new Stuff(2,5),
                new Stuff(5,10),
                new Stuff(1,2),
                new Stuff(6,16),
                new Stuff(4,8)
        };
        Knapsack knapsack_complete = Knapsack_Complete(10, stuff_complete);
        knapsack_complete.CompleteKnapsack_solve();
        System.out.println("--------------------------------------------------------------------------------------");
        Stuff stuff_multiple[]={
                new Stuff(0,0,0),
                new Stuff(3,6,2),
                new Stuff(2,5,1),
                new Stuff(5,10,3),
                new Stuff(1,2,4),
                new Stuff(6,16,3),
                new Stuff(4,8,2)
        };
        Knapsack multiple_complete = Knapsack_Multiple(10, stuff_multiple);
        multiple_complete.MultipleKnapsack_solve();
    }

    static class Record{
        private int value;
        private int x_parent;
        private int y_parent;

        public Record(int value) {
            this.value = value;
            this.x_parent=0;
            this.y_parent=0;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getX_parent() {
            return x_parent;
        }

        public void setX_parent(int x_parent) {
            this.x_parent = x_parent;
        }

        public int getY_parent() {
            return y_parent;
        }

        public void setY_parent(int y_parent) {
            this.y_parent = y_parent;
        }
    }
    static class Stuff{
        private int weight;
        private int value;
        private int maxnum;

        public Stuff(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public Stuff(int weight, int value, int maxnum) {
            this.weight = weight;
            this.value = value;
            this.maxnum = maxnum;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getMaxnum() {
            return maxnum;
        }

        public void setMaxnum(int maxnum) {
            this.maxnum = maxnum;
        }

    }

}