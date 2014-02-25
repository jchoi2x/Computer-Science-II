#include <stdio.h>
#include <stdlib.h>

/*    Function: (int) numBitsRequired
 *   Arguments: (int) n
 * Description: Given an integer n, returns the number of bits required to represent n in binary   */
int numBitsRequired( int n ){
    if ( n == 1 ) return 1 ;
    else return numBitsRequired(n/2)+1 ;
}
void printBinary(int n){
    if ( n == 0 ) return ;

    printBinary(n/2);
    printf("%d",n%2);
}





int main(){
    int userIn = 0 ;

    printf("\nEnter number (n): ");
    scanf("%d",&userIn);
    printf("\nNumber of bits required to represent %d in binary is %d\n",userIn,numBitsRequired(userIn));

    printf("Binary: ");
    printBinary(userIn);
    printf("\n");
}
