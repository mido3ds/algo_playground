''' bitonic array is increasing-decreasing or vice versa.. 
    1 2 3 4 [5] 4 3 2
    or -2 [100] 99 12 20 0

    write funciton max_bitonic to get max by binary search given inc-dec bitonic list
'''

def max_bitonic(arr):
    start = 0; end = len(arr) - 1
    while end - start > 1: # len is >= 2
        mid = (start + end) // 2
        if arr[mid] > arr[mid+1] and arr[mid] > arr[mid-1]: 
            return arr[mid]
        elif arr[mid-1] > arr[mid] > arr[mid+1]: 
            # go left
            end = mid
        else:
            # go right
            start = mid
    
    return max(arr[start:end+1])

    
arr = [-2, 100, 99, 12, 20, 0]
assert max_bitonic(arr) == max(arr)

arr = [0, 0, 0, 1, 0, -1]         
assert max_bitonic(arr) == max(arr)

arr = [-100, -99, -98, -99]
assert max_bitonic(arr) == max(arr)

arr = [0, 1, -1]
assert max_bitonic(arr) == max(arr)

arr = [0, 1]
assert max_bitonic(arr) == max(arr)

arr = [0]
assert max_bitonic(arr) == max(arr)