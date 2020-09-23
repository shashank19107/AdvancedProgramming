

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurants implements User {
    protected String name;
    private final int uid ;
    private String Address;
    private int orders;
    private final HashMap<Integer,Food> foods = new HashMap<>();
    private double Rewards;
    private double discountonBill;
    protected static HashMap<Integer, Restaurants> list = new HashMap<>();
    protected static int id=0;

    Restaurants(String name, String Address, double dis){
        this.name = name;
        this.uid = ++id;
        this.discountonBill=dis;
        this.Address = Address;
        this.orders =0;
        this.Rewards = 0;
        list.put(this.uid, this);


    }

    Food getfood(int n){
        return foods.get(n);
    }

    public static void choseRest() throws IOException{
        showList();
        int n = Reader.nextInt();
        list.get(n).excequeries();
    }

    void HardCode(String name, double price, double dis, String cat, int qty){
        Food temp = new Food(name, price, dis, cat,qty);
        foods.put(temp.getId(),temp);
    }

    void addFood() throws IOException {
        System.out.println("Enter the name of the dish");
        String name = Reader.next();
        System.out.println("item price:");
        double price = Reader.nextDouble();
        System.out.println("item quantity:");
        int qty = Reader.nextInt();
        System.out.println("item Category:");
        String cat = Reader.next();
        System.out.println("Offer:");
        double dis = Reader.nextDouble();
        Food temp = new Food(name, price, dis, cat,qty);
        foods.put(temp.getId(),temp);
        System.out.println(temp);


    }


    void addDiscount(double i){
        discountonBill = i/100;
    }
    double getDiscountonBill(){
        return discountonBill;
    }

    void showFood(){
        for(Integer h:foods.keySet()){
            if(foods.get(h).isAvailable(1))

            System.out.println(foods.get(h));
        }
    }

    static void printDetails(int n){
        System.out.println(list.get(n).name+" ,"+list.get(n).Address+" , Total orders :"+list.get(n).orders);
    }


    void printRewards(){
        System.out.println("Reward Points: " +Rewards);
    }

    void editFood() throws IOException{
        showFood();
        System.out.println("Chose item by code");

        int n = Reader.nextInt();
        System.out.println("Chose an attribute to edit");
        System.out.println("\t 1) Name");
        System.out.println("\t 2) Price");
        System.out.println("\t 3) Quantity");
        System.out.println("\t 4) category");
        System.out.println("\t 5) Offer");
        int n2 = Reader.nextInt();
        switch (n2){
            case 1:
                System.out.println("Enter new name:");
                foods.get(n).setName(Reader.next());

                break;
            case 2:
                System.out.println("Enter new Price");
                foods.get(n).setPrice(Reader.nextDouble());
                break;
            case 3:
                System.out.println("Enter new Quantity");
                foods.get(n).setQty(Reader.nextInt());
                break;
            case 4:
                System.out.println("Enter new Category");
                foods.get(n).setCategory(Reader.next());
                break;
            case 5:
                System.out.println("Enter offer");
                foods.get(n).setDiscount(Reader.nextDouble());
                break;

        }
        System.out.println(foods.get(n));

    }
    void excequeries() throws IOException{
        showQueries();
        int n =Reader.nextInt();
        switch (n){
            case 1:
                addFood();
                excequeries();
                break;
            case 2:
                editFood();
                excequeries();
                break;
            case 3:
                printRewards();
                excequeries();
                break;
            case 4:

                return;

        }

    }

    static  void enterasRest() throws IOException{
        showList();
        int n = Reader.nextInt();
        list.get(n).excequeries();


    }

    @Override
    public String toString() {
        return uid+") "+name;
    }

    @Override
    public void showQueries() throws IOException{
        System.out.println("Welcome "+this.name);
        System.out.println("\t 1) Add Item");
        System.out.println("\t 2) Edit item");
        System.out.println("\t 3) Print Rewards");
        System.out.println("\t 4) Exit ");


        //System.out.println("\t 5) Discount on bill value");
    }

    int getans() throws IOException{
        int n=Reader.nextInt();
        return n;
    }

    @Override
    public void getRewards() {
        System.out.println("Reward points = "+Rewards);
    }

    @Override
    public void setRewardPoints(User c, double amount) throws IOException {
            c.setRewardPoints(this, (double)(amount/100)*5);

            Rewards += ((amount*5)/100);
            orders++;
    }

    public static void showList() throws IOException{
        System.out.println("Chose Restaurant:");
        for(Integer i:list.keySet()){
            System.out.println(list.get(i));
        }


    }
}

class Fastfood extends Restaurants{
    Fastfood(String name, String Address, double dis){
        super(name, Address, dis );
    }

    @Override
    public String toString() {
        return super.toString() +" (Fast Food)";
    }
    @Override
    public void showQueries() {
        System.out.println("Welcome "+this.name);
        System.out.println("\t 1) Add Item");
        System.out.println("\t 2) Edit item");
        System.out.println("\t 3) Print Rewards");
        System.out.println("\t 4) Discount on bill value");
        System.out.println("\t 5) Exit ");
    }
    @Override
    void excequeries() throws IOException{
        showQueries();
        int n =Reader.nextInt();
        switch (n){
            case 1:
                addFood();
                excequeries();
                break;
            case 2:
                editFood();
                excequeries();
                break;
            case 3:
                printRewards();
                excequeries();
                break;
            case 4:
                System.out.println("Enter the new Discount value");
                addDiscount((double)Reader.nextInt());
                excequeries();
                break;
            case 5:

                return;

        }

    }
}
class Authentic extends Restaurants{
    Authentic(String name, String Address, double dis){
        super(name, Address, dis );
    }

    @Override
    public void showQueries() {
        System.out.println("Welcome "+this.name);
        System.out.println("\t 1) Add Item");
        System.out.println("\t 2) Edit item");
        System.out.println("\t 3) Print Rewards");
        System.out.println("\t 4) Discount on bill value");
        System.out.println("\t 5) Exit ");
    }

    @Override
    void excequeries() throws IOException{
        showQueries();
        int n =Reader.nextInt();
        switch (n){
            case 1:
                addFood();
                excequeries();
                break;
            case 2:
                editFood();
                excequeries();
                break;
            case 3:
                printRewards();
                excequeries();
                break;
            case 4:
                System.out.println("Enter the new Discount value");
                addDiscount((double)Reader.nextInt());
                excequeries();
                break;
            case 5:

                return;

        }

    }

    @Override
    public String toString() {
        return super.toString()+ " (Authentic)";
    }
}
