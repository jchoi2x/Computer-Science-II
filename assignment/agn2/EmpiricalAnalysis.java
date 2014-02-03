

import java.io.*;
import java.util.*;

public class EmpiricalAnalysis {

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
	
    public static int[] getRandArray(int size, int range){
        int[] array = new int[size];
        for ( int i = 0 ; i < size ; i++ ){
            array[i] = (int)(Math.random()*(range*2+1))-range;
        }
        return array ; 
    }
	public static void readIn(String filename){
	    try{
            Scanner scan = new Scanner(new FileReader(filename)); // Read from input stream
            String[] testCase = scan.nextLine().split("\\s+");
            for ( int i = 0 ;i < testCase.length ; i++ ){
                System.out.print(" "+testCase[i]);
            }
            
            //return testCase ; 
	    }catch(IOException ex){
            System.out.println("ERROR IOException");
	    }
	}
	
	public static void main(String [] args) {

		//int N = 1000;
		//int numRuns = 50;

		// create and populate array with random ints on range [-1000, 1000]
        //int[] array = getRandArray(N,1000);
		readIn("infile.txt");
		// start timer
		final long start = System.nanoTime();

        
		// run function: mcss_cubed(array), mcss_squared(array), or mcss_linear(array)
		//for (int i = 0; i < numRuns; i++)
		//	mcss_squared(array);

		// end timer
	//	final long end = System.nanoTime();

		// convert nanoseconds to ms and divide by numRuns to get average runtime
		// for a single function call
	//	System.out.println("Runtime: " + (((end - start) / 1000000.0) / numRuns) + "ms");
	}
}
