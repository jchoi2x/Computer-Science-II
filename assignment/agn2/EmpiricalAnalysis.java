// Sean Szumlanski
// COP 3503, Fall 2013

// EmpiricalAnalysis.java
// ======================
// This program contains the three implementations of the MCSS algorithm that
// we saw in class on Monday, August 26, 2013. It provides a framework for
// timing how long each algorithm takes to execute for different input sizes, to
// facilitate empirical analysis of Big-Oh runtime.


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
    /**
     *
     *  int[] cubeN   = {125,250,500,1000,2000};
     *  int[] squareN = {25000,50000,100000,200000,400000,800000};
     *  int[] linearN = {100000, 200000, 400000, 800000, 1600000, 3200000, 6400000};
     */

	public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter: ");

		int N = scan.nextInt();
		//int N = 200000;
		int numRuns = 10;


		// create and populate array with random ints on range [-1000, 1000]
		int [] array = new int[N];

		for (int i = 0; i < N; i++)
			array[i] = (int)(Math.random() * 2001) - 1000;

		// start timer
		final long start = System.nanoTime();

		// run function: mcss_cubed(array), mcss_squared(array), or mcss_linear(array)
		for (int i = 0; i < numRuns; i++){
            //mcss_cubed(array);
            //mcss_linear(array);
			mcss_cubed(array);
        }
		// end timer
		final long end = System.nanoTime();

		// convert nanoseconds to ms and divide by numRuns to get average runtime
		// for a single function call
		System.out.println((((end - start) ) / numRuns) );
	}
}
