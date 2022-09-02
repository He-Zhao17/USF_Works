import dt
import sklearn as sk

from sklearn import tree
from sklearn.datasets import load_breast_cancer

from sklearn import model_selection
import numpy as np
from sklearn.model_selection import cross_val_score
from sklearn.linear_model import LinearRegression
from sklearn.ensemble import RandomForestClassifier


cancer = load_breast_cancer()
print("len: ", len(cancer), '\n')
print('data: ', cancer.data, sep = '\n')
print('index: ', cancer.target, sep = '\n')
print('abrr: ', cancer.feature_names, sep = '\n')



cancerT = tree.DecisionTreeClassifier(criterion = 'gini')
cancerT = cancerT.fit(cancer.data, cancer.target)
scores = cross_val_score(cancerT, cancer.data, cancer.target, cv=5)
print('\nresult with gini:', scores.mean())

cancerT = tree.DecisionTreeClassifier(criterion = 'entropy')
cancerT = cancerT.fit(cancer.data, cancer.target)
scores = cross_val_score(cancerT, cancer.data, cancer.target, cv=5)
print('\nresult with entropy:', scores.mean())

for i in [2, 5, 10]:
    for j in [0.25, 0.5, 1.0]:
        t1 = RandomForestClassifier(n_estimators=i, max_features = j)
        t1 = t1.fit(cancer.data, cancer.target)
        scores = cross_val_score(cancerT, cancer.data, cancer.target, cv = 5)
        print('\nresult with n_es = %d, max_features = %f:' % (i, j), scores.mean())

