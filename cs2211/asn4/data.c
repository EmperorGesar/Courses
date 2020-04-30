#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "data.h"

Key *key_construct(char *in_name, int in_id) {

    Key *pointerKey;
    pointerKey = (Key *) malloc(sizeof(Key));

    pointerKey->id = in_id;

    pointerKey->name = (char *) malloc(sizeof(char) * strlen(in_name));
    strcpy(pointerKey->name, in_name);

    return pointerKey;

}

int key_comp(Key *key1, Key *key2) {

    if (strcmp(key1->name, key2->name) != 0){
        return strcmp(key1->name, key2->name);
    } else {

        if (key1->id != key2->id){
            return key2 - key1;
        } else {
            return 0;
        }

    }

}

int data_comp(Data data1, Data data2){
    return data1 - data2;
}

void print_key(Key *key) {

    int num = 0;
    int temp = key->id;
    int i;

    while (temp != 0){
        num = num + 1;
        temp = temp / 10;
    }

    printf("( ");

    for (i = 0; i < strlen(key->name); i++){
        printf("%c", key->name[i]);
    }

    for (i = 0; i < 43 - strlen(key->name) - num; i++){
        printf(" ");
    }

    printf("%d )", key->id);

}

void print_node(Node node) {

    print_key(node.key);

    int num = 0;
    int temp = node.data;
    int i;

    while (temp != 0){
        num = num + 1;
        temp = temp / 10;
    }

    for (i = 0; i < 12 - num; i++){
        printf(" ");
    }

    printf("%d", node.data);
    printf("\n");

}
