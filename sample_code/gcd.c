#include <stdio.h>
#include <stdlib.h>

int gcd(int m , int n );

int main(){
  int n,m;
  printf("\nNum1:");
  scanf("%d",&m);
  printf("\nNum2:");
  scanf("%d",&n);
  printf("\nGreatest common Divisor: %d\n",gcd(m,n));
}

int gcd( int m , int n ){
  if ( n == 0 ){
    return m ;
  }
  else{
    return gcd(n,m%n);
  }
}

