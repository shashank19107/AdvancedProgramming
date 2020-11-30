package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class tree{
    static HashSet<Integer> set = new HashSet<>();                  // Flyweight Design Pattern
    static tree root = null;
    static private int total_tree_hieght = 0;
    static int test =0;
    private int value;
    private int height;
    private tree[] children;
    tree(int value){
        this.value = value;
        children = new tree[Getrand.randInt(5)];
        for(int i=0; i<children.length; i++){
            children[i] = null;
        }

    }
    public static tree Seq_add(tree root, int value){
        if(root == null){
            tree node = new tree(value);
            //node.height = root.height+1;
            root = node;
           // System.out.println();
            return node;
        }
        int next = Getrand.randInt(root.children.length)-1;

        root.children[next] =  Seq_add(root.children[next],value);
        root.children[next].height= root.height+1;
        if(total_tree_hieght< root.height+1){
            total_tree_hieght = root.height+1;
        }
        return root;

    }
    public static void Seq_add(int value){
        if(root==null){
            root = new tree(value);
            root.height = 1;
            return;
        }
        Seq_add(root, value);
    }
    public void print(tree root){
        if(root == null){
            return;
        }
        System.out.print(root.value+" ");
        for(int i=0; i<root.children.length; i++){
            print(root.children[i]);
        }

    }

    public int getValue(){
        return this.value;
    }
    public int getHeight(){
        return this.height;
    }
    public tree[] getChildren() {
        return children;
    }
    public static int getTotal_tree_hieght(){
        return total_tree_hieght;
    }
}

abstract class Seq_Find{
    static void find(tree root, int aim){
        if(root == null){
            return;
        }
        if(root.getValue() == aim){
            System.out.println(root.getValue()+" Found on hieght "+ root.getHeight());
        }
        for(int i=0; i<root.getChildren().length; i++){
            find(root.getChildren()[i], aim);
        }

    }
}

class ExplicitThread implements Runnable{

    tree root;
    static HashSet<Integer> queries;
    static Thread ts[];
    @Override
    public void run() {
        if(root==null){
            return;
        }
        if(queries.contains(root.getValue())){
            //Print commented for the sake of clarity of output
            //System.out.println(root.getValue()+" found on hieght "+ root.getHeight());
        }
        if(true && root.getHeight()>=3){
            seq_find(root);
            return;
        }


//        try {
//            for (Thread tt : ts) {
//                tt.join();
//            }
//        }
//        catch (InterruptedException e){}


    }
    public static void initialise(int n, tree root, HashSet<Integer> query) throws InterruptedException{
         //pool = Executors.newFixedThreadPool(n);
         queries = query;

         ExplicitThread main = new ExplicitThread();

         main.root = root;
        ts = new Thread[root.getChildren().length];
        for(int i =0; i<root.getChildren().length ;i++){
            ExplicitThread x1 = new ExplicitThread();
            x1.root = root.getChildren()[i];
//            Thread t1 = new Thread(x1);
//            t1.start();
//            threads.add(t1);

            ts[i] = new Thread(x1);
            ts[i].start();
        }

         for (Thread tt : ts) {
                tt.join();
            }
//         Thread mainThread  = new Thread(main);
//         mainThread.start();
//         mainThread.join();



    }
    private void seq_find(tree root){
        if(root == null){
            return;
        }
        if(queries.contains(root.getValue()) ){
            //Print commented for the sake of output clearity
           // System.out.println(root.getValue()+" Found on hieght "+ root.getHeight());
        }
        for(int i=0; i<root.getChildren().length; i++){
            seq_find(root.getChildren()[i]);
        }
    }

}
class ForkJoin extends RecursiveAction{
    tree root;

