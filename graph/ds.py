class Vertix(object):
    def __init__(self, value):
        self.value = value
        self.edges = {}
        self.color = 'white'

    def addEdge(self, e, weigh=None):
        self.edges[e] = weigh

    def removeEdge(self, e):
        try: del self.edges[e]
        except: print('edge {} not found to be deleted from vertix {}'.format(e, self.value))

    def __len__(self):
        return len(self.edges.keys())

    def getWeigh(self, e):
        return self.edges[e]

    def getWeighs(self):
        return self.edges.values()

    def getEdges(self):
        return self.edges.keys()

    def __iter__(self):
        return iter(self.edges.items())

    def __str__(self):
        return 'Vertix #' + str(self.value) + ' has edges ' + str(list(self.edges.keys()))
    def __contains__(self, item):
        return item in self.edges
class Graph(object):
    def __init__(self, descr=None, random=False):
        self.vertices = {}

        if descr:
            if random:
                self._createRadnomGraph(descr)
            else:
                self._creatGraph(descr)

    def _creatGraph(self, description):
        for vertix, edges in description.items():
            for edge, weigh in edges.items():
                self.addEdge(vertix, edge, weigh)

    def _createRadnomGraph(self, descr):
        # create random graph following descr
        from random import randint
        for vertix in range(descr["begin"], descr["end"] + 1):
            for edge in range( randint(descr["min edges"], descr["max edges"]) ):
                edge = randint(descr["begin"], descr["end"])
                weigh = randint(0, 100)

                self.addEdge(vertix, edge, weigh)


    def addVertix(self, key):
        if isinstance(key, Vertix):
            key = key.value
        newVer = Vertix(key)
        self.vertices[key] = newVer
        return newVer

    def addEdge(self, v, e, weigh=None):
        if isinstance(v, Vertix):
            v = v.value
        if isinstance(e, Vertix):
            e = e.value

        if v not in self.vertices:
            self.addVertix(v)
        if e not in self.vertices:
            self.addVertix(e)
        self.vertices[v].addEdge(e, weigh)

    def __iter__(self):
        return iter(self.vertices.values())
    def __contains__(self, item):
        return item in self.vertices
    def __str__(self):
        to_print = {}
        for v, e in self.vertices.items():
            to_print[v] = e.edges

        import json
        return json.dumps(to_print, indent=4)

    def resetColors(self):
        for vertix in self:
            vertix.color = 'white'

    def traverse_depth(self, start, func=None):
        # start must be Vertix object
        if not isinstance(start, Vertix):
            start = self.vertices[start]
            
        self.resetColors()
        start.color = 'black'

        stack = Stack()
        stack.push(start)

        process_me = []
        while stack.notEmpty():
            currentVert = stack.pop()

            process_me.append(str(currentVert.value))

            # others
            for edge, weigh in currentVert:
                edge = self.vertices[edge]
                if edge.color == 'white':
                    edge.color = 'black'
                    stack.push(edge)

        if func:
            for element in process_me:
                func(element)

        return process_me


    def traverse_breadth(self, start, func=None):
        # start must be Vertix object
        if not isinstance(start, Vertix):
            start = self.vertices[start]

        self.resetColors()
        start.color = 'black'

        q = Queue()
        q.enque(start)

        process_me = []
        while q.notEmpty():
            currentVert = q.deque()

            process_me.append(str(currentVert.value))

            # include others
            for edge, weigh in currentVert:
                edge = self.vertices[edge]
                if edge.color == 'white':
                    edge.color = 'black'
                    q.enque(edge)

        if func:
            for element in process_me:
                func(element)
        
        return process_me
    def mst(self, start=None):
        # start should be an object
        if start:
            start = self.vertices[start]
        else:
            # pick anyone 
            start = list(self.vertices.keys())[0]
        
        self.resetColors()
        new_graph = Graph()
        new_graph.addVertix(start)

        while len(new_graph) != len(self):
            new_graph = self._getLeastEdges_mst(new_graph)
        
        self._repair_for_mst(new_graph)
        return new_graph
    
    def _repair_for_mst(self, new_graph):
        ''' make graph undirected after mst mess '''
        for v in new_graph:
            for edge, weigh in v:
                new_graph.addEdge(edge, v, weigh)

    def _getLeastEdges_mst(self, new_graph):
        edges = set()
        for vertix in new_graph:
            # we work with the vertix in the original graph
            vertix = self.vertices[vertix.value]

            # store all available edges 
            # that are not in new_graph
            for edge, weigh in vertix:
                if edge not in new_graph:
                    edges.add((vertix.value, edge, weigh))

        if edges:
            # get the minimum edge in weigh
            minimum_edge = min(edges, key=lambda x: x[2])

            # add it to the new_graph
            new_graph.addEdge(minimum_edge[0], minimum_edge[1], minimum_edge[2])

            # not to appear again
            edges.remove(minimum_edge)

        return new_graph

    def __len__(self):
        return len(self.vertices)
import collections
class Queue(collections.deque):
    enque = collections.deque.append
    deque = collections.deque.popleft
    def notEmpty(self):
        return len(self) != 0

class Stack(collections.deque):
    push = collections.deque.append
    pop = collections.deque.pop
    def notEmpty(self):
        return len(self) != 0