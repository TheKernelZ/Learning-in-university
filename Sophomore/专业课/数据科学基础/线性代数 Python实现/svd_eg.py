import numpy as np


a = np.random.randn(9, 6)
print("Matrix a:\n", a)
print("Shape of a:\n", a.shape)


# SVD 分解
u, s, vh = np.linalg.svd(a)
print("Shape of u, s, vh:\n", u.shape, s.shape, vh.shape)
print("Sigular values:\n", s)