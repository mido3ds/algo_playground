class Node:
    def __init__(self, num):
        self.val = num
        self.right = None
        self.left = None

class Tree(Node):
    def __init__(self):
        self.root = None

    def add(self, num):
        if self.root is None:
            self.root = Node(num)
        else:
            self._add(self.root, num)

    def _add(self, node, num):
        """add to left if smaller than current, otherwise to right"""
        if num >= node.val:
            #add to right
            if node.right is not None:
                self._add(node.right, num)
            else:
                node.right = Node(num)
        else:
            #add to left
            if node.left is not None:
                self._add(node.left, num)
            else:
                node.left = Node(num)

    def find(self, num):
        return self._find(self.root, num)

    def _find(self, node, num):
        if node == None:
            return False
        
        if num == node.val:
            return True

        elif num > node.val:
            return self._find(node.right, num)

        elif num < node.val:
            return self._find(node.left, num)

    def bft(self):
        """breadth first traversal """
        q = [self.root]
        for node in q:
            print(node.val,)
            if node.left:
                q.append(node.left)
            if node.right:
                q.append(node.right)
            q.remove(node)
    
    def preorder(self):
        """preorder traversal"""
        self._preorder(self.root)

    def _preorder(self, node):
        if node is None:
            return

        print (node.val)
        self._preorder(node.left)
        self._preorder(node.right)

    def postorder(self):
        self._postorder(self.root)

    def _postorder(self, node):
        if node is None:
            return

        self._postorder(node.left)
        self._postorder(node.right)
        print (node.val)

    def inorder(self):
        self._inorder(self.root)

    def _inorder(self, node):
        if node is None:
            return

        self._inorder(node.left)
        print (node.val)
        self._inorder(node.right)

    def remove(self, num):
        node = self._getnode(self.root, num)
        return self._remove(node)

    def _remove(self, node):
        """if node has no children
            delete it

            if it has children 
            get max/min in left/right subtree, copy it to this node and remove that max/min
         """
        # delete it safely
        if node == None:
            return False

        if node.left == node.right == None:
            del node
            
        else:
            max = self._getmax(node.left) 
            node.val = max
                
        return True

    def max(self):
        return self._getmax(self)

    def _getmax(self, node):
        if node.right == None:
            return node.val
        else:
            self._getmax(node.right)

    def _getmax_anddel(self, node):
        if node.right == None:
            temp = node.val
            del node.val
            del node
            return temp
        else:
            self._getmax(node.right)
    
    def _getnode(self, node, num):
        if node == None:
            return None

        if num == node.val:
            return node

        elif num > node.val:
            return self._getnode(node.right, num)

        elif num < node.val:
            return self._getnode(node.left, num)
# test tree


t = Tree()
list = [5, 7, 2, -1, 0, 20, 6]
for num in list:
    t.add(num)

t.remove(7)
t.preorder()