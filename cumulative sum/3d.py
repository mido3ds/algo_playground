from copy import deepcopy

def pre(arr):
    cum = deepcopy(arr)
    
    # x
    for z in range(len(cum)):
        for y in range(len(cum[0])):
            for x in range(1, len(cum[0][0])):
                cum[z][y][x] += cum[z][y][x - 1]

    # y
    for z in range(len(cum)):
        for x in range(len(cum[0][0])):
            for y in range(1, len(cum[0])):
                cum[z][y][x] += cum[z][y - 1][x]


    # z
    for y in range(len(cum[0])):
        for x in range(len(cum[0][0])):
            for z in range(1, len(cum)):
                cum[z][y][x] += cum[z - 1][y][x]

    return cum

def sum_range(s, e, c):
    cum = lambda x, y, z: c[z][y][x]

    x1 = s[0]
    y1 = s[1]
    z1 = s[2]
    x2 = e[0]
    y2 = e[1]
    z2 = e[2]

    if s == (0, 0, 0): return cum(x2, y2, z2)
    else:
        return cum( x2,y2,z2 ) - cum( x2, y2, z1 ) \
                - cum( x2, y1, z2 ) - cum( x1, y2, z2 ) \
                + cum( x1, y1, z2 ) + cum( x1, y2, z1 ) + cum( x2, y1, z1 ) \
                - cum( x1, y1, z1 ) 


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

print(sum_range((0,0,0), (1,0,0), cum_sum))
print(sum_range((0,0,0), (0,1,2), cum_sum))
print(sum_range((1,0,1), (2,0,1), cum_sum))
print(sum_range((0,1,1), (0,2,2), cum_sum))