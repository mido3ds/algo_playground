def bisec(arr, val):
    start = 0; end = len(arr) - 1
    while start <= end:
        mid = (start + end) // 2
        if val > arr[mid]: start = mid + 1
        elif val < arr[mid]: end = mid - 1
        elif end - start == 1: # special case, when arr is 2 in size
            if arr[start] == val: return start
            elif arr[end] == val: return end
        else: return mid
    return None

arr = [1,2,3,4]
assert bisec(arr, 3) == arr.index(3)

arr = [12, 20, 100, 300, 5000]
assert bisec(arr, 300) == arr.index(300)

arr = [1,2]
assert bisec(arr, 2) == arr.index(2)

arr = [1,2,3,4]
assert bisec(arr, 300) == None

arr = [-1,-2,-3]
arr = sorted(arr)
assert bisec(arr, -3) == arr.index(-3)