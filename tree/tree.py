#!/usr/bin/env python

class Tree(object):
    """Binary search tree"""

    def __init__(self, val=None):
        self.val = val
        # link to other nodes
        self.right = None
        self.left = None

    def add(self, num):
        if self.val is None:
            # val is not set
            self.val = num
        else:
            # val is set, look for another place
            self._add(self, num)

    def _add(self, node, num):
        if node is None:
            node = Tree(num)
            return node

        if num > node.val:
            self._add(node.right, num)
        else:
            self._add(node.left, num)

    def search(self, num):
        self._search(self, num)

    def _search(self, node, num):
        if node is None:
            return False

        if num == node.val:
            return True

        elif num > node.val:
            return self._search(node.right, num)

        elif num < node.val:
            return self._search(node.left, num)

    def printTree(self):
       self._print(self)

    def _print(self, node):
        if node is None:
            return

        print node.val
        
        self._print(node.left)
        print " ",
        self._print(node.right)


    
    
t = Tree(12)
t.add(4)
t.add(5)
t.add(7)

print t.search(5)
print t.search(7)
print t.search(12)

t.printTree()
