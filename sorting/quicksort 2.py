''' quicksort the recursive ,slow, way 
    the Haskell way
'''
def qs(arr):
    if len(arr) == 0: return []

    pivot = arr[0]
    small_part = qs([x for x in arr[1:] if x <= pivot])
    big_part = qs([x for x in arr[1:] if x > pivot])

    return small_part + [pivot] + big_part


# test
arrs = [
    [range(10, -200, -2)],
    [range(10, 200, 2)],
    [range(100, -200, -20)],
    [range(1, -2000, -100)],
    [],
    [1,2],
]

for arr in arrs:
    assert qs(arr) == sorted(arr), arr
    
print('OK')