    static HashSet<Integer> queries;
    @Override
    protected void compute() {
        //System.out.println("Compute gets");
        if(root == null){
            return;
        }
        if(queries.contains(root.getValue())){
            System.out.println(root.getValue()+" Value found at height "+ root.getHeight());
            //return;
        }
        if(root.getHeight()>=3){
            seq_find(root);
            return;
        }
        ForkJoin threads[] = new ForkJoin[root.getChildren().length];
        for(int i=0; i<root.getChildren().length;i++){
            threads[i] = new ForkJoin();
            threads[i].root = root.getChildren()[i];


        }
        threads[0].compute();
        for(int i=1; i<root.getChildren().length;i++){
            if(i%2==0)
            threads[i].fork();
            else
                threads[i].compute();
        }
        ForkJoin.helpQuiesce();
        return;
    }
    public static void initialise (int n, tree root, HashSet<Integer> query) throws InterruptedException{
        ForkJoinPool pool = new ForkJoinPool(n);
        ForkJoin find = new ForkJoin();
        find.root = root;
        queries = query;
        pool.invoke(find);


    }
    private void seq_find(tree root){
        if(root == null){
            return;
        }
        if(queries.contains(root.getValue()) ){
            System.out.println(root.getValue()+" Value found on hieght "+ root.getHeight());
        }
        for(int i=0; i<root.getChildren().length; i++){
            seq_find(root.getChildren()[i]);
        }
    }
}
public class Assignment4 {

    public static void main(String args[]) throws IOException, InterruptedException {
        //System.out.println("Hello");
        System.out.println("Enter the value of n");
        Reader.init(System.in);
        int n = Reader.nextInt();
        //HashSet<Integer> set = new HashSet<>();
        tree root = tree.root;
        for(int i =0; i<n; i++){
            int temp = Getrand.randInt(1000000);
            while(tree.set.contains(temp)){
                temp++;
            }
            tree.set.add(temp);

            root = root.Seq_add( root,temp);
        }
        root.print(root);
        System.out.println("Height of tree: "+tree.getTotal_tree_hieght());
        System.out.println("\nEnter number of queries");
        int q = Reader.nextInt();
        int[] queries = new int[q];
        for(int i =0; i<q;i++){
            System.out.println("Enter to find");
            queries[i] = Reader.nextInt();
        }
        System.out.println("Enter number of parallel cores");
        int c = Reader.nextInt();
        long startTime = System.currentTimeMillis();
        for(int i =0; i<q;i++){
            Seq_Find.find(root, queries[i]);
        }

        long end = System.currentTimeMillis();
        long Seq_time;
        System.out.println("The time taken Sequencially "+(Seq_time=end - startTime));
        HashSet<Integer> querySet = new HashSet<Integer>();
        for(int que : queries){
            querySet.add(que);
        }
        startTime = System.currentTimeMillis();
        ForkJoin.initialise(c, root, querySet);
//        for(int i =0; i<q;i++){
//            ForkJoin.initialise(c, root, queries[i]);
//        }
        end = System.currentTimeMillis();
        long forkj_time;
        System.out.println("The time taken by Fork Join pool "+(forkj_time= end - startTime));
        startTime = System.currentTimeMillis();
        ExplicitThread.initialise(c, root, querySet);
        end = System.currentTimeMillis();
        long Explicit_time;
        System.out.println("The time taken by Explicit threads "+(Explicit_time=end - startTime));
        System.out.println("ForkJoin speedUp: "+ (Seq_time-forkj_time));
        System.out.println("Explicit Threading speedup: "+ (Seq_time - Explicit_time));
        System.out.println("ForkJoin Efficiency: "+ (double)((Seq_time-forkj_time))/c);
        System.out.println("Explicit Efficiency: "+ (double)((Seq_time - Explicit_time))/c);





    }



}
/*
A Random number genrator class.
Follows  Singleton design patterns.*/

class Getrand{
    static Random randmise = new Random();
    static int randInt(int n){
        return randmise.nextInt(n)+1;
    }
    static public Random getInstace(){
        return randmise;
    }

}

/** Class for buffered reading int and double values */

//Follows Singleton design Pattern
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static public BufferedReader getInstance(){
        return reader;
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}