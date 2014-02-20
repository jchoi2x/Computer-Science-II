/**
 * James Choi
 * Computer-Science II - Tues/Thurs 1:30PM
 * Assignment #2
 */
import java.io.*;
import java.util.*;

public class Empirical {

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
        avg[0] = (((end - start) / 1000000.0) / numTimes)*1000000.0 ;

        //----------------------------------------------------------
	    start = System.nanoTime();
	    for ( int i = 0 ; i < numTimes ; i++ ){
	        mcss_squared(array);
	    }
	    end = System.nanoTime();
        avg[1] = (((end - start) / 1000000.0) / numTimes)*1000000.0 ;

        //----------------------------------------------------------
	    start = System.nanoTime();
	    for ( int i = 0 ; i < numTimes ; i++ ){
	        sol = mcss_linear(array);
	    }
	    end = System.nanoTime();
        avg[2] = (((end - start) / 1000000.0) / numTimes)*1000000.0 ;

        System.out.println(sol+" "+String.format("%.0f",avg[0])+" "+String.format("%.0f",avg[1])+" "+String.format("%.0f",avg[2]));
	}
	public static void main(String [] args) {
		readIn("mcss.txt");
	}
}
