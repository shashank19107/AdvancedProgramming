import java.util.ArrayList;
import java.util.HashMap;

public abstract class Player {
    private final int id;
    private String  name;
    private int hp;
    Player(int id,int hp){
        this.id = id;
        this.hp = hp;
        this.name = "Player"+id;

    }

    int getHp(){
        return hp;
    }
    int getId(){
        return id;

    }

    String getName(){
        return name;
    }
    void setHp(int i){
        hp-=i;
    }
    void setnohp(){
        hp=0;
    }
    void heal(){
        hp+=500;
    }
      int choose(){
        return Gameplay.GetRandPlayer().getId();
      }
    abstract void killit();

    @Override
    public String toString() {
        return this.name;
    }
    void setName(){
        this.name+="[User]";
    }
}

class Mafia extends Player{
    //Make this hashmap private
    static  HashMap<Integer, Mafia> mlist = new HashMap<>();
    static private int totalhp=0;
    static private int nomafias =0;
    Mafia(int i){
        super(i, 2500);
        mlist.put(this.getId(), this);
        totalhp+=2500;
        nomafias++;

    }

    @Override
    void killit() {
        if(this.getHp()!=0){
            nomafias--;
            totalhp-=this.getHp();
        }
        mlist.remove(this.getId());
    }

    static void nohp(){
        for (Integer i:mlist.keySet()) {
            mlist.get(i).setnohp();
        }
        totalhp=0;

    }
    static int numList(){
        return mlist.size();
    }

    static int getTotalhp(){
        return totalhp;
    }

    static int getNomafias(){
        return nomafias;
    }


    static int chose(){
        Player val = Gameplay.GetRandPlayer();
        if(val.getClass()==Mafia.class){
            return chose();
        }
        else return val.getId();
    }

    static void balance(int i){
        int cut = i/nomafias;
        for (Integer h: mlist.keySet()
             ) {
            if(mlist.get(h).getHp()!=0){
                if(cut>=mlist.get(h).getHp()){
                    nomafias--;
                    totalhp-=mlist.get(h).getHp();
                    int newcut = cut-mlist.get(h).getHp();
                    mlist.get(h).setnohp();
                    balance(newcut);

                }
                else{
                    totalhp-=cut;
                    mlist.get(h).setHp(cut);
                }
            }

        }
    }

}

class common extends Player{
    common(int i){
        super(i, 1000);
    }



    @Override
    void killit() {

    }
}

class detective extends Player{
    private static HashMap<Integer,detective> dlist = new HashMap<>();
    detective(int i){
        super(i, 800);
        dlist.put(this.getId(),this);
    }
    static int chose(){
        Player val = Gameplay.GetRandPlayer();
        if(val.getClass()==detective.class){
            return chose();
        }
        else return val.getId();
    }

    static boolean Nodetective(){
        if(dlist.size()==0){
            return true;
        }
        return false;
    }

    @Override
    void killit() {
        dlist.remove(this.getId());
    }
}

class healer extends Player{
    private static HashMap<Integer, healer> hlist = new HashMap<>();
    healer(int i){
        super(i, 800);
        hlist.put(this.getId(), this);
    }



    @Override
    void killit() {
        hlist.remove(this.getId());
    }

    static int chose(){
        Player val = Gameplay.GetRandPlayer();

        return val.getId();
    }

}