import numpy as np


x = np.diag((1, 2, 3 ,4))
print("x:\n", x)

eigval, eigvec = np.linalg.eig(x)
print("Eigen values:\n", eigval)
print("Eigtn vectors:\n", eigvec)

y = np.random.randn(3, 3)
print("\ny:\n", y)

eigval, eigvec = np.linalg.eig(y)
print("Eigen values:\n", eigval)
print("Eigtn vectors:\n", eigvec)
