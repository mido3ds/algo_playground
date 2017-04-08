#include <iostream>
using namespace std;

// for linked list testing
// #include "linked_list.h"
// int main()
// {
//     using namespace slist;
//     // make list
//     node* head = NewNode(6);

//     Push(head, 5);
//     Push(head, 8);
//     Push(head, 6857);
//     // remove(head, 1);
//     Insert(head, 99);

//     // pop(head);

//     node* test = Search(head, GET_VALUE, 6);
//     cout << test->value << '\n';

//     Print(head);
// }

// #include "queue.h"

// #define N 20

// int main()
// {
//     queue* q = NewQueue(N);

//     for (int i = 0; i < N; i++)
//     {
//         Enqueue(q, '#');
//         Print(q);
//     }

//     for (int i = 0; i < N; i++)
//     {
//         Dequeue(q);

//         if (q->ifront != -1)
//             Print(q);
//     }
// }

#include "stack.h"
#define N 10

int main()
{
    stack* s = NewStack(N);

    for (int i = 0; i < N + 3; i++)
    {
        Push(s, i % 3);
        if (i == 9)
            cout << "2 when i = 9 is firstly at index: " << Search(s, 2) << '\n';
        Print(s);
    }

    for (int i = 0; i < N; i++)
    {w
        Pop(s);
        Print(s);
    }


}