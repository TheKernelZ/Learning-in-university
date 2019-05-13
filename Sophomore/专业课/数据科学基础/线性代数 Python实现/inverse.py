import numpy as np
from numpy.random import randn
from numpy.linalg import det, inv


A = randn(3, 3)
print("\nMatrix A:\n", A)

detA = det(A)
print("\nDeterminant:\n", detA)

invA = inv(A)
print("\nInverse:\n", invA)

print("\nINV(A)*A\n", np.dot(inv(A), A))

adjoint = detA * invA
print("\nAdjonit:\n", adjoint)