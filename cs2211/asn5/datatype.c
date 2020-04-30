#include <stdio.h>
#include <stdlib.h>
#include "datatype.h"

// Input:	’key1’ and ’key2’ are two Keys
// Output: if return value < 0, then key1 < key2,
// 				 if return value = 0, then key1 = key2,
//				 if return value > 0, then key1 > key2,
int key_comp(Key key1, Key key2){
    return key1 - key2;
}

// Input:	’data1’ and ’data2’ are two Data
// Output: if return value < 0, then data1 < data2,
// 				 if return value = 0, then data1 = data2,
//				 if return value > 0, then data1 > data2,
int data_comp(Data data1, Data data2){
    return data1 - data2;
}

//	Input: ’key’: a Key
//	Effect: key is printed
void key_print(Key key){

    int num = 0;
    int temp = key;
    int i;

    while (temp != 0){
        num = num + 1;
        temp = temp / 10;
    }
    if (key == 0) num = 1;

    for (i = 0; i < 4 - num; i++){
        printf(" ");
    }

    printf("%d", key);

}

//	Input: ’data’: a Data
//	Effect: data is printed
void data_print(Data data){

    int num = 0;
    int temp = data;
    int i;

    while (temp != 0){
        num = num + 1;
        temp = temp / 10;
    }
    if (data == 0) num = 1;

    for (i = 0; i < 13 - num; i++){
        printf(" ");
    }
    printf("%d", data);

}

//	Input: ’node’: a Node
//	Effect: node.key is printed and then the node.data is printed
void print_node(Node node){

    key_print(node.key);
    data_print(node.data);
    printf("\n");

}

//	Input: ’node’: a pointer to List_node
//	Effect: node->key is printed and then the node->data is printed
void print_list_node(List_node *node){

    key_print(node->key);
    data_print(node->data);
    printf("\n");

}
