#include <stdio.h>
#include <stdlib.h>
#include "queue.h"

// Pre-condition: qPtr points to a valid struct queue.
// Post-condition: The struct that qPtr points to will be set up to represent an
//                 empty queue.
void init(struct queue* qPtr) {

     // The front index is 0, as is the number of elements.
     qPtr->elements = (int*)malloc(sizeof(int)*INIT_SIZE);
     qPtr->front = 0;
     qPtr->numElements = 0;
     qPtr->queueSize = INIT_SIZE;
}

// Pre-condition: qPtr points to a valid struct queue and val is the value to
//                enqueue into the queue pointed to by qPtr.
// Post-condition: If the operation is successful, 1 will be returned, otherwise
//                 no change will be made to the queue and 0 will be returned.

// Note: Right now, I don't know how to detect that the realloc failed, so 0
//       does not get returned.

int enqueue(struct queue* qPtr, int val) {

    int i;

    // Regular case where our queue isn't full.
    if (qPtr->numElements != qPtr->queueSize) {

        // Enqueue the current element. Note the use of mod for the wraparound
        // case. Edit the number of elements.
        qPtr->elements[(qPtr->front+qPtr->numElements)%qPtr->queueSize] = val;
        (qPtr->numElements)++;

        // Signifies a successful operation.
        return 1;
    }

    // Case where the queue is full, we must find more space before we enqueue.
    else {

         // Allocate more space!
         if (realloc(qPtr->elements, (qPtr->queueSize)*sizeof(int)*2)){

             // Copy all of the items that are stored "before" front and copy them
             // into their new correct locations.
             for (i=0; i<=qPtr->front-1; i++)
                 qPtr->elements[i+qPtr->queueSize] = qPtr->elements[i];

             // Enqueue the new item, now that there is space. We are guaranteed that
             // no wraparound is necessary here.
             qPtr->elements[i+qPtr->queueSize] = val;

             // More bookkeeping: The size of the queue as doubled and the number of
             // elements has increased by one.
             (qPtr->queueSize) *= 2;
             (qPtr->numElements)++;
         }
         return 1;
    }

}

// Pre-condition: qPtr points to a valid struct queue.
// Post-condition: If the queue pointed to by qPtr is non-empty, then the value
//                 at the front of the queue is deleted from the queue and
//                 returned. Otherwise, -1 is returned to signify that the queue
//                 was already empty when the dequeue was attempted.
int dequeue(struct queue* qPtr) {

    int retval;

    // Empty case.
    if (qPtr->numElements == 0)
        return QEMPTY;

    // Store the value that should be returned.
    retval = qPtr->elements[qPtr->front];

    // Adjust the index to the front of the queue accordingly.
    qPtr->front = (qPtr->front + 1)% qPtr->queueSize;

    // We have one fewer element in the queue.
    (qPtr->numElements)--;

    // Return the dequeued element.
    return retval;
}

// Pre-condition: qPtr points to a valid struct queue.
// Post-condition: returns true iff the queue pointed to by pPtr is empty.
int queue_empty(struct queue* qPtr) {
    return qPtr->numElements == 0;
}

// Pre-condition: qPtr points to a valid struct queue.
// Post-condition: if the queue pointed to by qPtr is non-empty, the value
//                 stored at the front of the queue is returned. Otherwise,
//                 -1 is returned to signify that this queue is empty.
int front(struct queue* qPtr) {
    if (qPtr->numElements != 0)
        return qPtr->elements[qPtr->front];
    else
        return QEMPTY; }
