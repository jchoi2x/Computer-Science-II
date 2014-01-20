import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Utility {

	public Matrix matrix ;
	public int numGraphs ; 
	public Utility(String filename){
		this.readIn(filename);
		
	}

	public void readIn(String filename){
		try{
			Scanner scan = new Scanner(new FileReader(filename)); // Read from input stream
			String[] aLine ;

			Boolean firstRow = true;
			
			if (scan.hasNextLine()){
			    this.numGraphs = Integer.parseInt(scan.nextLine());    
			}
	        for ( int j = 0 ; j < this.numGraphs ; j++ ){
			    int firstVertex = Integer.parseInt(scan.nextLine());
			    int n  = Integer.parseInt(scan.nextLine());
    			this.matrix = new Matrix(n);
    			for ( int k = 0 ; k < n ; k++ ){
    				aLine = scan.nextLine().split("\\s+"); // Read whole line and split
    			
    				for ( String s : scan.nextLine().split("\\s+") ){
    					matrix.addNext(Integer.parseInt(s));
    				}
    			}
    			matrix.printMatrix();
	        }
		}catch(IOException ex) {
			System.out.println("ERROR IOException");
		}
	}

	public void printMatrix(){
		matrix.printMatrix();
	}
	public Matrix getMatrix(){
		return this.matrix;
	}


	public static void main(String[] args){
		Utility util = new Utility("infile.txt");
		Matrix m = util.getMatrix();
		
		//m.depthFirst(0);
		

		/**
		 * a. Pick Start Vertex V1, and insert to queue
		 * 1. Go to row V1, and locate column # with a 1
		 *      a. If col # is visited find another
		 *      b.
		 */
	}

}
