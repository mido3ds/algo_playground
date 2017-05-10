''' [0 0 0 0 1 1 1 1] search binary to get index of last 1 '''

def bs_last(arr, val):
    start = 0; end = len(arr) - 1
    while start < end:
        mid = (start + end)//2
        if val > arr[mid]: start = mid + 1
        elif val < arr[mid]: end = mid - 1
        elif end - start == 1: # special case, when len is 2 
            if arr[end] == val: return end
            elif arr[start] == val: return start
            else: return -1
        else: start = mid

    return start

arr = [0, 0, 0, 0, 1, 1, 1, 1]
assert bs_last(arr, 1) == len(arr) - 1
assert bs_last(arr, 0) == 3

arr = [5,6,7,7,8,9,100]
assert bs_last(arr, 7) == 3

arr = [1,2]
assert bs_last(arr, 2) == 1

arr = [3,3,2]
assert bs_last(arr, 3) == 1