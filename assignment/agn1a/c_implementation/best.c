#include <stdio.h>
#include <stdlib.h>
#include "queue.h"
#include "stack.h"
#define EMPTY -1

int ** readIn(int n);
int readGraph();
void printMatrix(int ** matrix, int n);

void breadthFirst( int ** matrix, int startVertex, int matrixSize , struct queue * q );
void depthFirst(int ** matrix, int startVertex, int matrixSize, struct stack s);
void depthFirstRecursive(int ** matrix, int vertex, int matrixSize, int * visited );

int main(){
    int numGraphs = 0 ;
    scanf("%d",&numGraphs);

    int i = 0 ;
    for ( i = 0 ; i < numGraphs ; i++ ){
        readGraph();
    }
}



// good
void breadthFirst( int ** matrix, int startVertex, int matrixSize , struct queue * q ){
    int dq ;
    int visited[matrixSize] ;
    int i = 0 ;
    for ( i = 0 ; i < matrixSize ; i++ ) visited[i] = 0 ;

    // Queue the first vertex and mark as visited
    enqueue(q,startVertex);
    visited[startVertex] = 1 ;

    while (!queue_empty(q)){
        // Dequeue and print
        dq = dequeue(q);
        printf("%d ",dq);

        int j = 0 ;

        // Look for all unvisited 1's in the row of dequeued (matrix[dq][*]) that is unvisited
        for ( j = 0 ; j < matrixSize ; j++ ){
            // if a 1 in row of dq, and it is unvisited, add to queue and mark visited
            if ( matrix[dq][j] == 1 && visited[j] == 0 ){
                enqueue(q,j);
                visited[j] = 1 ;
                // dq = j ;
            }
        }
    }
}

void depthFirstRecursive(int ** matrix, int vertex, int matrixSize, int * visited ){
}

void depthFirst(int ** matrix, int startVertex, int matrixSize, struct stack s){
    int * visited = (int *) malloc(sizeof(int)*matrixSize);
    int i = 0 ;
    for ( i = 0 ; i < matrixSize ; i++ ) visited[i] = 0 ;

    push(&s,startVertex);
    visited[startVertex]= 1;
    int vertex ;
    while (!empty(&s)){
        vertex = pop(&s);
        if ( vertex == startVertex  ) printf("%d ",vertex);

        for ( i = 0 ; i < matrixSize ; i++ ){
            if ((matrix[vertex][i]==1) && (visited[i]==0)) {
                push(&s,i);
                visited[i] = 1;
                printf("%d ",i);
                // vertex = i ;
            }
        }
    }
}









/*
 * Read utilities
 */
int readGraph(){
    int startVertex = 0 ;
    int n = 0 ;
    scanf("%d",&startVertex);
    scanf("%d",&n);

    int ** matrix = readIn(n);
    printMatrix(matrix,n);

    struct queue* q = (struct queue*)malloc(sizeof(struct queue));
    init(q);
    struct stack stk;
    initialize(&stk);
    int * visited = (int *) malloc(sizeof(int)*n);
    int i = 0 ;
    for ( i = 0 ; i < n ; i++ ) visited[i] = 0 ;

    printf("\nBreadth First: ");
    breadthFirst(matrix,startVertex,n,q);


    printf("\nDepth First: ");
    depthFirst(matrix,startVertex,n,stk);
    printf("\n");
    printf("\n");

/*
    printf("\ndepth first recursive: ");
    depthfirstrecursive(matrix,startvertex,n,visited);
    printf("\n");
*/
    free(q);
    free(visited);
    free(matrix);

}
int ** readIn(int n){
    int ** matrix = (int **)malloc(sizeof(int *)*n);
    int i = 0 ;
    for ( i = 0 ; i < n ; i++ ){
        *(matrix+i) = (int *)malloc(sizeof(int)*n);
        int j = 0 ;
        for ( j = 0 ; j < n ; j++ ){
            scanf("%d",(*(matrix+i)+j));
        }
    }
    return matrix ;
}
void printMatrix(int ** matrix, int n){
    int i, j ;
    i = 0 ;
    j = 0 ;
    printf("\n-----------------------------\n");
    printf(  "-----------Matrix------------\n");
    printf(  "-----------------------------\n");
    for ( i = 0 ; i < n ; i++ ){
        for ( j = 0 ; j < n ; j++ ){
            printf("%d ",*(*(matrix+i)+j));
        }
        printf("\n");
    }
    printf("\n");
}
