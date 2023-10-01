# Import the NumPy library for matrix operations
import numpy as np

# Define two matrices A and B
A = np.array([[1, 2, 3],
              [4, 5, 6],
              [7, 8, 9]])

B = np.array([[9, 8, 7],
              [6, 5, 4],
              [3, 2, 1]])

# Addition of two matrices
addition_result = np.add(A, B)

# Subtraction of two matrices
subtraction_result = np.subtract(A, B)

# Multiplication of two matrices
multiplication_result = np.matmul(A, B)

# Determinant of matrix A
determinant_A = np.linalg.det(A)

# Determinant of matrix B
determinant_B = np.linalg.det(B)

# Print the results
print("Matrix A:")
print(A)
print("\nMatrix B:")
print(B)
print("\nMatrix Addition (A + B):")
print(addition_result)
print("\nMatrix Subtraction (A - B):")
print(subtraction_result)
print("\nMatrix Multiplication (A * B):")
print(multiplication_result)
print("\nDeterminant of Matrix A:", determinant_A)
print("Determinant of Matrix B:", determinant_B)
