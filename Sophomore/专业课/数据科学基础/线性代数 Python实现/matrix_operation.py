import numpy as np


a = np.array([[-1, 4], [2, 3]])
b = np.array([[3, 4], [4, 5]])
print("\n a: \n", a)
print("\n b: \n", b)


print("\n a transponse:\n", a.T)  # 矩阵转置
print("\n a+b: \n", a+b)  # 矩阵加法
print("\n a-b: \n", a-b)  # 矩阵减法
print("\n 3*a: \n", 3*a)  # 矩阵数乘
print("\n ab: \n", np.dot(a, b))  # 矩阵乘法
print("\n a*b: \n", a*b)  # 矩阵点乘
print("\n a/b: \n", a/b)  # 矩阵点除