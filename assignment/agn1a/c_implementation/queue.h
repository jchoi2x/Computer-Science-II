#ifndef QUEUE
#define QUEUE

#define QEMPTY -1
#define INIT_SIZE 10

// Stores our queue.
struct queue {
    int * elements;
    int front;
    int numElements;
    int queueSize;
};

void init(struct queue* qPtr);
int enqueue(struct queue* qPtr, int val);
int dequeue(struct queue* qPtr);
int queue_empty(struct queue* qPtr);
int front(struct queue* qPtr);

#endif
