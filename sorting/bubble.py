def bubble_sort(a):
    has_swaped = True
    size = len(a)
    while has_swaped:
        has_swaped = False
        i, i2 = 0, 1
        while i2 < size:
            if a[i] > a[i2]:
                #swap
                a[i], a[i2] = a[i2], a[i]
                has_swaped = True
            i = i2
            i2 += 1
        size -= 1

    return a

from numpy import random
a = [int(i) for i in random.randn(5) * 100]
def test():
    bubble_sort(a)

import timeit 
print(timeit.timeit(stmt='test()', setup='from __main__ import bubble_sort, test', number=1))