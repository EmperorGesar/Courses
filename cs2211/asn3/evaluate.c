#include <stdio.h>
#include <stdlib.h>

char get_op(){

    char space;
    space = getchar();

    while (space == ' ' || space == '\t'){
        space = getchar();
    }
    ungetc(space, stdin);

    char op;
    scanf("%c", &op);
    return op;

}

float get_num(){

    char space;
    space = getchar();

    while (space == ' ' || space == '\t'){
        space = getchar();
    }
    ungetc(space, stdin);

    float num;
    scanf("%f", &num);
    return num;

}

float m_exp(float sub_exp, char op){

    float numCur;
    char opCur;

    if (op == '*'){

        numCur = get_num();
        opCur = get_op();
        return m_exp(sub_exp * numCur, opCur);

    } else if (op == '/'){

        numCur = get_num();
        opCur = get_op();
        return m_exp(sub_exp / numCur, opCur);

    } else if (op == '+' || op == '-' || op == '\n'){

        ungetc(op, stdin);
        return sub_exp;

    } else {
        exit(EXIT_FAILURE);
    }

}

float s_exp(){

    float numCur = get_num();
    char opCur = get_op();
    float ans = m_exp(numCur, opCur);
    opCur = get_op();

    while (opCur != '\n'){

        if (opCur == '+'){

            numCur = get_num();
            opCur = get_op();
            ans = ans + m_exp(numCur, opCur);

        } else if (opCur == '-'){

            numCur = get_num();
            opCur = get_op();
            ans = ans - m_exp(numCur, opCur);

        }

        opCur = get_op();
    }

    return ans;

}

int main(){

    float ans;
    char valid;

    do {

        printf("Please enter the arithmetic expression:\n");
        ans = s_exp();
        printf("The answer is:\n");
        printf("%f", *(float*)&ans);
        printf("\n");

        do {

            printf("Continue calculation?\n");
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
