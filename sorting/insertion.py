import numpy as np
def insert(A, i, num):
    while i != 0 and num < A[i - 1]:
        A[i] = A[i - 1]
        i -= 1
    A[i] = num

def insertion_sort(A):
    for i, num in list(enumerate(A)):
        insert(A, i, num)
    return A

def main():
    A = np.random.randn(5000) * 100
    insertion_sort(A)

    A2 = sorted(A)

    print(A2 == A)