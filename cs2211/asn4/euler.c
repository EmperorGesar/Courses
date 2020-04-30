/*
#include <stdio.h>

double factorial(int n){

    if (n == 0){
        return 1;
    } else {
        return 1 / (n * (1 / factorial(n - 1)));
    }

}

int main(){

    double epsilon, ans = 0;
    long long last = 1;
    int n = 1;
    int i;


    printf("Please enter epsilon: \n");
    scanf("%lf", &epsilon);

    while (last < (1 / epsilon)){
        n = n + 1;
        last = last * n;
    }

    for (i = 0; i <= n; i++){
        ans = ans + factorial(i);
    }

    printf("The constant e is: \n");
    printf("%.16lf", ans);
    printf("\n");

}
*/