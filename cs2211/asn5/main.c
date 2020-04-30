#include <stdio.h>
#include "memory.h"
#include "list.h"

int main(){

    FILE *input = fopen("infile", "r");

    int i, size, temp;
    char cur;
    fscanf(input, "%d", &size);
    cur = fgetc(input);

    int num[10];
    for (i = 0; i < 10; i++){
        num[i] = 0;
    }

    do{

        fscanf(input, "%d", &temp);
        num[temp] = num[temp] + 1;

        cur = fgetc(input);

    } while (cur == '\n');

    mem_ini(size);
    printf("-----total memory-----\n");
    printf("  key        data     \n");
    mem_print();
    printf("\n");

    List list = list_ini();

    printf("-----initial list-----\n");
    printf("  key        data     \n");
    mem_print();
    printf("\n");

    for (i = 0; i < 10; i++){
        if (num[i] != 0) list_add(list, i, num[i]);
    }

    printf("-----after adding-----\n");
    printf("  key        data     \n");
    mem_print();
    printf("\n");

    printf("------list count------\n");
    printf("integer   occurrence  \n");
    list_print(list);
    printf("\n");

    list_delete(list, 1);
    list_delete(list, 2);
    list_delete(list, 4);

    printf("-----after delete-----\n");
    printf("  key        data     \n");
    mem_print();
    printf("\n");

    printf("------list count------\n");
    printf("integer   occurrence  \n");
    list_print(list);
    printf("\n");

    list_add(list, 2, num[2]);
    list_add(list, 4, num[4]);
    list_add(list, 1, num[1]);

    printf("-----adding again-----\n");
    printf("  key        data     \n");
    mem_print();
    printf("\n");

    printf("------list count------\n");
    printf("integer   occurrence  \n");
    list_print(list);
    printf("\n");

    list_free(list);

    printf("------free nodes------\n");
    printf("  key        data     \n");
    mem_print();

    mem_free();

    return 0;

}
