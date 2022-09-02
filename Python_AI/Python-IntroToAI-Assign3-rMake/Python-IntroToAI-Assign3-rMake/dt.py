import sklearn
import pandas as pd
from collections import Counter
import math

from sklearn.datasets import load_iris

### compute entropy for a set of classification, provided as a pandas Series
def entropy(classes) :
    vals = set(classes)
    counts = Counter(classes)
    ent = 0.0
    for val in vals :
        frequency = counts[val] / len(classes)
        ent += -1 * frequency * math.log(frequency, 2)
    return ent

### Assume that both attribute and classes are pandas Series
### For each value of attribute, compute the entropy. Then return the weighted sum
def remainder(attribute, classes) :
    uniAttr = attribute.unique()
    xlen = len(classes)
    map = {}
    rem = 0.0
    for val in uniAttr:
        map[val] = []
    rows = attribute.index
    for val in rows:
        map[attribute[val]].append(val)
    for val in uniAttr:
        tempRows = map[val]
        tempClass = classes[tempRows]
        entTemp = entropy(tempClass)
        rem += (len(tempRows) / xlen) * entTemp
    return rem
    pass


### assume that data is a pandas dataframe, and classes is a pandas series
### For each column in the dataframe, compute the remainder and select the column with the lowest
### remainder

def selectAttribute(data, classes):
    numCol = data.shape[1]
    minIndex = ""
    minRem = 1.0
    for index, atrr in data.iteritems():
        tempRem = remainder(atrr, classes)
        print(tempRem)
        if tempRem <= minRem:
            minRem = tempRem
            minIndex = index
    print(minIndex)
    print("\n")
    return minIndex
    pass

### Now we're ready to build a Decision Tree.
### A tree consists of one or more Nodes.
### A Node is either a leaf node, which has a value and no children
### Or it is a non-leaf, in which case it has an attribute that it tests and a set of children.

class Node :
    def __init__(self, attribute=None, value=None):
        self.attribute = attribute
        self.value=value
        self.children={}

    def isLeaf(self):
        return len(self.children) == 0

    ### you'll implement this
    def classify(self, instance):
        if self.isLeaf():
            return self
        else:
            key = instance[self.attribute]
            nextNode = self.children[key]
            nextInstance = instance.drop(self.attribute)
            return nextNode.classify(nextInstance)

    def __repr__(self) :
        return "%s %s" % (self.attribute, self.value)

##
class DecisionTree :
    def __init__(self, root) :
        self.root = root

    def __init__(self, df, abbrDic):
        self.root = makeNode(df, abbrDic)

    ### assume instance is a pandas dataframe - use node.classify as a helper.
    def classify(self, instance):
        return self.root.classify(instance)


### construct a decision tree. Inputs are a pandas dataframe containing a dataset,
### and an attributeDict that maps each attribute to the possible values it can take on.

### We make the tree recursively. There are three base cases:
### 1. All the data is of the same class.
###   In this case, we are at a leaf node. set the value to be the classification.
### 2. We are out of attributes to test.
###   In this case, apply ZeroR.
### 3 We are out of data
###   In this case, apply ZeroR.
### Return the node
### Otherwise :
###  1. Use selectAttribute to find the attribute with the largest information gain.
###  2. Break the data into subsets according to each value of that attribute.
###  3. For each subset, call makeNode

def ZeroR (cla, res):
    index = cla.index
    counts = Counter(cla)
    xset = set(cla)
    max = counts[index[0]]
    for val in xset:
        if counts[val] > max:
            max = counts[val]
            maxindex = val
    return maxindex

def makeNode(df, attributeDict) :

    numRow = df.shape[0]
    numCol = df.shape[1]
    data = df.iloc[:, 0: numCol - 1]
    cla = df.iloc[:, numCol - 1]
    minCol = selectAttribute(data, cla)
    root = Node()

    # out of data
    if numRow == 0:
        resLabel = df.columns[-1]
        zr = attributeDict[resLabel][0]
        return Node(None, zr)
    # all data is same
    index = cla.index
    temp = cla[index[0]]
    tempCounts = Counter(cla)
    if tempCounts[temp] == len(cla):
        return Node(None, temp)
    # out of attr
    if df.shape[1] == 1:
        temp = attributeDict[df.columns[-1]]
        return Node(None, ZeroR(cla, temp))

    # can be split
    bestArr = selectAttribute(data, cla)
    root = Node(bestArr)

    t = bestArr in attributeDict

    for val in attributeDict[bestArr]:
        subAttrDic = dict.copy(attributeDict)
        del subAttrDic[bestArr]
        # edit the data
        temp = df[df[bestArr] == val]
        temp = temp.drop([bestArr], axis = 1)
        root.children[val] = makeNode(temp, subAttrDic)
    return root