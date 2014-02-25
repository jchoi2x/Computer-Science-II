#include <stdio.h>
#include <stdlib.h>

int decByHalf(int a, int n){
    if ( n == 0 ) return 1 ;
    else {
        if ( n % 2 == 0 ){
            int subProb = decByHalf(a,n/2);
            subProb = subProb*subProb ;
            return subProb ;
        }
        else{
            int subProb = decByHalf(a,(n-1)/2);
            subProb = subProb*subProb*a ;
            return subProb ;
        }
    }
}
int main(){
    int a, n ;
    printf("\nEnter a: ");
    scanf("%d",&a);
    printf("\nEnter n: ");
    scanf("%d",&n);

    printf("\nSolution:%d",decByHalf(a,n));
}
