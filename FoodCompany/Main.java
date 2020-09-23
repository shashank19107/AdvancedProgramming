import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //System.out.println("HEllo World");
        Company Zotato = new Company();
        Restaurants Shah = new Authentic("Taj Mahal", "Delhi", 0);
        Restaurants wang = new Fastfood("Wazai", "Pune",2);
        Restaurants ravi = new Restaurants("Roshan's", "Kashmir", 10);
        Restaurants ravi3 = new Restaurants("Thai Chinese", "China", 10);
        Restaurants ravi4 = new Restaurants("Paradise", "heaven", 10);
        Customer ram = new Elite("Rohan", "Knanpur");
        Customer Sam = new Elite("Sameer", "W DC");
        Customer Tim = new Special("Tina", "Jaipur");
        Customer Adi = new Customer("Aditya ", "Ghar pe");
        Customer sho = new Customer("Yash ", "Sainik Farms");
        Shah.HardCode("Momos", 40, 5, "Starter",30);
        Shah.HardCode("Manchurien", 100, 0, "MainCourse", 15);
        Shah.HardCode("Shahi Paneer", 90, 10, "MainCourse", 12);
        ravi.HardCode("IceCream", 45, 0, "Dessert", 5);
        //Shah.showFood();




        Reader.init(System.in);
        boolean fl = true;
        while(fl) {
            System.out.println("Welcome to Zotato");
            System.out.println("\t 1) Enter as Restaurant Owner");
            System.out.println("\t 2) Enter as Customer");
            System.out.println("\t 3) check user details");
            System.out.println("\t 4) Company Account Details");
            System.out.println("\t 5) Exit");
            int z = Reader.nextInt();
            switch (z) {
                case 1:
                    Restaurants.choseRest();

                    break;
                case 2:
                    Customer.choseCus();
                    break;
                case 3:
                    System.out.println("1) Customer List");
                    System.out.println("2) Restaurant List");
                    int n = Reader.nextInt();
                    if(n==1){
                        Customer.showList();
                        int n_ = Reader.nextInt();
                        Customer.printDetails(n_);
                    }
                    else{
                        Restaurants.showList();
                        int n_ = Reader.nextInt();
                        Restaurants.printDetails(n_);
                    }

                    break;
                case 4:
                    System.out.println(Zotato);
                    break;
                case 5:
                    fl = false;
                    break;
                default:
                    System.out.println("Enter a valid value");

            }
        }
    }
}
