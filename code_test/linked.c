#include <stdio.h>
#include <stdlib.h>
struct node {
     int data;
     struct node * next;
};

struct node * insert(struct node* list,int d );
struct node * del(struct node* list,int d );
void print( struct node *list);
void freelist(struct node* list);

// Recursively inserts item into the LL pointed to by list.
struct node * insert(struct node *list,int item) {

    // Node is at front, insert and return.
    if (list == NULL || item <= list->data) {
        struct node * pNew=(struct node *) (malloc(sizeof(struct  node)));
        pNew->data = item;
        pNew->next = list;
        return pNew;
    }

    // Recursively insert and return the front of the list.
    list->next = insert(list->next, item);
    return list;
}

// Recursively deletes the first node storing item in the list pointed to
// by list. If no such node exists, no changes are made. a pointer to the front
// of the resulting list is returned.
struct node * del(struct node *list, int item) {

    // Simple case.
    if (list == NULL)
        return NULL;

    // Free this node and return a pointer to the rest of the list.
    if (list->data == item) {
        struct node* rest = list->next;
        free(list);
        return rest;
    }

    // Recursively delete and return the front of the list.
    list->next = del(list->next, item);
    return list;
}

void print(struct node *list) {

    // Iterate through each item and print it!
    while (list != NULL) {
        printf("-->%d", list->data);
        list = list->next;
    }

    printf("\n");
}

// Frees each node in the LL pointed to by list.
void freelist(struct node* list) {

    // We can stop.
    if (list == NULL) return;

    // Free the rest of the list.
    if (list->next != NULL)
        freelist(list->next);

    // Free first node.
    free(list);
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
