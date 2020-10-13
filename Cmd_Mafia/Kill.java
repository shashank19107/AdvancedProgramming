public class Kill {

    static boolean k(int victim){
        Player victi = Gameplay.get(victim);

        if(victi.getHp()>=Mafia.getTotalhp()){
            victi.setHp(Mafia.getTotalhp());
            Mafia.nohp();
            return false;
        }
        Mafia.balance(victi.getHp());
        return true;
    }
}

