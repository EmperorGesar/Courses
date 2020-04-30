#include <stdio.h>
#include <stdlib.h>
#include "memory.h"

static unsigned char *memory;
static BStree bst;

// Initialize an unsigned char array of size elements and initialize a binary search tree
void mem_ini(unsigned int size){

    memory = (unsigned char *) malloc(size);
    bst = bstree_ini(size / 5); //  size / 5 is large enough
    bstree_insert(bst, 0, size);

}

// Allocate size bytes and return a pointer to the first byte allocated
void *simu_malloc(unsigned int size){

    Key *block = bstree_data_search(bst, size + 4);
    int start = *block;
    Data *total = bstree_search(bst, start);

    bstree_delete(bst, start);

    unsigned char *byte = (unsigned char *) &size;
    int i;

    for (i = 0; i < 4; i++){
        memory[start + i] = byte[i];
    }

    if (size + 4 != *total){
        bstree_insert(bst, (start + size + 4), (*total - size - 4));
    }

    return &memory[start + 4];

}

// Put the allocated memory pointed by ptr back to be free memory
void simu_free(void *ptr){

    unsigned char *cast = (unsigned char *) ptr;

    int start = ((cast - &memory[0]) - 4);
    int size = *(int *) &memory[start];

    bstree_insert(bst, start, size + 4);

}

// Print all the free memory blocks
void mem_print(void){
    bstree_traversal(bst);
}

// Free memory used for the array and the binary search tree
void mem_free(void){

    free(memory);
    bstree_free(bst);

}
