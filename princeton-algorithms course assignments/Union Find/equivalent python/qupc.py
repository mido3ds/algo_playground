class UF:
    '''quick-union algo for UF with path compression
    best & optimized one '''

    def __init__(self, size):
        self.id = [i for i in range(size)]
        self.rank = [1 for i in range(size)]

    def union(self, a, b):
        a = self.get_root(a) 
        b = self.get_root(b) 

        if a == b:
            return
        # a must be bigger than b, or swap them
        elif self.rank[a] < self.rank[b]:
            a, b = b, a

        # a is now bigger than b, b should go to a
        self.id[b] = a
        self.rank[a] += self.rank[b]

    def is_connected(self, a, b):
        return self.get_root(a) == self.get_root(b)

    def get_root(self, a):
        while not self.is_root(a): 
            # path compression, make this node points to the root of this root
            # this makes the graph nearly flat
            self.id[a] = self.id[self.id[a]] 

            # move to the root of this point
            a = self.id[a]
        return a

    def is_root(self, a):
        return self.id[a] == a