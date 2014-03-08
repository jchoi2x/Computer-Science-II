#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

int max(char * num1,char * num2, int base){
    int one, two ;
    one = strlen(num1);
    two = strlen(num2);
    if ( one == two ){
        return one ;
    }
    else if ( one > two ){
        return one ;
    }
    else {
        return two ;
    }
}
int convert_to_int(char * num){
    int len = strlen(num)-1 ;
    int sum = 0 ;
    int i = 0 ;
    int j = 0 ;
    for ( i = len ; i > -1 ; i--){
        sum+=(num[j]-'0')*pow(10,i);
        j++;
    }
    return sum ;
}
char * convert_to_char(int num){
    int BUF = 10 ;
    int x = num ;
    char * rtn1 = (char *)malloc(sizeof(char)*BUF);
    int j = 0 ;
    while ( x > 0 ){
        // extract rightmost
        rtn1[j] = x%10+'0' ;
        x=x/10 ;
        j++ ;
        if ( j > BUF-1){
            BUF*=2;
            rtn1 = (char * )realloc(rtn1,sizeof(char)*BUF);
        }
    }
    char * rtn2 = (char *)malloc(sizeof(char)*(strlen(rtn1)+1));
    int k = 0 ;
    for ( j = strlen(rtn1)-1 ; j > -1 ; j-- ){
        rtn2[k++] = rtn1[j];
    }
    return rtn2;
}
char * split_at(char * num, int split1, int split2){
    if ( split1 >= strlen(num)){
        return NULL ;
    }
    else if (split2 < strlen(num)){
        if ( split1 == 0 ){
            char * rtn = (char *) malloc(sizeof(char)*split2);
            strncpy(rtn,num,split2);
            return rtn ;
        }
        else{
            char * rtn = (char *) malloc(sizeof(char)*(split2-split1+1));
            strncpy(rtn,num+split1,split2);
            return rtn ;
        }
    }
    else{
        if ( split1 == 0 ){
            char * rtn = (char *) malloc(sizeof(char)*split2);
            strncpy(rtn,num,split2);
            return rtn ;
        }
        else{
            char * rtn = (char *) malloc(sizeof(char)*(split2-split1+1));
            strncpy(rtn,num+split1,split2);

            return rtn ;
        }
    }
}
int karatSuba( char * num1, char * num2 ){
    int n1,n2 ;
    n1 = convert_to_int(num1);
    n2 = convert_to_int(num2);

    if ( n1 < 10 || n2 < 10 ){
        return n1*n2 ;
    }
    int m = max(num1,num2,10)/2;

    // high1, low1 = split_at(num1,m2);
    char * high1 = split_at(num1,0,m);
    char * low1 = split_at(num1,m,strlen(num1));
    // high2, low2 = split_at(num2,m2);
    char * high2 = split_at(num2,0,m);
    char * low2 = split_at(num2,m,strlen(num2));

    int z0 = karatSuba(low1,low2);
    int z1 = karatSuba(convert_to_char(convert_to_int(low1)+convert_to_int(high1)),(convert_to_char(convert_to_int(low2)+convert_to_int(high2))));
    int z2 = karatSuba(high1,high2);
    return (z2*pow(10,2*m))+((z1-z2-z0)*pow(10,m))+z0;
}

char * readInput(int inputNum){
    char * strT = (char * )malloc(sizeof(char)*100);
    printf("Enter Input %d: ",inputNum);
    scanf("%s",strT);
    return strT;
}
int main(){
    char * num1 ;
    char * num2 ;
    num1 = readInput(1);
    num2 = readInput(2);

    printf("\nConverted to int:%d",convert_to_int(num1));
    printf("\nConverted to char:%s",convert_to_char(convert_to_int(num1)));
    printf("\nMax:%d",max(num1,num2,10));
    printf("\nSplit at 0-3:%s",split_at(num1,0,3));
    printf("\nSplit at 3-5:%s",split_at(num1,3,strlen(num1)));
    printf("\nKaratsuba:%d",karatSuba(num1,num2));

    return 0 ;
}




