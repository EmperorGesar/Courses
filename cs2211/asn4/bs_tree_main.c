#include <stdio.h>
#include <stdlib.h>
#include "bst.h"

int main(void) {

    BStree bst;

    bst = bstree_ini(256);

    FILE *input = fopen("E:\\UWO\\Soph\\Fall\\cs2211\\assignments\\assign4\\untitled\\input", "r");

    char cur;
    int i, id, data;
    char *temp;

    do{

        temp = (char *) malloc(sizeof(char) * 50);
        fscanf(input, "%s%d%d", temp, &id, &data);

        bstree_insert(bst, key_construct(temp, id), data);
        free(temp);

        cur = fgetc(input);

    } while (cur == '\n');

    for (i = 1; i < 190; i++){
        print_key(bstree_data_search(bst, i));
        printf("\n");
    }

}
