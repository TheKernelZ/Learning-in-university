import numpy as np


# 向量 = 一维矩阵
a1 = np.array([1, 1, 1])
a2 = np.array([2, 2, 2])
a3 = np.array([3, 3, 3])


# 输出
print("a1: ", a1)
print("a2: ", a2)
print("a3: ", a3)

# 输出向量表示
print("a1+a2=", a1+a2)             # 向量加法
print("a1-a2=", a1-a2)             # 向量减法
print("5*a1=", 5*a1)               # 向量数乘
print("a3^3=", a3**3)              # 向量点幂
print("a1*a3=", a1*a3)             # 向量乘法
print("(a1,a3)=", np.dot(a1, a3))  # 向量内积