import java.io.IOException;

import java.util.*;

public class Gameplay {
    private int total;
    private final int dects;
    private final int mafia;
    private final int comm;
    private final int heals;
    private final static HashMap<Integer,Player> plist = new HashMap<>();
    private final static ArrayList<Mafia> mafias = new ArrayList<>();
    private final  static ArrayList<detective> detectives = new ArrayList<>();
    private final static ArrayList<common> commons = new ArrayList<>();
    private final static ArrayList<healer> healers= new ArrayList<>();
    private static String userLast;
    private static Player user;
    private static int rounds =0;
    private static int arr[];
    private static int totalPlayers=0;
    Gameplay(int n, int user){
        totalPlayers =n;
        arr = new int[n];
        for(int i =0; i<n;i++)
            arr[i]=i+1;
        //System.out.println(Arrays.toString(arr));
        arr = Getrand.makerand(arr);
        //System.out.println(Arrays.toString(arr));
        mafia = (n/5>0)?n/5:1;
        dects = mafia;
        heals = (n/10>0)?n/10:1;
        comm= n - mafia - mafia - heals;
        int curr =0;
        for(int i =0; i<mafia;i++){
            Mafia xx = new Mafia(arr[curr]);
            curr++;
            mafias.add(xx);
            plist.put(xx.getId(),xx);
        }
        for(int i =0; i<mafia;i++){
            detective xx = new detective(arr[curr]);
            curr++;
            detectives.add(xx);
            plist.put(xx.getId(),xx);
        }
        for(int i =0; i<heals;i++){
            healer xx = new healer(arr[curr]);
            curr++;
            healers.add(xx);
            plist.put(xx.getId(),xx);
        }
        while(curr<n){
            common xx = new common(arr[curr]);
            curr++;
            commons.add(xx);
            plist.put(xx.getId(),xx);
        }
        switch (user){
            case 1:
                System.out.println("You are "+mafias.get(0).getName());
                this.user = mafias.get(0);
                System.out.println("You are a Mafia.Other Mafias are"+mafias.toString());
                break;
            case 2:
                    System.out.println("You are "+detectives.get(0).toString());
                    this.user = detectives.get(0);
                    System.out.println("You are a detective. All detectives are "+detectives.toString());
                    break;
            case 3:
                System.out.println("You are "+healers.get(0).toString());
                this.user= healers.get(0);
                System.out.println("You are a healer. All healers are "+healers.toString());
                break;
            case 4:
                System.out.println("You are "+commons.get(0).getName());
                System.out.println("You are a commoner.");
                this.user = commons.get(0);
                break;
            default:

        }
         this.user.setName();

    }
    static int getUserInput(){
        int vote;
        while(true) {
            System.out.println("Chose player to vote out: ");
            try {
                vote = Reader.nextInt();
                if(!plist.containsKey(vote))
                    throw new NullPointerException();
                break;


            } catch (NumberFormatException zz) {
                System.out.println("Please enter an Integer");
                //toTest = detective.chose();
            } catch (IOException e) {
                System.out.println("IOException Occurred. Please enter again");
                //toTest = detective.chose();
            }
            catch (NullPointerException ne){
                System.out.println("Please chose a valid Player");
            }
        }
        return vote;

    }
    static int Voting(){
        int votes[] = new int[totalPlayers+1];
        for(int IDD:plist.keySet()){
            if(user!=null && IDD == user.getId()){
                votes[getUserInput()]++;
            }
            else{
                votes[GetRandPlayer().getId()]++;
            }

        }
        int max =0;
        boolean isDone = false;
        for(int jjj=1;jjj<votes.length;jjj++){
            if(votes[max]<votes[jjj]){
                max = jjj;
                isDone = true;
            }
            else if(votes[max]==votes[jjj]){
                isDone=false;
            }
        }
        if(isDone){
            return max;
        }
        System.out.println("It was a tie.... Vote again");
        return Voting();
    }
    static Player GetRandPlayer(){
        int re = Getrand.randInt(plist.size()-1);
        return Numtoplayer(re);

    }
    static Player Numtoplayer(int re){
        re = (re%plist.size());
        List<Integer> keyAsList = new ArrayList<Integer>(plist.keySet());

        return plist.get(keyAsList.get(re));
    }
    void conductRound(){
        rounds++;
        System.out.println("Round "+rounds);
        System.out.print(plist.size()+" Players remaining ");
        System.out.println(plist.toString());
//        if(user!=null) {
//            System.out.println(user.getClass());
//        }
        //System.out.println(Mafia.mlist.toString());
        int victim;
        if(user!=null && (user.getClass() != Mafia.class)){

            victim = Mafia.chose();
            System.out.println("Mafias have chosen their target.");
        }
        else if(user==null){
            victim = Mafia.chose();
            System.out.println("Mafias have chosen their target.");
        }

        else{
            while(true) {
                try {
                    System.out.println("Choose a target: ");
                    victim = Reader.nextInt();
                    if(plist.get(victim).getClass()!=Mafia.class){
                        break;
                    }
                    throw new NumberFormatException();
                } catch (NumberFormatException zz) {
                    System.out.println("Please enter a valid Non mafia Player to kill.");
                } catch (IOException e) {
                    System.out.println("IOException Occurred. Please enter again");
                }
                catch (NullPointerException ne){
                    System.out.println("Please enter a valid player.");
                }
            }

        }

        boolean vic = Kill.k(victim);
        int toTest;
        boolean voting = true;
        if(user!=null&&(user.getClass() != detective.class)){
            toTest= detective.chose();
            System.out.println("Detectives have chosen someone to test.");
        }
        else if(user==null){
            toTest= detective.chose();
            System.out.println("Detectives have chosen someone to test.");
        }
        else{
            while(true) {
                try {
                    System.out.println("Enter player to Test");
                    toTest = Reader.nextInt();
                    if(plist.get(toTest).getClass()!=detective.class){
                        break;
                    }
                    throw new NumberFormatException();

                } catch (NumberFormatException zz) {
                    System.out.println("Please enter a valid Non detective Integer.");
                    toTest = detective.chose();
                } catch (IOException e) {
                    System.out.println("IOException Occurred. Please enter again");
                    toTest = detective.chose();
                }
                catch (NullPointerException ne){
                    System.out.println("Enter a valid ID.");
                }
            }


        }
        if(detective.Nodetective()){

        }

        else if(plist.get(toTest).getClass()==Mafia.class){
            System.out.println(toTest+" was a Mafia");
            voting = false;
            System.out.println("Voting will be skiped.");
        }
        else{
            if(user!=null && user instanceof detective)
            System.out.println(toTest+" was not a mafia.");
        }

        // Healing part
        int toheal;
        if(user!=null && user.getClass() != healer.class){
            toheal = healer.chose();
            plist.get(toheal).heal();
            System.out.println("Healers have healed a player.");
        }
        else if(user == null){
            toheal = healer.chose();
            plist.get(toheal).heal();
        }
        else{
            while(true) {
                System.out.println("Chose player to heal: ");
                try {
                    toheal = Reader.nextInt();
                    if(!plist.containsKey(toheal))
                        throw new NumberFormatException();
                    break;


                } catch (NumberFormatException zz) {
                    System.out.println("Please enter a valid Player ID.");
                    //toTest = detective.chose();
                } catch (IOException e) {
                    System.out.println("IOException Occurred. Please enter again");
                    //toTest = detective.chose();
                }
                catch (NullPointerException ne){
                    System.out.println("Please chose a valid Player");
                }
            }

        }
        if(toheal==victim){
            if(vic){
                plist.get(victim).heal();
                vic = false;
            }
            else{
                plist.get(victim).heal();
            }
        }
        else{
            plist.get(toheal).heal();
        }
        if(vic){
            System.out.println(plist.get(victim).getName()+" Died.");
            if(plist.get(victim).equals(user)){
                user=null;
            }
            plist.get(victim).killit();
            plist.remove(victim);
        }
        else{
            System.out.println("No one died.");
        }
        if(!voting){
            System.out.println(plist.get(toTest).getName()+" was voted out.");
            if(plist.get(toTest).equals(user)){
                user=null;
            }
            plist.get(toTest).killit();
            plist.remove(toTest);
        }
        else{
//            int toOut=Getrand.randInt(plist.size()-1);
//            if(!plist.containsKey(toOut)){
//                System.out.println("toout is "+toOut);
//            }
            Player toOut = plist.get(Voting());

            System.out.println(toOut.getName()+" was voted out.");
            //plist.get(toOut).killit();
            if(toOut.equals(user)){
                user = null;
            }
            toOut.killit();
            plist.remove(toOut.getId());

        }
        if(Mafia.numList()==0){
            System.out.println("\t Game Over");
            System.out.println("The Mafias has lost.");
            GameOver();
            return;
        }
        else if((double)plist.size()/Mafia.numList()==2){
            System.out.println("\t Game Over");
            System.out.println("The Mafias has won.");
            GameOver();
            return;

        }
        else{
            System.out.println("-- End of Round "+rounds+" --");
            conductRound();
        }











    }

    static Player get(int id){
        return plist.get(id);
    }

    static void GameOver(){
//        System.out.println("Mafias: "+mafias.toString());
//        System.out.println("Detectives: "+detectives.toString());
//        System.out.println("Healers were "+healers.toString());
//        System.out.println("Commoners were "+commons.toString());
        MyGenricPrint<Mafia> print = new MyGenricPrint<>(mafias,"Mafias");
        MyGenricPrint<detective> print2 = new MyGenricPrint<>(detectives,"Detectives");
        MyGenricPrint<healer> print4 = new MyGenricPrint<>(healers,"Healers");
        MyGenricPrint<common> print3 = new MyGenricPrint<>(commons,"Commoners");

    }

}
