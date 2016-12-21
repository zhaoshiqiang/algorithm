package dynamic_programming;

/**
 * 01背包问题
 * Created by zhaoshiqiang on 2016/12/20.
 */
public class Knapsack_01 {
    private int maxWeight;
    private Stuff[] stuffs;
    private Record[][] record;
    private Record[] recordII;

    public Knapsack_01(int maxWeight, Stuff[] stuffs) {
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
    public void solve(){
        for (int i=1; i<stuffs.length; i++){
            int stuffweight = stuffs[i].getWeight();
            System.out.print("第" + i + "行： ");
            for (int j=1; j<=maxWeight; j++){
                if ( j - stuffweight >=0 ){
                    if (record[i-1][j].getValue() < record[i-1][j-stuffweight].getValue()+stuffs[i].getValue()){
                        record[i][j].setValue(record[i - 1][j - stuffweight].getValue() + stuffs[i].getValue());
                        record[i][j].setX_parent(i - 1);
                        record[i][j].setY_parent(j -stuffweight);
                    }else {
                        record[i][j].setValue( record[i-1][j].getValue());
                        record[i][j].setX_parent(i-1);
                        record[i][j].setY_parent(j);
                    }
                }else {
                    record[i][j].setValue( record[i-1][j].getValue());
                    record[i][j].setX_parent(i-1);
                    record[i][j].setY_parent(j);
                }
                System.out.print(record[i][j].getValue() +
                        "("+record[i][j].getX_parent()+","+record[i][j].getY_parent()+")    ");
            }
            System.out.print("\n");
        }
    }
    //这个空间复杂度为O(m)
    public void solveII(){
        for (int i=1; i<stuffs.length; i++){
            int stuffweight = stuffs[i].getWeight();
            System.out.print("第" + i + "行： ");
            //这里要求在每次主循环中我们以maxWeight...1的递减顺序计算recordII，
            // 这样才能保证计算recordII时，recordII[j - stuffweight]保存的是状态record[i - 1][j - stuffweight]的值
            for (int j=maxWeight; j>=1; j--){
                if ( j - stuffweight >=0 ){
                    if (recordII[j].getValue() < recordII[j-stuffweight].getValue()+stuffs[i].getValue()){
                        recordII[j].setValue(recordII[j - stuffweight].getValue() + stuffs[i].getValue());
                        recordII[j].setX_parent(i - 1);
                        recordII[j].setY_parent(j -stuffweight);
                    }else {
                        recordII[j].setValue( recordII[j].getValue());
                        recordII[j].setX_parent(i-1);
                        recordII[j].setY_parent(j);
                    }
                }else {
                    recordII[j].setValue( recordII[j].getValue());
                    recordII[j].setX_parent(i-1);
                    recordII[j].setY_parent(j);
                }
                System.out.print(recordII[j].getValue() +
                        "("+recordII[j].getX_parent()+","+recordII[j].getY_parent()+")    ");
            }
            System.out.print("\n");
        }
    }
    public static void main(String args[]) {
        Stuff stuffs[] = {
                new Stuff(0,0),
                new Stuff(2, 3),
                new Stuff(1, 2),
                new Stuff(3, 4),
                new Stuff(2, 2)
        };
        Knapsack_01 knapsack = new Knapsack_01(5,stuffs);
        knapsack.solve();
//        knapsack.solveII();
    }
}

class Record{
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
class Stuff{
    private int weight;
    private int value;

    public Stuff(int weight, int value) {
        this.weight = weight;
        this.value = value;
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
}