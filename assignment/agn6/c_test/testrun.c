#include <stdio.h>
#include <stdlib.h>

int * initRow( int n );
void print (int * row, int n);

int main(){
    int n = 0 ;
    printf("Enter n: ");
    scanf("%d",&n);

    int * row = initRow( n );
    print(row,n);
}

void print (int * row, int n){
    int i = 0 ;
    for ( i = 0 ; i <= n ; i++ ) printf("|%d",row[i]);
    printf("|\n ");
}

int * initRow( int n ){
    int * rtn = (int *)malloc(sizeof(int)*(n+1));
    int i = 0 ;
    for ( i = 0 ; i < n+1 ; i++ ) rtn[i] = i ;
    return rtn ;
}
