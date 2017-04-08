class heap(object):
    def __init__(self):
        self.list = []

    def add(self, val):
        self.list.append(val)
        self.repair()

    def getParent(self, index):
        return self.list[(index -1) // 2]

    def getChild(self, index):
        return (2 * index + 1, 2 * index + 2)

    def repair(self, index):
        # must be greater than 
        c = self.getChild(index)

        if self.exist(c[0]):
            if (self.list[c[0]] > self.list[index]):
                self.swap(index, c[0])
                self.repair()

        if self.exist(c[1]):
            if (self.list[c[1]] > self.list[index]):
                self.swap(index, c[1])
        
        # must be smaller than parent
        p = self.getParent(index)
        if self.exist(p):
            if (self.list[p] < self.list[index]):
                self.swap(index, p)
    def _pushUp(self, index):
        # must be smaller than parent
        p = self.getParent(index)
        if self.exist(p):
            if (self.list[p] < self.list[index]):
                self.swap(index, p)
                return True     # maybe we need more push
            else:
                return False    # no need for more pushs

        else:
            return False
            
    def swap(self, i1, i2):
        self.list[i1], self.list[i2] = self.list[i2], self.list[i1]

    def exist(self, i):
        return i < len(self.list) and i >= 0