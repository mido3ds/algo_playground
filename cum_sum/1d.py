def pre(arr):
    cum_sum = [arr[0]] * len(arr)

    for i in range(1, len(arr)):
        cum_sum[i] = cum_sum[i - 1] + arr[i]

    return cum_sum

def sum_range(start, end, cum_sum):
    if start == 0: return cum_sum[end]
    else: return cum_sum[end] - cum_sum[start - 1]
    
if __name__=='__main__':
    arr = [1,2,3,4]
    cum = pre(arr)

    print(sum_range(0, 1, cum))
    print(sum_range(0, 3, cum))
    print(sum_range(1, 2, cum))
    print(sum_range(1, 3, cum))