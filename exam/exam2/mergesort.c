#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#define     SIZE    1000000

/* Pre-condition:  start, middle and end are valid indexes to the array values,
 *                 with middle >= start and middle <= end. Also, the values from
 *                 index start to middle are sorted AND the values from middle+1
 *                 to end are sorted.
 * Post-condition: The values in the array starting from index start to
 *                 index end will be in non-decreasing sorted order.*/
void Merge(int values[], int start, int middle, int end) {
    //printf("merge %d, %d, %d\n", start, middle, end);
    int *temp, i, length, count1, count2, mc;

    // Allocate the proper amount of space for our auxiliary array.
    length = end - start + 1;
    temp = (int*)calloc(length, sizeof(int));

    // These will be our indexes into our two sorted lists.
    count1 = start;
    count2 = middle;

    // Keeps track of our index into our auxiliary array.
    mc = 0;

    /* Here we copy values into our auxiliary array, so long as there are
     * numbers from both lists to copy.*/
    while ((count1 < middle) || (count2 <= end)) {
        /* Next value to copy comes from list one - make sure list
         * one isn't exhausted yet. Also make sure we don't access index
         * count2 if we aren't supposed to.*/
        if (count2 > end || (count1 < middle && values[count1] < values[count2])) {
            temp[mc] = values[count1];
            count1++;
            mc++;
        }
        // We copy the next value from list two.
        else {
            temp[mc] = values[count2];
            count2++;
            mc++;
        }
    }

    // Copy back all of our values into the original array.
    for (i=start; i<=end; i++)
        values[i] = temp[i - start];

    // Don't need this space any more!
    free(temp);
}

/* Pre-condition: start and end are valid indexes to the array values.
 * Post-condition: The values in the array starting from index start to
                 index end will be in non-decreasing sorted order.*/
void MergeSort(int values[], int start, int end) {
    int mid;

    // Check if our sorting range is more than one element.
    if (start < end) {
        mid = (start+end)/2;

        // Sort the first half of the values.
        MergeSort(values, start, mid);
        // Sort the last half of the values.
        MergeSort(values, mid+1, end);
        // Put it all together.
        Merge(values, start, mid+1, end);
    }
}

void Print_Array(int values[], int length);
void Fill_Array(int values[], int length, int max);
int Is_Sorted(int values[], int length);
int main() {
    int *val;
    val = (int*)(malloc(sizeof(int)*SIZE));
    srand(time(NULL));

    // Let's look at the random array values.
    Fill_Array(val, SIZE, 100000);

    // And now, the sorted ones.
    MergeSort(val, 0, SIZE-1);
    Print_Array(val, SIZE);

    // Check if it's sorted.
    if (Is_Sorted(val, SIZE))
        printf("Sorted correctly.\n");

    free(val);
    system("PAUSE");

    return 0;
}

/* Pre-condition:  length is the length of the array values.
 * Post-condition: all the numbers stored in values will be printed out,
                   from the values stored in index 0 to index length-1.*/
void Print_Array(int values[], int length) {
    int i;
    for (i=0; i<length; i++) printf("%d ", values[i]);
    printf("\n");
}

/* Pre-condition: length is the length of the array values and max<32767.
 * Post-condition: the array values will be initialized with random values
 *                 in between 1 and max. */
void Fill_Array(int values[], int length, int max) {
    int i;
    for (i=0; i<length; i++)
        values[i] = (rand()%max) + 1;
}

/* Pre-condition: values is of length length.
 * Post-condition: Returns 1, iff values is in non-decreasing order. */
int Is_Sorted(int values[], int length) {
    int i;
    // Return false if any adjacent pair is out of order.
    for (i=0; i<length-1; i++)
        if (values[i] > values[i+1])
            return 0;
    return 1;
}

