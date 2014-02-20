#include <stdio.h>
#include <stdlib.h>
int gcd(int a, int b ){
    if ( b == 0 ){
        return a ;
    }
    else {
        return gcd(b,a%b);
    }

}
int main(){
    int a,b ;
    printf("\nEnter A: ");
    scanf("%d",&a);
    printf("\nEnter B: ");
    scanf("%d",&b);

    int ans = gcd(a,b);
    printf("\nAnswer = %d\n",ans);
}
