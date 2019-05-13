import numpy as np


A = np.array([[1, 2, 3], [1, 4, 5], [6, 7, 8]])
B = np.array([[5, 4, 2], [1, 7, 9], [0, 4, 5]])
print("\n Matrix A:\n", A)

print("\n Trace Of A:\n", np.trace(A))  # 矩阵的迹
print("\n Sum of traces of A and B:\n", np.trace(A+B), np.trace(A)+np.trace(B))  # 和的迹=迹的和

print("\n Determinant of A:\n", np.linalg.det(A))  # A 的行列式
print("\n Determinant of B:\n", np.linalg.det(B))  # B 的行列式
print("\n Determinant of AB:\n", np.linalg.det(np.dot(A, B)))  # AB 的行列式
print("\n Determinant of A+B:\n", np.linalg.det(A+B))  # A+B 的行列式
