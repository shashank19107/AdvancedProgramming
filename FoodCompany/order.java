import java.io.IOException;
import java.util.ArrayList;

public class order {
    private Customer cust;
    private Restaurants rest;
    private ArrayList<cart> list = new ArrayList<>();
    private double totalcost;


    order(Customer c, Restaurants r){
        cust = c;
        rest = r;
        totalcost = 0;


    }

    @Override
    public String toString() {
        return "Order for Rupees "+totalcost +" from Restaurant "+rest;
    }

    void cartadd(Food f, int qt){
        totalcost += f.getPrice(qt);
        list.add(new cart(f, qt));
    }
    void showCart(){
        for(cart i : list){
            System.out.println(i);
        }
    }
    void checkout() throws IOException{
        System.out.println("Items in cart");
        showCart();

        totalcost -= rest.getDiscountonBill()*totalcost;
        totalcost=totalcost+cust.getdelCharge();
        totalcost -= cust.getDiscount(totalcost);
        if(totalcost> cust.getBalance()){
            System.out.println("Empty cart items to proceed");
            return;
        }

        System.out.println("delivery Charge - "+cust.getdelCharge()+"/-");
        Company.payDeleveryCharge(cust.getdelCharge());

        Company.payValue(totalcost);
        cust.updateWallet(totalcost);
        rest.setRewardPoints(cust, totalcost);
        cust.setRecorders(this.toString());

        System.out.println("Total order value - INR "+ totalcost);


    }
    void  showMenu() throws IOException {
        System.out.println("Choose item by code: ");
        rest.showFood();
        int n = Reader.nextInt();
        System.out.println("Enter Quantity");
        int q = Reader.nextInt();
        cartadd(rest.getfood(n), q);
        System.out.println("Item added in cart");
        showCart();
        System.out.println("1) Proceed to Checkout");
        int n2 = Reader.nextInt();
        checkout();



    }
}

class cart{
    private int qty;
    private  String name;
    private  double price;
    private  int code;
    private  double dis;
    cart(Food x, int Qty){
        qty =  Qty;
        name = x.getName();
        code = x.getId();
        price = x.getPrice(Qty);
        dis = x.getDiscount();

    }

    @Override
    public String toString() {
        return code +" - "+name+" - "+price+" - "+qty+" - "+dis;
    }
}