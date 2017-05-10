from copy import deepcopy

def pre(arr):
    cum = deepcopy(arr)
    
    
    return cum

def sum_range(s, e, cum_sum):
    cum = lambda s: cum_sum[s[0]][s[1]][s[2]]

    if s == (0, 0, 0): return cum(e)
    else:
        pass 


arr = [
    [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9]
    ],
    [
        [10, 20, 30],
        [40, 50, 60],
        [70, 80, 90]
    ],
    [
        [100, 200, 300],
        [400, 500, 600],
        [700, 800, 900]
    ],
]

cum_sum = pre(arr)

print(cum_sum)