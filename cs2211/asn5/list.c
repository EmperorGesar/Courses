#include <stdio.h>
#include <stdlib.h>
#include "list.h"
#include "memory.h"

// Return a pointer to a dynamically allocated and initialized List
List list_ini(void){

    List_node *head = (List_node *) simu_malloc(sizeof(List_node));
    head->next = NULL;
    return head;

}

// If key is in list, return the address of key’s associated data
// If key is not inlist, return NULL
Data *list_search(List list, Key key){

    if (list == NULL){
        return NULL;
    } else {

        if (key_comp(list->key, key) == 0){
            return &list->data;
        } else {
            return list_search(list->next, key);
        }

    }

}

// Add key with data into the front of list
// If key is in list, then do nothing
void list_add(List list, Key key, Data data){

    if (key_comp(list->key, key) == 0){
        return;
    } else {

        if (list->next == NULL){
            list->next = list_ini();
            list->next->key = key;
            list->next->data = data;
        } else {
            list_add(list->next, key, data);
        }

    }

}

// Delete the node in list with its key equals to key
// If no such node in list, do nothing
void list_delete(List list, Key key){

    if (list->next != NULL){

        if (key_comp(list->next->key, key) == 0){

            List temp = list->next;
            list->next = list->next->next;
            simu_free(temp);

        } else {
            list_delete(list->next, key);
        }

    }

}

// Linearly traversal the list and print each node’s key and data
void list_print(List list){

    if (list->next == NULL){
        return;
    } else {

        print_list_node(list->next);
        list_print(list->next);

    }

}

// Free all the dynamically allocated memory of list
void list_free(List list){

    if (list == NULL){
        return;
    } else {

        list_free(list->next);
        simu_free(list);

    }

}
