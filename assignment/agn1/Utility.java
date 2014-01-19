import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Utility {

	public Matrix matrix ;
	public Utility(String filename){
		this.readIn(filename);
	}

	public void readIn(String filename){
		try{
			Scanner scan = new Scanner(new FileReader(filename)); // Read from input stream
			String[] aLine ;

			Boolean firstRow = true;
			while ( scan.hasNextLine() ){
				aLine = scan.nextLine().split("\\s+"); // Read whole line and split
				if ( firstRow ) {
					matrix = new Matrix(aLine.length);
					firstRow = false;
				}
				for ( String s : aLine ){
					matrix.addNext(Integer.parseInt(s));
				}
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
		int[][] adjMatrix = {{0,0,1,0,0,0,0,0},
				             {0,0,1,0,0,0,0,0},
				             {0,0,0,0,1,0,0,0},
		                     {0,0,0,0,0,0,0,1},
	                         {0,0,0,0,0,0,1,1},
		                     {0,1,0,0,0,0,0,0},
			                 {0,0,1,0,0,0,0,0},
	                         {0,0,0,0,0,0,0,0}};
		Matrix m = new Matrix(adjMatrix);
		//m.depthFirst(0);
		m.breadthFirst(0);

		/**
		 * a. Pick Start Vertex V1, and insert to queue
		 * 1. Go to row V1, and locate column # with a 1
		 *      a. If col # is visited find another
		 *      b.
		 */
	}

}
