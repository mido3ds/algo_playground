#pragma once

#include <iostream>
using namespace std;


/* single linked list */
namespace slist
{
    struct node
    {
        int value;
        node* next;
    };

    enum SEARCH_MODE
    {
        GET_LAST, GET_LAST_PREV, GET_INDEX, GET_INDEX_PREV, GET_VALUE, GET_VALUE_PREV
    };

    /* functions prototypes */
    node* NewNode(const int);
    int Remove(node*, const int &);
    node* Push(node* , const int &);
    int Pop(node*);
    int Print(node*, int);
    node* Search(node*, SEARCH_MODE, const int, node**);
    node* Search(node*, SEARCH_MODE, node**);
    node* Insert(node*, const int &, const int);

    /* functions implementation  */

    /* returns pointer to new node, which next ptr is initialized to zero
    *  not connected to anything
    *  allocated dynamically
    */
    node* NewNode(const int value = 0)
    {
        node* n = new node;
        n->value = value;
        n->next = nullptr;

        return n;
    }

    /*  search in list according to the chosen mode
    *   complexity: O(n)
    *   GET_INDEX, returns ptr to element at index and if (index >= list size or index < 0) returns nullptr.
    *   GET_VALUE, returns ptr to element if found, otherwise it returns nullptr
    *   to get ptr to the previous node, pass it address of node pointer
    */
    node* Search(node* head, SEARCH_MODE mode, const int input, node** before = nullptr)
    {
        if (head == nullptr)
            return nullptr; // empty

        if (before != nullptr)
        {
            if (mode == GET_INDEX)
                mode = GET_INDEX_PREV;
            else 
                mode = GET_VALUE_PREV;
        }

        switch (mode)
        {
            case GET_INDEX:
                if (input < 0)
                    return nullptr;     // no index = 0
                for (int i = 0; i < input; i++)
                {
                    if (head->next == nullptr)
                        return nullptr;     // out of boundries
                    head = head->next;
                }
                break;

            case GET_INDEX_PREV:
                if (input < 0)
                    return nullptr;     // no index = 0
                for (int i = 0; i < input; i++)
                {
                    if (head->next == nullptr)
                        return nullptr;     // out of boundries
                    *before = head;
                    head = head->next;
                }
                break;

            case GET_VALUE:
                while (head->next != nullptr && head->value != input)
                    head = head->next;
                if (head->value != input)
                    head = nullptr;     // not found
                break;

            case GET_VALUE_PREV:
                while (head->next != nullptr && head->value != input)
                {
                    *before = head;
                    head = head->next;
                }
                if (head->value != input)
                    head = nullptr;     // not found
                break;

            default:
                return nullptr;     // wrong mode, error
        }

        return head;
    }

    /*  Overloaded search function
    *   GET_LAST, returns ptr to last element
    *   to get ptr to the previous node, pass it address of node pointer
    */
    node* Search(node* head, SEARCH_MODE mode = GET_LAST, node** before = nullptr)
    {
        if (head == nullptr)
            return nullptr; // empty

        if (before != nullptr)
            mode = GET_LAST_PREV;

        switch (mode)
        {
            case GET_LAST:
                while (head->next != nullptr)
                    head = head->next;
                break;
            
            case GET_LAST_PREV:
                while (head->next != nullptr)
                {
                    *before = head;
                    head = head->next;
                }
                break;
            default:
                return nullptr;     // wrong mode, error
        }

        return head;
    }

    /*  deletes a node from the list, complexity: O(n)
    *   returns 0 on succcess, negative otherwise
    */
    int Remove(node* head, const int &index)
    {
        if (index < 0)
            return -1;      // unvalid

        node* prevn;
        node* n = Search(head, GET_INDEX, index, &prevn);
        if (n == nullptr)
            return -2;      // not found

        // link prevoius node with the next one
        if (prevn != nullptr)
            prevn->next = n->next;

        delete n;

        // success 
        return 0;
    }

    /*  Inserts value at given index, if not given it Pushes it, complexity: O(n)
    *   return ptr to node on success, otherwise nullptr
    */
    node* Insert(node* head, const int &value, const int index = -1)
    {
        // if called without index
        if (index == -1)
            return Push(head, value);
        
        else if (index < -1)
            return nullptr;     // invalid
        
        else if (head == nullptr)
            return nullptr;      // empty

        node* prev;
        node* n = Search(head, GET_INDEX, index, &prev);

        node* newn = NewNode(value);

        prev->next = newn;      // link previous with this node
        newn->next = n;         // link this node to next

        // now newn is at index

        return newn;
    }

    /*  Pushes element to the end of list, complexity :O(n) 
    *   input: head, and value to Insert, returns 0 on success, otherwise on failure
    */
    node* Push(node* head, const int &value)
    {
        // check if empty
        if (head == nullptr)
            return nullptr;  // failure, empty

        // get the last element
        head = Search(head, GET_LAST);

        // make new node
        node* newn = NewNode(value);

        // link it with the previous
        head->next = newn;

        // success
        return newn;
    }

    /*  deletes last element in list, complexity: O(n)
    *   input: head, returns 0 on success, otherwise on failure
    */
    int Pop(node* head)
    {
        if (head == nullptr)
            return -1; // empty

        // get last two elements
        node* prev;
        head = Search(head, GET_LAST_PREV, &prev);

        delete head;

        // prev is the last element
        prev->next = nullptr;

        // success
        return 0;
    }

    /*  iterates through list and prints elements on one line
    *   input: head, index to stop at. return 0 on success, negative otherwise
    *   if index >= list size or if no value was passed, it prints all elements
    *   complexity: O(n)
    */ 
    int Print(node* head, int index = -1)
    {
        if (head == nullptr)
            return -1; // empty

        for (int i = 0; (index == -1 || i < index) && head != nullptr; i++)
        {
            printf("%d ", head->value);
            head = head->next;
        }
        printf("\n");

        return 0;
    }
}