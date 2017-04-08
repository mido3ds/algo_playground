#pragma once 
#include <iostream>
using namespace std;

struct stack
{
    int* arr;
    int size;
    int peek;
};

stack* NewStack(const int &size)
{
    stack* s = new stack;
    if (s == nullptr)
        throw -1;

    if (size <= 0)
    {
        cerr << "ERROR: Size of stack is not allowed\n";
        throw 0;
    }
    s->arr = new int[size];

    s->size = size;
    s->peek = -1;
    return s;
}

void Push(stack* s,const int &num)
{
    if (++s->peek == s->size)
    {
        cerr << "ERROR: Out of boundries, array is full\n";
        throw -1;
    }

    s->arr[s->peek] = num;
}

void Pop(stack* s)
{
    if (s->peek == -1)
    {
        cerr << "ERROR: Stack is already empty\n";
        throw -1;
    }
    s->peek--;
}

void Print(stack* s)
{
    if (s->peek == -1)
    {
        cerr << "ERROR: attempt to print empty stack\n";
        return;
    }

    for (int i = 0; i <= s->peek; i++)
        cout << s->arr[i] << ' ';
    
    cout << '\n';
}

int Search(stack* s, const int &num)
{
    if (!s)
        throw -1;
    if (s->peek == -1)
    {
        cerr << "Stack is empty\n";
        return -1;
    }

    for (int i  = 0; i <= s->peek; i++)
        if (s->arr[i] == num)
            return i;
    
    return -1;
}