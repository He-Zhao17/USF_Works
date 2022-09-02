import copy
import state
from queue import Queue, LifoQueue
import heapq


### An abstract class that other states will inherit from.
class State:

    def __init__(self):
        pass

    def isGoal(self):
        pass

    def successors(self):
        pass

    def __repr__(self):
        pass


class Node:
    def __init__(self, arr):
        self.array = arr
        self.parent = None
        self.g = 0
    def __repr__(self):
        return "%s" % (self.array)


class EightPuzzleState(State):
    def __init__(self, start):
        self.array = start

    def isGoal(self, arr):
        for i in range(8):
            if arr[i] == i + 1:
                continue
            else:
                return False

        if arr[8] == 0:
            return True
        else:
            return False

    # def isValidState

    def __repr__(self):
        res = ""
        for i in range(9):
            if i == 2 or i == 5:
                res += "%s\n" % (self.array[i])
            else:
                if i == 8:
                    res += "%s" % (self.array[8])
                else:
                    res += "%s " % (self.array[i])
        return res

    # def successors(self):

    def slideBlankLeft(self, array, bIndex):
        arr = copy.copy(array)
        arr[bIndex] = arr[bIndex - 1]
        arr[bIndex - 1] = 0
        return arr

    def slideBlankRight(self, array, bIndex):
        arr = copy.copy(array)
        arr[bIndex] = arr[bIndex + 1]
        arr[bIndex + 1] = 0
        return arr

    def slideBlankUp(self, array, bIndex):
        arr = copy.copy(array)
        arr[bIndex] = arr[bIndex - 3]
        arr[bIndex - 3] = 0
        return arr

    def slideBlankDown(self, array, bIndex):
        arr = copy.copy(array)
        arr[bIndex] = arr[bIndex + 3]
        arr[bIndex + 3] = 0
        return arr

    @property
    def successors(self):
        queue = Queue(maxsize=0)
        res = None
        rootNode = Node(self.array)
        bfs = 1
        bfsCL = 1
        for i in range(9):
            if self.array[i] == 0:
                bIndex = i
        if self.isGoal(self.array):
            res = rootNode
        else:
            queue.put(rootNode)
            closedList = {bIndex: [rootNode.array]}
            while not queue.empty():
                pointer = queue.get()
                bIndex = 0
                for i in range(9):
                    if pointer.array[i] == 0:
                        bIndex = i


                if bIndex != 0 and bIndex != 3 and bIndex != 6:
                    temp = self.slideBlankLeft(pointer.array, bIndex)
                    bfs = bfs + 1
                    if self.clSearch(closedList.get(bIndex - 1), temp) == False:
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        if self.isGoal(temp):
                            res = tempNode
                            bfsCL = bfsCL + 1
                            break
                        else:
                            queue.put(tempNode)
                            bfsCL = bfsCL + 1
                            if closedList.get(bIndex - 1) is None:
                                closedList[bIndex - 1] = [temp]
                            else:
                                closedList[bIndex - 1].append(temp)



                if bIndex != 2 and bIndex != 5 and bIndex != 8:
                    temp = self.slideBlankRight(pointer.array, bIndex)
                    bfs = bfs + 1
                    if self.clSearch(closedList.get(bIndex + 1), temp) == False:
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        if self.isGoal(temp):
                            res = tempNode
                            bfsCL = bfsCL + 1
                            break
                        else:
                            queue.put(tempNode)
                            bfsCL = bfsCL + 1
                            if closedList.get(bIndex + 1) is None:
                                closedList[bIndex + 1] = [temp]
                            else:
                                closedList[bIndex + 1].append(temp)

                if bIndex != 0 and bIndex != 1 and bIndex != 2:
                    temp = self.slideBlankUp(pointer.array, bIndex)
                    bfs = bfs + 1
                    if self.clSearch(closedList.get(bIndex - 3), temp) == False:
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        if self.isGoal(temp):
                            res = tempNode
                            bfsCL = bfsCL + 1
                            break
                        else:
                            queue.put(tempNode)
                            bfsCL = bfsCL + 1
                            if closedList.get(bIndex - 3) is None:
                                closedList[bIndex - 3] = [temp]
                            else:
                                closedList[bIndex - 3].append(temp)

                if bIndex != 6 and bIndex != 7 and bIndex != 8:
                    temp = self.slideBlankDown(pointer.array, bIndex)
                    bfs = bfs + 1
                    if self.clSearch(closedList.get(bIndex + 3), temp) == False:
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        if self.isGoal(temp):
                            res = tempNode
                            bfsCL = bfsCL + 1
                            break
                        else:
                            queue.put(tempNode)
                            bfsCL = bfsCL + 1
                            if closedList.get(bIndex + 3) is None:
                                closedList[bIndex + 3] = [temp]
                            else:
                                closedList[bIndex + 3].append(temp)

        # pointer = res
        # sta = LifoQueue(0)
        # while not pointer:
        #     sta.put(pointer.array)
        #     pointer = pointer.parent
        # result = []
        # while not sta.empty():
        #     result.append(sta.get())
        # return result
        return bfsCL

    def DFS(self):
        sta = LifoQueue(0)
        res = None
        dfs = 1
        rootNode = Node(self.array)
        if self.isGoal(self.array):
            res = rootNode
        else:
            sta.put(rootNode)
            for i in range(9):
                if rootNode.array[i] == 0:
                    bIndex = i
            closeList = {bIndex : [rootNode.array]}
            while not sta.empty():
                pointer = sta.get()
                for i in range (9):
                    if pointer.array[i] == 0:
                        bIndex = i
                if bIndex != 0 and bIndex != 3 and bIndex != 6:
                    temp = self.slideBlankLeft(pointer.array, bIndex)
                    if self.isGoal(temp):
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        res = tempNode
                        dfs = dfs + 1
                        break
                    if self.clSearch(closeList.get(bIndex - 1), temp) == False:
                        dfs = dfs + 1
                        sta.put(pointer)
                        print(tempNode)
                        print(dfs)
                        print(sta.qsize())
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        sta.put(tempNode)
                        if closeList.get(bIndex - 1) is None:
                            closeList[bIndex - 1] = [temp]
                        else:
                            closeList[bIndex - 1].append(temp)
                        continue
                if bIndex != 2 and bIndex != 5 and bIndex != 8:
                    temp = self.slideBlankRight(pointer.array, bIndex)
                    if self.isGoal(temp):
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        res = tempNode
                        dfs = dfs + 1
                        break
                    if self.clSearch(closeList.get(bIndex + 1), temp) == False:
                        sta.put(pointer)
                        dfs = dfs + 1
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        sta.put(tempNode)
                        print(tempNode)
                        print(dfs)
                        print(sta.qsize())
                        if closeList.get(bIndex + 1) is None:
                            closeList[bIndex + 1] = [temp]
                        else:
                            closeList[bIndex + 1].append(temp)
                        continue
                if bIndex != 0 and bIndex != 1 and bIndex != 2:
                    temp = self.slideBlankUp(pointer.array, bIndex)
                    if self.isGoal(temp):
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        res = tempNode
                        dfs = dfs + 1
                        break
                    if self.clSearch(closeList.get(bIndex - 3), temp) == False:
                        sta.put(pointer)
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        sta.put(tempNode)

                        dfs = dfs + 1
                        print(tempNode)
                        print(dfs)
                        print(sta.qsize())
                        if closeList.get(bIndex - 3) is None:
                            closeList[bIndex - 3] = [temp]
                        else:
                            closeList[bIndex - 3].append(temp)
                        continue
                if bIndex != 6 and bIndex != 7 and bIndex != 8:
                    temp = self.slideBlankRight(pointer.array, bIndex)
                    if self.isGoal(temp):
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        res = tempNode
                        dfs = dfs + 1
                        break
                    if self.clSearch(closeList.get(bIndex + 3), temp) == False:
                        sta.put(pointer)
                        tempNode = Node(temp)
                        tempNode.parent = pointer
                        sta.put(tempNode)
                        dfs = dfs + 1
                        print(tempNode)
                        print(dfs)
                        print(sta.qsize())
                        if closeList.get(bIndex + 3) is None:
                            closeList[bIndex + 3] = [temp]
                        else:
                            closeList[bIndex + 3].append(temp)
                        continue

        return dfs




    def AStar(self):
        oList = proQueue()
        cList = {}
        pointer = Node(self.array)
        pointer.g = 0
        Astar = 1
        if self.isGoal(pointer.array):
            return Astar
        for i in range(9):
            if pointer.array[i] == 0:
                bIndex = i
        # cList[i] = [pointer.array]
        oList.push(pointer, self.Man(pointer.array) + pointer.g)
        while not oList.empty():
            pointer = oList.pop()[2]
            print(pointer.array)
            print(len(oList.q))
            print(Astar)
            for i in range(9):
                if pointer.array[i] == 0:
                    bIndex = i
            if self.clSearch(cList.get(i), pointer.array) == True:
                continue
            if bIndex != 0 and bIndex != 3 and bIndex != 6:
                temp = self.slideBlankLeft(pointer.array, bIndex)
                tempNode = Node(temp)
                tempNode.parent = pointer
                tempNode.g = pointer.g + 1
                if self.isGoal(temp):
                    res = tempNode
                    Astar += 1
                    break
                else:
                    if self.clSearch(cList.get(bIndex - 1), temp) == False:
                        oList.push(tempNode, self.Man(temp) + tempNode.g)
                        Astar += 1

            if bIndex != 2 and bIndex != 5 and bIndex != 8:
                temp = self.slideBlankRight(pointer.array, bIndex)
                tempNode = Node(temp)
                tempNode.parent = pointer
                tempNode.g = pointer.g + 1
                if self.isGoal(temp):
                    res = tempNode
                    Astar += 1
                    break
                else:
                    if self.clSearch(cList.get(bIndex + 1), temp) == False:
                        oList.push(tempNode, self.Man(temp) + tempNode.g)
                        Astar += 1

            if bIndex != 0 and bIndex != 1 and bIndex != 2:
                temp = self.slideBlankUp(pointer.array, bIndex)
                tempNode = Node(temp)
                tempNode.parent = pointer
                tempNode.g = pointer.g + 1
                if self.isGoal(temp):
                    res = tempNode
                    Astar += 1
                    break
                else:
                    if self.clSearch(cList.get(bIndex - 3), temp) == False:
                        oList.push(tempNode, self.Man(temp) + tempNode.g)
                        Astar += 1

            if bIndex != 6 and bIndex != 7 and bIndex != 8:
                temp = self.slideBlankDown(pointer.array, bIndex)
                tempNode = Node(temp)
                tempNode.parent = pointer
                tempNode.g = pointer.g + 1
                if self.isGoal(temp):
                    res = tempNode
                    Astar += 1
                    break
                else:
                    if self.clSearch(cList.get(bIndex + 3), temp) == False:
                        oList.push(tempNode, self.Man(temp) + tempNode.g)
                        Astar += 1
            if cList.get(bIndex) is None :
                cList[bIndex] = [pointer.array]
            else:
                cList[bIndex].append(pointer.array)
        return Astar










    def Man(self, arr):
        matrix = []
        matrix.append([arr[0], arr[1], arr[2]])
        matrix.append([arr[3], arr[4], arr[5]])
        matrix.append([arr[6], arr[7], arr[8]])
        res = 0
        for i in range(3):
            for j in range(3):
                if matrix[i][j] != 0:
                    x = (matrix[i][j] - 1) // 3
                    y = (matrix[i][j] - 1) % 3
                    res += abs(x - i) + abs(y - j)
        return res









    def clSearch(self, clist, arr):
        if not clist is None:
            for i in clist:
                flag = True
                for k in range(9):
                    if i[k] == arr[k]:
                        continue
                    else:
                        flag = False
                        break
                if flag:
                    return True
            return False

        else:
            return False


class proQueue:
    def __init__(self):
        self.q = []
        self.index = 0
    def push(self, node, pro):
        heapq.heappush(self.q, (pro, self.index, node))
        self.index += 1
    def pop(self):
        return heapq.heappop(self.q)
    def empty(self):
        if len(self.q) == 0:
            return True
        else:
            return False

    def __repr__(self):
        return len(self.q)


start = [0, 1, 2, 3, 4, 5, 6, 7, 8]
test = EightPuzzleState(start)
bfs = test.AStar()
print(bfs)