#include <stdio.h>
#include <stdlib.h>
struct range {
    int start ;
    int end  ;
    int amt ;
};
int * swap( int * ray, int a, int b){
    int tmp = *(ray+a);
    *(ray+a) = *(ray+b);
    *(ray+b) = tmp ;
    return ray ;
}
void print(int * ray, int length ){
    int i = 0 ;
    printf("\n");
    for ( i = 0 ; i < length ; i++ ){
        printf("\e%d",*(ray+i));
    }
    printf("\n");
}
/*BlockSwap(array, index1, index2, count)
  for (index = 0; index < count; index++)
  Swap(array[index1 + index], array[index2 + index])*/
int * blockSwap(int * array, int index1, int index2, int count){
    for ( int index = 0 ; index < count ; index++ ){
        array = swap(array,*(array+index1+index),*(array+index2+index));
    }
    return array ;
}

int * reverse(int * array, int rstart, int rend, int rlength){
    int i = 0 ;
    for ( i = rlength/2-1; i >= 0 ; i-- ){
        array = swap(array,rstart+i,rend-i-1);
    }
    return array;
}
int * rotate (int * array, int start, int end, int amt) {
    array = reverse(array,start,end,start-end);
    array = reverse(array,amt,start+amt,end);
    return array ;
}
int main(){
    int * ray = (int *)malloc(sizeof(int)*10);
    int i = 0 ;
    for ( i = 0 ; i < 10 ; i++ ){
        ray[i] = i ;
    }
    print(ray,10);
    ray = rotate(ray,1,5,4);

    print(ray,10);
    ray = rotate(ray,1,5,4);
    print(ray,10);
}

