#include <stdio.h>
#include <stdlib.h>

const char segments[10][3][3] = {
        {
                {' ', '_', ' '},                    //  _ 
                {'|', ' ', '|'},                    // | |
                {'|', '_', '|'}                     // |_|
        },
        {
                {' ', ' ', ' '},                    //    
                {' ', ' ', '|'},                    //   |
                {' ', ' ', '|'}                     //   |
        },
        {
                {' ', '_', ' '},                    //  _ 
                {' ', '_', '|'},                    //  _|
                {'|', '_', ' '}                     // |_
        },
        {
                {' ', '_', ' '},                    //  _ 
                {' ', '_', '|'},                    //  _|
                {' ', '_', '|'}                     //  _|
        },
        {
                {' ', ' ', ' '},                    //    
                {'|', '_', '|'},                    // |_|
                {' ', ' ', '|'}                     //   |
        },
        {
                {' ', '_', ' '},                    //  _ 
                {'|', '_', ' '},                    // |_
                {' ', '_', '|'}                     //  _|
        },
        {
                {' ', '_', ' '},                    //  _ 
                {'|', '_', ' '},                    // |_
                {'|', '_', '|'}                     // |_|
        },
        {
                {' ', '_', ' '},                    //  _ 
                {' ', ' ', '|'},                    //   |
                {' ', ' ', '|'}                     //   |
        },
        {
                {' ', '_', ' '},                    //  _ 
                {'|', '_', '|'},                    // |_|
                {'|', '_', '|'}                     // |_|
        },
        {
                {' ', '_', ' '},                    //  _ 
                {'|', '_', '|'},                    // |_|
                {' ', '_', '|'}                     //  _|
        }
};

int main(){

    int num, i, j, k, count, negative;
    int separate[100];
    char valid;

    do {

        printf("Please enter the integer:\n");
        scanf("%d", &num);
        getchar();
        printf("Display:\n");

        if (num < 0) negative = -1;
        else negative = 0;
        num = abs(num);

        count = 0;
        for (i = 0; i < 100; i++){
            separate[i] = 0;
        }

        while (num != 0){
            separate[count] = num % 10;
            num = num / 10;
            count = count + 1;
        }

        for (i = 0; i < 3; i++){

            if (negative == -1){
                if (i == 1) printf(" _  ");
                else printf("    ");
            }

            for (k = count - 1; k >= 0; k--) {
                for (j = 0; j < 3; j++) {
                    printf("%c", *(char*)&segments[separate[k]][i][j]);
                }
                printf(" ");
            }
            
            printf("\n");

        }

        do {

            printf("Continue display?\n");
            printf("  * 'Y' to continue\n");
            printf("  * 'N' to quit\n");
            valid = getchar();
            getchar();

            if (valid != 'Y' && valid != 'N'){
                printf("Unknown operation. Please re-enter:\n");
            }

        } while (valid != 'Y' && valid != 'N');

    } while (valid != 'N');

    return 0;

}
