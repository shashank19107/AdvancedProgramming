 class Company {
    static double value=0;
    static double deleveryCahrge=0;
    Company(){
        value =0;
        deleveryCahrge=0;
    }

    static void payValue(double amount){
        value+= (0.01*amount);
    }

    static void payDeleveryCharge(double amount){
        deleveryCahrge+=amount;
    }

     @Override
     public String toString() {
         System.out.println("Total delivery charge collected - INR "+deleveryCahrge);
         return "Total company Balance - INR "+value+"/-";
     }
 }
