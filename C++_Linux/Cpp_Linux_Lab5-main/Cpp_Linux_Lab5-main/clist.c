#include <sys/types.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "clist.h"

struct clist {
    size_t insertions;
    size_t capacity;
    size_t item_sz;
    void *element_storage;
};

struct clist_iterator {
    int initialized : 1;
    size_t idx;
};

// TODO: implement clist functions here!

int main(void)
{
    struct clist *list = clist_create(5, sizeof(int));

    int x;
    x = 0;
    clist_add(list, &x);

    x = 1; clist_add(list, &x);
    x = 2; clist_add(list, &x);
    x = 3; clist_add(list, &x);
    x = 4; clist_add(list, &x);
    x = 5; clist_add(list, &x);
    x = 6; clist_add(list, &x);

    int *y = clist_get(list, 2);
    printf("y = %d\n\n", *y);

    void *elem;
    struct clist_iterator iter = clist_create_iter();
    while ((elem = clist_iterate(list, &iter)) != NULL) {
        int ie = *((int *) elem);
        printf("-> %d\n", ie);
    }

    puts("\n\n");

    struct clist_iterator iter2 = clist_create_iter();
    while ((elem = clist_iterate_rev(list, &iter2)) != NULL) {
        int ie = *((int *) elem);
        printf("-> %d\n", ie);
    }

    clist_destroy(list);

    return 0;
}
