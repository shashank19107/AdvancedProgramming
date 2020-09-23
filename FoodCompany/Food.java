public class Food {
    private int Uid;
    private static int Id=0;
    private double discount;
    private String name;
    private double price;
    private String Category;
    private int Qty;

    Food(String name, double price, double dis, String cat, int qty){
        Uid = ++Id;
        this.name = name;
        this.price = price;
        this.discount = dis/100;
        this.Category = cat;
        this.Qty = qty;
    }
    int getId(){
        return Uid;
    }
    String getName(){
        return name;
    }
    double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    void setName(String n){
        name = n;

    }
    void setPrice(double c){
        price = c;

    }
    void setCategory(String ne){
        Category = ne;
    }
    void setQty(int nq){
        Qty = nq;
    }

    @Override
    public String toString() {
        return Uid+" "+name+" "+price+" "+Qty+" "+(discount*100)+"% off "+Category;
    }

    double getPrice(int q){
        double cost = 0;
        cost += q*price;
        cost -= (discount*cost);

        return cost;
    }

    int GetQty(){
        return Qty;

    }
    void reduceQty(int q){
        Qty -= q;
    }

    boolean isAvailable(int q){
        return (q<=Qty);
    }




}
