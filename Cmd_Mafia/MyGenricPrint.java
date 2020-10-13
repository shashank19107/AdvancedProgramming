import java.util.ArrayList;

public class MyGenricPrint<T>{
    MyGenricPrint(ArrayList<T> list, String aa){
        System.out.print("The "+aa+" were: ");
        int lp =0;
        for( lp =0; lp<list.size()-2;lp++){
            System.out.print(list.get(lp)+", ");
        }
        for (;lp<list.size()-1;lp++){
            System.out.print(list.get(lp)+" and ");
        }
        for (;lp<list.size();lp++){
            System.out.println(list.get(lp)+".");
        }
//        for(T jj:list){
//            System.out.print(jj+",");
//        }
//        System.out.println();
    }

}
