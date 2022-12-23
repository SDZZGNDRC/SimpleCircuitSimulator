from lcapy import Circuit

a = Circuit("""
V0 0 1 10.0 
W0 1 3 
R0 3 4 10.0
W1 4 5
W2 5 6
W3 6 7
""")

if __name__ == "__main__":
    print(a.R1.v, a.R1.i)
    print(type(a.R1.v))
    print(a.describe())