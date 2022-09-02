/**
 * linked_list.c
 *
 * Implements an unsorted linked list with ops insert, append, print, delete.
 * To keep track of list, you'll pass in its head and then update it (if
 * necessary) using double pointers.
 *
 * Compile:  gcc -g -Wall -o linked_list linked_list.c
 * Run:      ./linked_list
 */

#include <stdio.h>
#include <stdlib.h>


//Declare the Node
typedef struct list_node {
    int data;
    struct list_node *next;
}Node;

//Declare the LinkedLis
typedef struct LinkedList{
    Node *head;
    int size;



}LinkedList;

/** Insert at the beginning (head) of the list. */
//return data.
int insert(LinkedList *list, int item) {
    if (list == NULL) {
        printf("Error: list is NULL.");\
        return -1;
    }

    if (list->size == 0) {
        if (list->head ==NULL) {
            head = new Node {
                data = item;
                next = NULL;
            };
            list->size++;
        } else {
            printf("Error: error list.")
            return -1;
        }

    }
    else {
        if (list->head == NULL) {
            printf("Error: size is not fit the list.")
            return -1;
        }
        Node itemNode = new Node {
            data = item;
            next = list->head;
        };
        list->head = itemNode;
        list->size++;
    }
    return 0;
}

/** Inserts at a particular position in the list. */
// return data.
int insert_at(LinkedList *list, int pos, int item) {
    if (list == NULL) {
        printf("Error: list is NULL");
        return -1;
    }
    if (list->size < pos || pos < 0) {
        printf("Error: incorrect position.");
        return -1;
    }
    if (size == 0) {
        if (list->head == NULL) {
            list->head = new Node {
                data = item;
                next = NULL;
            }
            list->size++;
        } else {
            printf ("Error: error list;")
        }
    }
    else {
        Node *currNode = list->head;
        for (int i = 0; i < pos - 1; i++) {
            Node currNode = currNode->next;
        }
        Node itemNode = new Node{
            data = item;
            next = currNode->next->next;
        };
        *(currNode->next) = itemNode;
        list->size++;
    }
    return 0;
}

/** Appends to the end of the list */
int append(LinkedList *list, item) {
    if (list == NULL) {
        printf("Error: list is NULL");
        return -1;
    }
    if (list->size  == 0) {
        if (list->head == NULL) {
            list->head = new Node{
                data = item;
                next = NULL;
            };
            list->size++;
        }
        else {
            printf("Error: incorrect list");
            return -1;
        }

    }
    else {
        Node *currNode = list->head;
        for (int i = 0; i < list->size - 1; i++) {
            currNode = currNode->next;
        }
        currNode->next = new Node {
            data = item;
            next = NULL;
        };
        list->size++;
        return 0;
    }
}

/** Prints the list contents */
void print(LinkedList *list) {
    if (list == NULL) {
        printf ("Error: list is NULL.");
        return;
    }
    else {
        if (list->size == 0) {
            printf("[]");
        }
        else {
            Node *currNode = list->head;
            for (int i = 0; i < list->size - 1; i++) {
                printf("[");
                printf("%d, ", currNode->data);
                currNode = currNode->next;
            }
            printf("%d]", currNode->data);
        }
    }
}

/**
 * Deletes a particular value from the list (if there are duplicate values, the
 * first is deleted. */
int delete(LinkedList *list, int item) {
    if (list == NULL) {
    printf("Error: list is NULL.");
    return -1;
    }
    if (list->size != 0) {
        Node *currNode = list->head;
        if (currNode->data == item) {
            list->head = currNode->next;
            list->size--;
        }
        for (int i - 0; i < list->size - 1; i++) {
            if (currNode->next->data == item) {
                currNode->next = currNode->next->next;
                list->size--;
                return 0;
                break;
            }
        }
        return -1;
    }
}

/** Delete the node at a particular position in the list */
int delete_at(LinkedList *list, int pos) {
    if (list == NULL) {
        printf("Error: list is NULL.");
        return -1;
    }
    if (list->size -  1 < pos) {
        printf("Error: incorrect list.");
        return -1;
    }
    else {
        Node *currNode = list->head;
        for (int i = 0; i < pos - 1; i++) {
            currNode = currNode->next;
        }
        currNode->next = currNode->next->next;
        list->size--;
        return 0;
    }

}

/**
 * Locate a particular node. Returns the node index if found, or -1 on failure.
 */
int search(LinkedList *list, Node *itemNode) {
    if (list == NULL) {
        printf("Error: list is NULL.");
        return -1;
    } else {
        if (list->size == 0) {
            printf("Error: list is NULL.");
            return -1;
        }
        Node *currNode = list->head;
        for (int i = 0; i < list->size; i++) {
            if (currNode->next == itemNode->next && currNode->data == itemNode->data) {
                return i;
            }
        }

    }


}

// TODO each function should return 0 to indicate success, -1 on failure.

int main(void)
{
    /* start with empty list */
    struct list_node* head = NULL;

    return 0;
}
