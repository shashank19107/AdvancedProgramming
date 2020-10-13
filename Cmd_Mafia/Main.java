import java.io.IOException;
import java.util.Random;

public class Main {
    public static void show() {
        System.out.println("Choose a Character");
        System.out.println("\t1)Mafia");
        System.out.println("\t2)Detective");
        System.out.println("\t3)Healer");
        System.out.println("\t4)Commoner");
        System.out.println("\t5)Assign Randomly");
    }
    public static void main(String[] args) {
        Reader.init(System.in);
        System.out.println("Welcome to Mafia");
        System.out.println("Enter Number of players:");
        int n=0;
        while(true){
            try{
                n = Reader.nextInt();
                if(n>=6)
                break;
                else
                    throw new NumberFormatException();
            }
            catch (NumberFormatException zz){
                System.out.println("Please enter an Integer >=6");
            }
            catch (IOException e){
                System.out.println("IOException Occurred. Please enter again");
            }
        }
        //show();
        int user=0;
        while(user==0){
            try{
                show();
                user= Reader.nextInt();
                if(user<1 || user>5)
                    throw new NumberFormatException();
            }
            catch (NumberFormatException zz){
                System.out.println("Please enter an Integer from 1 to 5..");
            }
            catch (IOException e){
                System.out.println("IOException Occurred. Please enter again");
            }
        }
        if(user == 5){
            user = Getrand.randInt(user-1);
        }

        Gameplay game = new Gameplay(n,user);
        game.conductRound();


    }


}

abstract class Getrand{
    static Random randmise = new Random();
    static int randInt(int n){
        return randmise.nextInt(n)+1;
    }

    static int[] makerand(int arr[]){
        for(int i =0;i<arr.length;i++){
            int sw = randmise.nextInt(arr.length);
            if(i==sw){
                continue;
            }
            arr[i]+=arr[sw];
            arr[sw]=arr[i]-arr[sw];
            arr[i]-=arr[sw];
        }
        return arr;
    }
}