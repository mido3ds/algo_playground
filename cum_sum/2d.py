from copy import deepcopy

def pre(arr):
    cum = deepcopy(arr)

    for i in range(len(arr)):
        for j in range(1, len(arr[i])):
            cum[i][j] += cum[i][j - 1]

    for i in range(len(arr[0])):
        for j in range(1, len(arr)):
            cum[j][i] += cum[j - 1][i]

    return cum

def sum_range(s, e, cum_sum):
    cum = lambda s: cum_sum[s[0]][s[1]]

    if s == (0, 0): return cum(e)
    else:
        b = (e[0], s[1])
        d = (s[0], e[1])
        return cum(e) - cum(b) - cum(d) + cum(s) 

arr = [
    [1,2,3,4],
    [5,6,7,8],
    [9,10,11,12],
    [14,15,16,17],
]

cum = pre(arr)

print(sum_range((0, 0), (1, 1), cum))  # 14
print(sum_range((0, 0), (1, 2), cum)) # 24
print(sum_range((0, 0), (2, 2), cum)) # 54
print(sum_range((0, 0), (3, 0), cum)) # 29