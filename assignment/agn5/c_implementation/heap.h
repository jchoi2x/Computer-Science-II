#ifndef HEAP
#define HEAP

struct heap{
    int size ;
    int * h ;
};
struct heap * initHeap();
/* load <listOfIntegers>
 * Loads list into a heap array and returns it*/
//int * load(void);
/* print()
 * Prints last loaded heap list using load() */
//int * print(void);
/* build-Heap()
 * Constructs heap from current array using bottoms-up (pg229)
 * Guarateed to be called before delete-max, insert, or heapSort call */
//int * buildHeap();
/* delete-max
 * Removes the max value from heap using algorithm on pg231*/
//int * deleteMax();
/* insert
 * Inserts argument into heap using algorithm on page 230*/
//int * insert(int * heap, int i);
/* heapSort
 * Sorts values in heap using algorithm on page 231-232*/
//int * heapSort(int * heap);

#endif
