from lcapy import Circuit
from fractions import Fraction
import base64
import sys

# a = Circuit("""
# V0 1 0 10.0
# W0 1 2
# R0 2 3 3.0
# W1 3 4
# W2 4 5
# W3 5 0
# """)

def calSim(netlist : str) -> str:
    lines = netlist.split('\n')
    s = ""
    for i in lines: # 删去#后的注释
        s += i.split('#')[0]+'\n'
    n = Circuit(s)
    r = ''
    l = n.components.voltage_sources+n.components.resistors
    for j in l:
        u = str(n[j].v)
        i = str(n[j].i)
        r += (j+f' {round(float(Fraction(u)), 6)} {round(float(Fraction(i)), 6)}')+'\n'
    return r

if __name__ == "__main__":
    if len(sys.argv) != 2:
        exit(-1)
    netlist = base64.b64decode(sys.argv[1]).decode('UTF-8')
    print(calSim(netlist))


