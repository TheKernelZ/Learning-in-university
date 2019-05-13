import numpy as np


# 特殊矩阵
a = np.zeros((2, 3))  # 2x3 零矩阵
print("All zeros:\n", a)

a = np.ones([2, 3])  # 2x3 全1矩阵
print("\nAll ones:\n", a)

a = np.eye(4, 7)  # 4x7 主对角线为1
print("\n4x7 diagonal:\n", a)

a = np.identity(3)  # 单位矩阵
print("\nIdentity marix:\n", a)

a = np.diag(range(5))  # 对角矩阵
print("\n5x5 diagonal:\n", a)


# 特殊分布矩阵 
a = np.random.rand(2, 3)  # 2x3 在[0,1]上均匀分布的随机矩阵
print("\n[0,1]random matrix:\n", a)

a = np.random.randint(1, 100, (5, 5))  # 5x5 (1, 100)以内随机矩阵
print("\n[1,100]random matrix:\n", a)

a = np.random.randn(3, 3)  # 3x3 正态分布
print("\nrandom matrix:\n", a)