#include <stdio.h>

float one(char target, float value){

    if (target == 'K'){
        return value * 0.6214;
    } else if (target == 'M'){
        return value * 1.6093;
    }
    return 0;

}

float two(char target, float value){

    if (target == 'M'){
        return value * 3.2808;
    } else if (target == 'F'){
        return value * 0.3048;
    }
    return 0;

}

float three(char target, float value){

    if (target == 'C'){
        return value * 0.3937;
    } else if (target == 'I'){
        return value * 2.5400;
    }
    return 0;

}

float four(char target, float value){

    if (target == 'C'){
        return value * 1.8 + 32;
    } else if (target == 'F'){
        return (value - 32) / 1.8;
    }
    return 0;

}

int main() {

    int action;
    char target;
    float value, answer;

    printf("Please enter the number to select the action you want to execute:\n");
    printf("  * 1 for conversion between Kilometer and Mile\n");
    printf("  * 2 for conversion between Meter and Feet\n");
    printf("  * 3 for conversion between Centimetre and Inch\n");
    printf("  * 4 for conversion between Celsius and Fahrenheit\n");
    printf("  * 5 for quit\n");

    scanf("%d", &action);
    getchar();

    while (action != 5){

        if (action == 1){

            printf("Please enter the character to select the operation:\n");
            printf("  * 'K' for conversion from Kilometer to Mile\n");
            printf("  * 'M' for conversion from Mile to Kilometer\n");

            target = getchar();
            while (target == ' ' || target == '\t' || target == '\n'){
                target = getchar();
            }
            getchar();

            if (target == 'K' || target == 'M'){

                printf("Please enter the value:\n");
                scanf("%f", &value);
                getchar();

                answer = one(target, value);
                printf("The converted value is:\n");
                printf("%.2f", *(float *)&answer);
                if (target == 'K'){
                    printf(" mile\n");
                } else {
                    printf(" kilometer\n");
                }

            } else {
                printf("Unknown type. Please re-enter:\n");
            }

        } else if (action == 2){

            printf("Please enter the character to select the operation:\n");
            printf("  * 'M' for conversion from Meter to Feet\n");
            printf("  * 'F' for conversion from Feet to Meter\n");

            target = getchar();
            while (target == ' ' || target == '\t' || target == '\n'){
                target = getchar();
            }
            getchar();

            if (target == 'M' || target == 'F'){

                printf("Please enter the value:\n");
                scanf("%f", &value);
                getchar();

                answer = two(target, value);
                printf("The converted value is:\n");
                printf("%.2f", *(float *)&answer);
                if (target == 'M'){
                    printf(" feet\n");
                } else {
                    printf(" meter\n");
                }
            } else {
                printf("Unknown type. Please re-enter:\n");
            }

        } else if (action == 3){

            printf("Please enter the character to select the operation:\n");
            printf("  * 'C' for conversion from Centimetre to Inch\n");
            printf("  * 'I' for conversion from Inch to Centimetre\n");

            target = getchar();
            while (target == ' ' || target == '\t' || target == '\n'){
                target = getchar();
            }
            getchar();

            if (target == 'C' || target == 'I'){

                printf("Please enter the value:\n");
                scanf("%f", &value);
                getchar();

                answer = three(target, value);
                printf("The converted value is:\n");
                printf("%.2f", *(float *)&answer);
                if (target == 'C'){
                    printf(" inch\n");
                } else {
                    printf(" centimetre\n");
                }

            } else {
                printf("Unknown type. Please re-enter:\n");
            }

        } else if (action == 4){

            printf("Please enter the character to select the operation:\n");
            printf("  * 'C' for conversion from Celsius to Fahrenheit\n");
            printf("  * 'F' for conversion from Fahrenheit to Celsius\n");

            target = getchar();
            while (target == ' ' || target == '\t' || target == '\n'){
                target = getchar();
            }
            getchar();

            if (target == 'C' || target == 'F'){

                printf("Please enter the value:\n");
                scanf("%f", &value);
                getchar();

                answer = four(target, value);
                printf("The converted value is:\n");
                printf("%.1f", *(float *)&answer);
                if (target == 'C'){
                    printf(" fahrenheit\n");
                } else {
                    printf(" celsius\n");
                }

            } else {
                printf("Unknown type. Please re-enter:\n");
            }

        } else {
            printf("Unknown action. Please re-enter:\n");
        }

        printf("Please enter the number to select the action you want to execute:\n");
        printf("  * 1 for conversion between Kilometer and Mile\n");
        printf("  * 2 for conversion between Meter and Feet\n");
        printf("  * 3 for conversion between Centimetre and Inch\n");
        printf("  * 4 for conversion between Celsius and Fahrenheit\n");
        printf("  * 5 for quit\n");

        scanf("%d", &action);
        getchar();

    }

    return 0;

}
