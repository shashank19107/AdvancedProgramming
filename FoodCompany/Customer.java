import java.io.IOException;
import java.util.HashMap;

public class Customer implements User{
    static private HashMap<Integer, Customer> list = new HashMap<>();
    private final int delCharge;
    private String name;
    private int id;
    private static int uid =0;
    private double balance;
    private double rewards;
    private final String address;
    private String recOrders;
    private int currRes;
    private order Order;

    //static int id =0;

    Customer(String nam, String add){
        id = ++uid;
        name = nam;
        address = add;
        balance = 1000;
        delCharge = 40;
        recOrders ="";
        rewards =0;
        list.put(id, this);

    }
     void setRecorders(String x){
        recOrders+= "\n "+x;
    }
    static void choseCus() throws IOException{
        for(Integer i: list.keySet()){
            System.out.println(i+". "+list.get(i));
        }
        int n = Reader.nextInt();
        list.get(n).showQueries();


    }
    public static void printDetails(int n){
        System.out.print(list.get(n));
        System.out.print(" ,"+list.get(n).address+" ,"+list.get(n).balance+"\n");
    }

    @Override
    public String toString() {
        return name;
    }


    public static void showList() throws IOException {
        for(Integer i: list.keySet()){
            System.out.println(i+". "+list.get(i));
        }
    }
    int getdelCharge(){
        return delCharge;
    }

    @Override
    public void getRewards() {
        System.out.println("Reward Points - "+rewards);
    }

    void selectRes() throws IOException{

        int n = Reader.nextInt();
        Order = new order(this, Restaurants.list.get(n));
        Order.showMenu();

    }
    double getBalance(){
        return balance;
    }
    @Override
    public void setRewardPoints(User x, double v) {
        //System.out.println("Adding rewards "+v);
        this.rewards = rewards+ v;
       // System.out.println(rewards);


    }
    void updateRewards(double amount){
        rewards += amount;
    }
    void updateWallet(double amount){
        if(amount>rewards){
            rewards = 0;
            balance -= (amount-rewards);
        }
        else{
            rewards -= amount;
        }


    }

    @Override
    public void showQueries() throws IOException{

        System.out.println("Welcome "+name);
        System.out.println("Customer Menu");
        System.out.println("1) Select Restaurant");
        System.out.println("2) Checkout Cart");
        System.out.println("3) Reward Won");
        System.out.println("4) Print recent orders");
        System.out.println("5) Exit");
        int n = Reader.nextInt();
        switch (n){
            case 1:
                Restaurants.showList();
                selectRes();
                showQueries();


                break;
            case 2:
                if(Order==null){
                    System.out.println("Cart Empty");
                    showQueries();
                }
                Order.checkout();
                showQueries();
                break;
            case 3:
                getRewards();
                showQueries();
                break;
            case 4:
                System.out.println(recOrders);
                showQueries();
                break;
            case 5:
                return ;

            default:
                System.out.println("Enter a valid choice Try Again");

        }




    }

    public double getDiscount(double totalcost) {
        return 0;
    }
}

class Elite extends Customer{
    private final int delcharge=0;
    Elite(String nam , String add){
        super(nam, add);
    }

    @Override
    int getdelCharge() {
        return delcharge;
    }

    @Override
    public String toString() {
        return super.toString()+" (Elite)";
    }

    @Override
    public double getDiscount(double totalcost) {
        if(totalcost>200)
        return 50;
        return 0;
    }
}
class Special extends Customer{
    private final int delCharge = 20;
    Special(String nam , String add){
        super(nam, add);
    }

    @Override
    public String toString() {
        return super.toString()+" (Special)";
    }

    @Override
    int getdelCharge() {
        return delCharge;
    }

    @Override
    public double getDiscount(double totalcost) {
        if(totalcost>200)
            return 25;
        return 0;
    }


}
