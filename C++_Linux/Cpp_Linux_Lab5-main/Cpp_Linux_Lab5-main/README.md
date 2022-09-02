# Lab 5: Circular List

In this lab, we'll take inspiration from our 'elist' data structure to build a circular list -- a 'clist'!


```

           ╔═══════════════════════════╗
           ║  A Circular Array (list)  ║
           ║         size = 5          ║
           ╚═══════════════════════════╝

insert(0)
insert(1)
insert(2)
insert(3)
insert(4)
insert(5)
insert(6)

                   ┌────── Head
                   │
                   ▼
          ┌─────┬─────┬─────┬─────┬─────┐
          │     │     │     │     │     │
          │  5  │  6  │  2  │  3  │  4  │
          │     │     │     │     │     │
          └─────┴─────┴─────┴─────┴─────┘
                         ▲
                         │
                         └─────── Next Insertion

get(2) = 2
get(1) = NULL
get(4) = 4
get(6) = 6
get(7) = NULL

NOTE: to simplify, the elements contents are the same as their
circular indices.
```
