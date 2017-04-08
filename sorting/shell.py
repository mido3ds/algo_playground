from insertion import insertion_sort
def shell_sort(a, inc):
    i, j = 0, 0

    while j < inc:
        # get numbers
        ToSort = []
        while i * inc + j < len(a):
            ToSort.append(a[i * inc + j])
            i += 1
        # i = 0

        # sort numbers
        insertion_sort(ToSort)

        # return numbers
        for i, num in list(enumerate(ToSort)):
            a[i * inc + j] = num
        i = 0
        j += 1

    return insertion_sort(a)

a = [-2, 99, -5, 0, 9, 4]
print("shell:", shell_sort(a, inc=3))
print("system:", sorted(a))