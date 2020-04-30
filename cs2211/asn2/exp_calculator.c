#include <stdio.h>

float exponential(float base, int exponent) {

    if (exponent == 1){
        return base;
    } else if (exponent % 2 == 0){
        return exponential(base, (exponent / 2)) * exponential(base, (exponent / 2));
    } else {
        return exponential(base, ((exponent - 1) / 2)) * exponential(base, ((exponent - 1) / 2)) * base;
    }

}

int main(){

    float base;
    int exponent;

    printf("Please enter the value of the base:\n");
    scanf("%f", &base);
    printf("Please enter the value of the exponent:\n");
    scanf("%d", &exponent);

    if (base > 0 && exponent > 0){
    
        float answer = exponential(base, exponent);
        printf("The answer is:\n");
        printf("%.2f", *(float *)&answer);
        printf("\n");
        
    } else {
        printf("Error. Base or Exponent is not positive.\n");
    }

    return 0;

}
