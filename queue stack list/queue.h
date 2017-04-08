#pragma once 
#include <iostream>
using namespace std;

struct queue
{
    int* q;
    int size;
    int ifront, iback;
};

queue* NewQueue(const int &size)
{
    queue* q = new queue;
    q->size = size;
    q->q = new int[size];
    if (q == nullptr)
    {
        cerr << "Can't allocate memory for queue\n";
        throw -1;
    }

    q->iback = q->ifront = -1;
    return q;
}

bool IsEmpty(queue* q)
{
    return (q->ifront == -1 && q->iback == -1);
}

bool IsFull(queue* q)
{
    return ((q->iback + 1) % q->size == q->ifront);
}

void Enqueue(queue* q, const int &num)
{
    if (IsFull(q))
    {
        cerr << "ERROR: Queue is Full\n";
        return;
    }
    else if (IsEmpty(q))
    {
        // just move the indexes to zero to start adding numbers
        q->iback = q->ifront = 0;
    }
    else    // you can add it normally
    {
        q->iback++;
    }
    q->q[q->iback] = num;
}

void Dequeue(queue* q)
{
    if (IsEmpty(q))
    {
        cerr << "ERROR, queue is empty\n";
        return;
    }
    else if (q->iback == q->ifront)     // deleting first node, they equall 0 and should get to -1
    {
        q->iback = q->ifront = -1;
    }
    else    // deleting normally
    {
        q->ifront++;
    }
}

int GetFirst(queue* q)
{
    // empty
    if (IsEmpty(q))
    {
        cerr << "ERROR: queue empty\n";
        throw -1;
    }

    // not empty
    return q->q[q->ifront];
}

int GetLast(queue* q)
{
    if (IsEmpty(q))
    {
        cerr << "ERROR: empty queue\n";
        throw -1;
    }

    return q->q[q->iback];
}

void Print(queue* q)
{
    if (IsEmpty(q))
    {
        cerr << "Empty queue\n";
        return ;
    }

    // iterate through elements from front to rear
    for (int i = q->ifront;; i = ++i % q->size)
    {
        cout << q->q[i] << "  ";

        if (i == q->iback)  // reached rear and printed it
        {
            printf("\n");
            return;
        }
    }
}