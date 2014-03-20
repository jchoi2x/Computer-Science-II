#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define PRINTLIST 0
#define SIZEOFLIST 1000000

void print(int* numbers, int length);
int is_sorted(int values[], int length);



// Swaps the values pointed to by a and b.
void swap(int *a, int *b) {
     int temp = *a;
     *a = *b;
     *b = temp;
}
/* Pre-condition: low and high are valid indexes into values
 * Post-condition: Returns the partition index such that all the values
 *                 stored in vals from index low to until that index are
 *                 less or equal to the value stored there and all the values
 *                 after that index until index high are greater than that
 *                 value.*/
int partition(int* vals, int low, int high) {
    int temp;
    int i, lowpos;

    // A base case that should never really occur.
    if (low == high) {
        return low;
    }
    // Pick a random partition element and swap it into index low.
    i = low + rand()%(high-low+1);

    swap(vals+i,vals+low);
    // Store the index of the partition element.
    lowpos = low;
    // Update our low pointer.
    low++;

    // Run the partition so long as the low and high counters don't cross.
    while (low <= high) {
        // Move the low pointer until we find a value too large for this side.
        while (low <= high && vals[low] <= vals[lowpos]) low++;
        // Move the high pointer until we find a value too small for this side.
        while (high >= low && vals[high] > vals[lowpos]) high--;
        // Now that we've identified two values on the wrong side, swap them.
        if (low < high)
           swap(&vals[low], &vals[high]);
    }

    // Swap the partition element into it's correct location.
    swap(&vals[lowpos], &vals[high]);

    return high; // Return the index of the partition element.
}

/* Pre-condition: s and f are value indexes into numbers.
 * Post-condition: The values in numbers will be sorted in between indexes s
                   and f.                                                       */
void quicksort(int* numbers, int low, int high) {
    // Only have to sort if we are sorting more than one number
    if (low < high) {
        int split = partition(numbers,low,high);
        quicksort(numbers,low,split-1);
        quicksort(numbers,split+1,high);
    }
}



int main() {
    srand(time(0));
    int *list;

    // Allocate space for our list.
    list = (int*)malloc(SIZEOFLIST*sizeof(int));

    // Fill it with random values.
    int i;
    for (i=0;i<SIZEOFLIST;i++)
        list[i] = rand();

    // Print out the original values.
    if (PRINTLIST)
        print(list, SIZEOFLIST);

    // Time our sort!
    int tstart = time(0);
    quicksort(list,0,SIZEOFLIST-1);
    int tfinish = time(0);

    // And now the sorted values.
    if (PRINTLIST)
        print(list, SIZEOFLIST);

    if (is_sorted(list, SIZEOFLIST))
        printf("Sorted correctly.\n");

    // Print out how long it took.
    printf("Quicksort took  %d milliseconds.\n", tfinish-tstart);

    free(list);
    system("PAUSE");
    return 0;
}
void print(int* numbers, int length) {

    int i;
    for (i=0;i<length;i++)
        printf("%d ", numbers[i]);
    printf("\n");
}
/* Pre-condition:  values is of length length.
 * Post-condition: Returns 1, iff values is in non-decreasing order.*/
int is_sorted(int values[], int length) {

    int i;

    // Return false if any adjacent pair is out of order.
    for (i=0; i<length-1; i++)
        if (values[i] > values[i+1])
            return 0;

    return 1;
}

