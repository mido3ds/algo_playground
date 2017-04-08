class UF(object):
    ''' quick-find '''
    
    def __init__(self, size):
        self.arr = [i for i in range(size)]

    def union(self, a, b):
        for i, _ in enumerate(self.arr):
            if self.arr[i] == self.arr[b]:
                self.arr[i] = self.arr[a]

    def is_connected(self, a, b):
        return self.arr[a] == self.arr[b]

    def __len__(self):
        return len(self.arr)