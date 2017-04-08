class UF:
    def __init__(self, size):
        self.arr = [i for i in range(size)]

    def union(self, a, b): 
        a = self.get_root(a)
        b = self.get_root(b)
        self.arr[a] = b
        
    def is_connected(self, a, b):
        return self.get_root(a) == self.get_root(b)

    def get_root(self, a):
        while self.arr[a] != a: a = self.arr[a]
        return a