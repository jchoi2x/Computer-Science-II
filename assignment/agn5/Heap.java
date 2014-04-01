import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Heap {
    private int[] h;
    public Heap(String fileName){
        this.h = new int[]{-1};
        this.readIn(fileName);
    }
    public void load(String[] list){
        this.h = new int[list.length];
        this.h[0] = -1 ;
        for ( int i = 1 ; i < list.length ; i++ ) this.h[i] = Integer.parseInt(list[i]);
    }

    public int[] heapBottomsUp(){
        int n = this.h.length-1 ;
        for ( int i = this.h.length/2 ; i > 0 ; i-- ){
            int k = i ;
            int v = this.h[k] ;
            boolean heap = false ;
            while (!heap && 2*k <= n ){
                int j = 2*k ;
                if ( j < n && this.h[j] < this.h[j+1] ) j++ ;
                if ( v > this.h[j] ) heap = true ;
                else{
                    this.h[k] = this.h[j] ;
                    k = j ;
                }
            }
            this.h[k] = v ;
        }
        return this.h ;
    }

    public void delete_max(){
        // swap root with bottom leftmost
        swap(1,this.h.length-1);
        // create a new array with same elements original excluding last element
        int[] nn = new int[this.h.length-1];
        System.arraycopy(this.h, 0, nn, 0, this.h.length-1);
        this.h = nn ;
        // resort bottoms up
        heapBottomsUp();
    }
    public void readIn(String fileName){
        Scanner scan = null ;
        try{
            scan = new Scanner(new File(fileName));
        }catch(FileNotFoundException ex){
            System.out.println("File Not Found");
        }
        while (scan.hasNextLine()){
            String[] aLine = scan.nextLine().split("\\s+");
            if ( aLine[0].equals("load")) this.load(aLine);
            else if ( aLine[0].equals("print")) this.print();
            else if ( aLine[0].equals("build-heap")) this.heapBottomsUp();
            else if ( aLine[0].equals("delete-max")) this.delete_max();
            else if ( aLine[0].equals("insert")){

            }
            else if ( aLine[0].equals("heap-sort")){

            }
            else{
                System.out.println("Error: Input file Malformed");
            }
        }
    }
    public void print(){
        if ( this.h.length > 1 )
            for ( int i = 1 ; i < this.h.length ; i++ ) System.out.print(h[i]+" ");
        else System.out.print("( Empty )");
        System.out.println();
    }
    public int[] getHeap(){
        return this.h ;
    }
    public void swap(int a, int b){
        this.h[0] = this.h[a];
        this.h[a] = this.h[b];
        this.h[b] = this.h[0];
        this.h[0] = -1 ;
    }

    public static void main(String[] args){
        int[] h = {-1,2,9,7,6,5,8};
        Heap heap = new Heap("heapOps.txt");
    }
}
