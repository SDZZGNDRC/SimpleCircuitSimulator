from lcapy import Circuit

a = Circuit("""
V 1 0 7
R1 1 2 2
R2 2 0_2 4
W 0 0_2""")

if __name__ == "__main__":
    print(a.R1.v, a.R1.i)
    print(type(a.R1.v))
    print(a.describe())