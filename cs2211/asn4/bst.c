#include <stdio.h>
#include <stdlib.h>
#include "bst.h"

BStree bstree_ini(int size) {

    BStree_struct *pointerTree;
    pointerTree = (BStree_struct *) malloc(sizeof(BStree_struct));

    pointerTree->tree_nodes = (Node *) malloc(sizeof(Node) * (size + 1));
    pointerTree->free_nodes = (unsigned int *) malloc(sizeof(unsigned int) * (size + 1));

    pointerTree->size = size;

    unsigned int i;
    for (i = 0; i < size + 1; i++){
        pointerTree->free_nodes[i] = i;
    }

    pointerTree->top = 1;
    pointerTree->root = 0;

    return pointerTree;

}

static int new_node(BStree bst, Key *key, Data data){

    if (bst->free_nodes[bst->root] == 0){

        if (key_comp(key, bst->tree_nodes[bst->root].key) < 0){

            if (bst->tree_nodes[bst->root].left != 0){

                bst->root = bst->tree_nodes[bst->root].left;
                return new_node(bst, key, data);

            } else {

                bst->tree_nodes[bst->top].key = key;
                bst->tree_nodes[bst->top].data = data;

                bst->tree_nodes[bst->top].left = 0;
                bst->tree_nodes[bst->top].right = 0;

                bst->tree_nodes[bst->root].left = bst->top;

                return bst->top;

            }

        } else if (key_comp(key, bst->tree_nodes[bst->root].key) > 0){

            if (bst->tree_nodes[bst->root].right != 0){

                bst->root = bst->tree_nodes[bst->root].right;
                return new_node(bst, key, data);

            } else {

                bst->tree_nodes[bst->top].key = key;
                bst->tree_nodes[bst->top].data = data;

                bst->tree_nodes[bst->top].left = 0;
                bst->tree_nodes[bst->top].right = 0;

                bst->tree_nodes[bst->root].right = bst->top;

                return bst->top;

            }

        } else {
            return 0;
        }

    } else {
        return 0;
    }

}

void bstree_insert(BStree bst, Key *key, Data data) {

    if (bst->root == 0) {

        bst->tree_nodes[bst->top].key = key;
        bst->tree_nodes[bst->top].data = data;

        bst->tree_nodes[bst->top].left = 0;
        bst->tree_nodes[bst->top].right = 0;

        bst->free_nodes[bst->top] = 0;
        bst->top = bst->top + 1;
        bst->root = 1;

    } else if (bst->top == bst->size){

        exit(EXIT_FAILURE);

    } else {

        int next = new_node(bst, key, data);

        if (next != 0){

            bst->free_nodes[bst->top] = 0;
            bst->top = bst->top + 1;
            bst->root = 1;

        }

    }

}

void bstree_traversal(BStree bst) {

    if (bst->root != 0){

        int current = bst->root;

        if (bst->tree_nodes[bst->root].left != 0){

            bst->root = bst->tree_nodes[bst->root].left;
            bstree_traversal(bst);
            bst->root = current;

        }

        print_node(bst->tree_nodes[bst->root]);

        if (bst->tree_nodes[bst->root].right != 0){

            bst->root = bst->tree_nodes[bst->root].right;
            bstree_traversal(bst);
            bst->root = current;

        }

    }

}

void freeNode(BStree bst){

    if (bst->root != 0){

        int current = bst->root;

        if (bst->tree_nodes[bst->root].left != 0){

            bst->root = bst->tree_nodes[bst->root].left;
            freeNode(bst);
            bst->root = current;

        }

        if (bst->tree_nodes[bst->root].right != 0){

            bst->root = bst->tree_nodes[bst->root].right;
            freeNode(bst);
            bst->root = current;

        }

        free(bst->tree_nodes[bst->root].key->name);
        free(bst->tree_nodes[bst->root].key);

    }

}

void bstree_free(BStree bst) {

    freeNode(bst);
    free(bst->tree_nodes);
    free(bst);

}

static Data *bst_search(BStree bst, int index, Key *key){

    if (index == 0){
        return NULL;
    } else {

        if (key_comp(key, bst->tree_nodes[index].key) < 0){
            return bst_search(bst, bst->tree_nodes[index].left, key);
        } else if (key_comp(key, bst->tree_nodes[index].key) > 0){
            return bst_search(bst, bst->tree_nodes[index].right, key);
        } else {
            return &bst->tree_nodes[index].data;
        }

    }

}

Data *bstree_search(BStree bst, Key *key){
    return bst_search(bst, bst->root, key);
}

static Key *bst_data_search(BStree bst, int index, Data data){

    if (index == 0) {
        return NULL;
    } else {

        Key *temp;

        temp = bst_data_search(bst, bst->tree_nodes[index].left, data);
        if (temp != NULL) return temp;

        if (data_comp(bst->tree_nodes[index].data, data) == 0){
            return bst->tree_nodes[index].key;
        }

        temp = bst_data_search(bst, bst->tree_nodes[index].right, data);
        if (temp != NULL) return temp;

    }

    return NULL;

}

Key *bstree_data_search(BStree bst, Data data){
    return bst_data_search(bst, bst->root, data);
}
