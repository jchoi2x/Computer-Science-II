/**
 * James Choi
 * Computer-Science II - Tues/Thurs 1:30PM
 * Assignment #2
 */
import java.io.*;
import java.util.*;

public class EmpiricalTest {

	public static int mcss_cubed(int [] array) {
		int max = 0;

		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				int sum = 0;

				for (int k = i; k <= j; k++)
					sum += array[k];

				if (sum > max)
					max = sum;
			}
		}
		return max;
	}

	public static int mcss_squared(int [] array) {
		int max = 0;

		for (int i = 0; i < array.length; i++) {
			int sum = 0;
			for (int j = i; j < array.length; j++) {
				sum += array[j];

				if (sum > max)
					max = sum;
			}
		}
		return max;
	}

	public static int mcss_linear(int [] array) {
		int max = 0, sum = 0;

		for (int i = 0; i < array.length; i++) {
			sum += array[i];

			if (sum > max)
				max = sum;
			else if (sum < 0)
				sum = 0;
		}
		return max;
	}


	public static void readIn(String filename){
	    try{
	        Scanner scan = new Scanner(new FileReader(filename)); // Read from input stream
	        // read the first line containing number of testcases
	        int numCases = Integer.parseInt(scan.nextLine().split("\\s+")[0]);
	        int[] array ;
	        for ( int j = 0  ; j < numCases ; j++ ){
	            // make temp string array and read into it
                String[] testCase = scan.nextLine().split("\\s+");
                // Init int array
                array = new int[testCase.length-1];
                for ( int i = 1 ; i < testCase.length ; i++ ){
                    array[i-1] = Integer.parseInt(testCase[i]);
                }
                runTest(array,50);
	        }


	    }catch(IOException ex){
            System.out.println("ERROR IOException");
	    }
	}

    public static int[] getRandArray(int numElements){
        Random rand= new Random();
        int randomInt ;
        int[] array = new int[numElements];
        for ( int i = 0 ; i < numElements; i++ ){
            array[i] = (int)(Math.random()*2001)-1000;
        }
        return array ;
    }

    public static void runLinear(Integer[] inputSizeList, int numTimes){

	    long start;
	    long end ;
        System.out.println("Linear");
        for ( int j = 0 ; j < inputSizeList.length ; j++ ){
            // get a randomlist of size N
            int[] randList = getRandArray(inputSizeList[j]);

            start = System.nanoTime();
            for ( int i = 0 ; i < numTimes ; i++ ){
                mcss_cubed(randList);
            }
            end = System.nanoTime();

            System.out.println("N: "+inputSizeList[j]+"No. Runs: "+numTimes+" Time: "+(((end - start) / 1000000.0) / numTimes )) ;
        }
    }
    public static void runSquare(Integer[] inputSizeList, int numTimes){

	    long start;
	    long end ;

        System.out.println("Squared");
        for ( int j = 0 ; j < inputSizeList.length ; j++ ){
            // get a randomlist of size N
            int[] randList = getRandArray(inputSizeList[j]);

            start = System.nanoTime();
            for ( int i = 0 ; i < numTimes ; i++ ){
                mcss_cubed(randList);
            }
            end = System.nanoTime();

            System.out.println("N: "+inputSizeList[j]+"No. Runs: "+numTimes+" Time: "+(((end - start) / 1000000.0) / numTimes )) ;
        }
    }
    public static void runCube(Integer[] inputSizeList, int numTimes){
	    double[] avg = new double[3];

	    long start;
	    long end ;
        System.out.println("Cubed");
        for ( int j = 0 ; j < inputSizeList.length ; j++ ){
            // get a randomlist of size N
            int[] randList = getRandArray(inputSizeList[j]);

            start = System.nanoTime();
            for ( int i = 0 ; i < numTimes ; i++ ){
                mcss_cubed(randList);
            }
            end = System.nanoTime();

            System.out.println("N: "+inputSizeList[j]+"No. Runs: "+numTimes+" Time: "+(((end - start) / 1000000.0) / numTimes )) ;
        }
    }

	public static void runTest(int[] array, int numTimes){
	    double[] avg = new double[3];
	    long start;
	    long end ;
	    int sol = 0;

        //----------------------------------------------------------
	    start = System.nanoTime();
	    for ( int i = 0 ; i < numTimes ; i++ ){
	        mcss_cubed(array);
	    }
	    end = System.nanoTime();
        avg[0] = (((end - start) / 1000000.0) / numTimes ) ;

        //----------------------------------------------------------
	    start = System.nanoTime();
	    for ( int i = 0 ; i < numTimes ; i++ ){
	        mcss_squared(array);
	    }
	    end = System.nanoTime();
        avg[1] = (((end - start) / 1000000.0) / numTimes ) ;

        //----------------------------------------------------------
	    start = System.nanoTime();
	    for ( int i = 0 ; i < numTimes ; i++ ){
	        sol = mcss_linear(array);
	    }
	    end = System.nanoTime();
        avg[2] = (((end - start) / 1000000.0) / numTimes ) ;

        //System.out.println("\nInput Size (N):"+array.length+"\nMaximal: "+sol+"\n\t Cubed Time:"+String.format("%.0f",avg[0])+"ms"+"\n\t Squared Time:"+String.format("%.0f",avg[1])+"ms"+"\n\t Linear Time:"+String.format("%.0f",avg[2])+"ms");

        System.out.println("\nInput Size (N):"+array.length+"\nMaximal: "+sol+
                "\n\t Cubed Time:"+avg[0]+"ms"+
                "\n\t Squared Time:"+avg[1]+"ms"+
                "\n\t Linear Time:"+avg[2]+"ms");
	}
	public static void main(String [] args) {
        Scanner userIn = new Scanner(System.in);
        String yn = new String();
        yn = "y";

        Integer[] cubeN = {125,250,500,1000,2000};
        Integer[] squareN = {25000,50000,100000,200000,400000,800000};
        Integer[] linearN = {100000, 200000, 400000, 800000, 1600000, 3200000, 6400000};

        runCube(cubeN,10);
        System.out.println();
        runSquare(squareN,10);
        System.out.println();
        runLinear(linearN,10);
        System.out.println();
    }
}
