''' 0 0 0 0 1 1 1 1 search binary to get index of first 1 '''

def bs_first(arr, val):
    start = 0; end = len(arr) - 1
    while start < end:
        mid = (start + end) // 2
        if val > arr[mid]: start = mid + 1
        elif val < arr[mid]: end = mid - 1
        elif end - start == 1: # special case, when len is 2 
            if arr[start] == val: return start
            elif arr[end] == val: return end
            else: return -1
        else: end = mid # make end shift to mid
    
    return end # we are interested in first one, which end goes to it


arr = [0, 0, 0, 0, 1, 1, 1, 1]
assert bs_first(arr, 0) == 0
assert bs_first(arr, 1) == 4

arr = [5,6, -10, 9, 9]
arr = sorted(arr)

assert bs_first(arr, 9) == 3

arr = [1,1]
assert bs_first(arr, 1) == 0

arr = [-2,1]
assert bs_first(arr, 1) == 1, bs_first(arr, 1)