from random import choice

def qsort(A):
    if len(A) <= 1:
        return A
    
    pivot = choice(A)
    A.remove(pivot)

    A = partition(A, pivot)     # partition it and get the pivot index

    pivotindex = A.index(pivot)

    # recursion
    return qsort(A[:pivotindex]) + [pivot] + qsort(A[pivotindex + 1:])
    pass

def partition(A, pivot):
    """move all items > pivot to right, others to the left"""

    small = []
    big = []
    equal = 1
    for num in A:
        if num < pivot:
            small.append(num)
        elif num > pivot:
            big.append(num)
        else:
            equal += 1

    A = small + [pivot] * equal + big
    
    return A

A = [3, -1, 7, 11, 2, 0]
A = qsort(A)
print(A)

A2 = sorted(A)
print(A2)

print(A2 == A)