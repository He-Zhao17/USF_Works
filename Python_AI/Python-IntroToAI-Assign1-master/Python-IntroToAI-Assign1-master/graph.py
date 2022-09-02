from collections import deque
### graph.py
### an implementation of a graph using an adjacency list.

## helper class representing a node in a graph. For the moment, nodes
## only have names. Later, we will add state variables.

class Node() :
    def __init__(self, n):
        self.name = n

    def __hash__(self):
        return hash(self.name)

### an edge is a link between two nodes. Right now, the only other
### information an edge carries is the weight of the link. Later we
### will add other annotations.

class Edge() :
    def __init__(self, src, dest, weight) :
        self.src = src
        self.dest = dest
        self.weight = weight

### The graph class itself.
### The nodeTable is a dictionary that maps names to Node objects.
### (this keeps us from having to repeatedly search edgeMap.keys())

### The edgeMap is a dictionary that maps nodes to lists of Edges emanating from that node.

class Graph() :

    def __init__(self):
        self.nodeTable = {}
        self.edgeMap = {}

    ### implements the 'in' keyword. Returns true if the node is in the graph.
    def __contains__(self, item):
        for i in self.nodeTable :
            if self.nodeTable[i] == item :
                return True
        return False
        # return item in self.nodeTable

    def getNode(self, src):
        return self.nodeTable[src]

    def addNode(self, src):
        if src not in self.nodeTable :
            self.nodeTable[src] = Node(src)

    def addEdge(self, src, dest, weight):
        e = Edge(src,dest,weight)
        self.addNode(src)
        self.addNode(dest)
        if src in self.edgeMap :
            self.edgeMap[src].append(e)
        else :
            self.edgeMap[src] = [e]


    ## Assume file is in the mtx format: % is a comment
    ## Otherwise it's source destination weight
    ### The file in the github repo will work as a sample for you.
    ### It's in the format: source, vertex, weight. You should assume that the graph is symmetric -
    ### if there's an edge from a to b, there's an edge from b to a.
    ### You can find lots of others here: http://networkrepository.com/index.php
    def readFromFile(self, fname):
        with open(fname) as f :
            for l in f.readlines() :
                if not l.startswith("%") :
                    (s,d,w) = l.split()
                    self.addEdge(s,d,w)

    ### inputs are the name of a startNode and endNode. Given this,
    ### return a list of Nodes that indicates the path from start to finish, using breadth-first search.

    def breadthFirstSearch(self, startNode, endNode):
        deque1 = deque()
        result = list()
        result.append(startNode)
        currentNode = startNode
        currentNodeList = self.edgeMap[startNode]
        while currentNode != endNode :
            currentNodeList = self.edgeMap[currentNode]
            for tempedge in currentNodeList :
                deque1.append(tempedge.dest)
                result.append(tempedge.dest)
            if len(deque1) == 0 :
                return result
            currentNode = deque1.popleft()
        return result
        # pass

    ### inputs are the name of a startNode and endNode. Given this,
    ### return a list of Nodes that indicates the path from start to finish, using depth-first search.

    def depthFirstSearch(self, startNode, endNode):
        stack = deque()
        nodeDic = self.nodeTable
        edgeDic = self.edgeMap
        currentNode = startNode
        result = list()
        result.append(currentNode)
        while currentNode != endNode :
            if len(edgeDic[currentNode]) == 0 :
                if len(stack) == 0 :
                    return result
                else :
                    currentNode = stack.pop()
            else :
                stack.append(currentNode)
                pointerEdge = edgeDic[currentNode][0]
                edgeDic[currentNode].pop(pointerEdge)
                currentNode = pointerEdge.dest
                result.append(currentNode)
        return result
        # pass

    ### implement Djikstra's all-pairs shortest-path algorithm.
    ### https://yourbasic.org/algorithms/graph/#dijkstra-s-algorithm
    ### return the array of distances and the array previous nodes.

    # I return a dictionary.
    def minDic(self, par) :
        Inf = float('inf')
        min = Inf
        minNode = ""
        for item in par :
            if par[item] < min :
                min = par[item]
                minNode = item
        return minNode




    def djikstra(self, startNode):
        Inf = float ('inf')
        dist = dict()
        par = dict()
        prev = dict()
        dist[startNode] = 0
        prev[startNode] = startNode
        for item in self.nodeTable :
            if item != startNode :
                par[item] = Inf
        currentNode = startNode
        while len(par) > 0 :
            for item in self.edgeMap[currentNode] :
                if par.has_key(item) :
                    if item.weight + dist[currentNode] < par[item.dest]:
                        par[item.dest] = item.weight + dist[currentNode]
            prev[self.minDic(par)] = currentNode
            currentNode = self.minDic(par)
            dist[currentNode] = par[currentNode]
            par.pop(currentNode)
        return dist, prev
        # pass

    ### takes as input a starting node, and computes the minimum spanning tree, using Prim's algorithm.
    ### https:// en.wikipedia.org/wiki/Prim % 27s_algorithm
    ### you should return a new graph representing the spanning tree generated by Prim's.
    def GetMinEdge (self, edges) :
        Inf = float('inf')
        mintemp = Inf
        minEdge = Edge()
        for item in edges :
            if item.weight < mintemp :
                minEdge = item
                mintemp = item.weight

        return minEdge

    def prim(self, startNode):
        result = list()
        reachedNode = {startNode}
        unreachedNode = list()
        for item in self.nodeTable :
            if item != startNode :
                unreachedNode.append(item)
        edges = list()
        while len(unreachedNode) > 0 :
            for item in reachedNode :
                for i in self.edgeMap[item] :
                    edges.append(i)
            reachedNode.append(self.GetMinEdge(edges).dest)
            unreachedNode.pop(self.GetMinEdge(edges).dest)
            result.append((self.GetMinEdge(edges).src, self.GetMinEdge(edges).dest))
            edges.clear()
        return result

        # pass

    ### 686 students only ###
    ### takes as input a startingNode and returns a list of all nodes in the maximum clique containing this node.
    ### https://en.wikipedia.org/wiki/Clique_problem#Finding_a_single_maximal_clique

    def clique(self, startNode):
        pass