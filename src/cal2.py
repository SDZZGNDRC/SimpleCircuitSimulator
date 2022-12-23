from lcapy import Circuit
from fractions import Fraction
import jpysocket
import socket

host='localhost' #Host Name
port=12345    #Port Number
s=socket.socket() #Create Socket
s.bind((host,port)) #Bind Port And Host
s.listen(5) #Socket is Listening
print("Python: Socket Is Listening....")
a = Circuit("""
V0 1 0 10.0
W0 1 2
R0 2 3 3.0
W1 3 4
W2 4 5
W3 5 0
""")

if __name__ == "__main__":
    while(True):
        connection,address=s.accept() #Accept the Connection
        print("Python: Connected To ",address)
        msgsend=jpysocket.jpyencode("Connected") #Encript The Msg
        connection.send(msgsend) #Send Msg
        msgrecv=connection.recv(1024) #Recieve msg
        msgrecv=jpysocket.jpydecode(msgrecv) #Decript msg
        print("Python: From Client: ",msgrecv)
        connection.close()
        print("Connection Closed.")


def calSim(netlist : str) -> str:
    n = Circuit(netlist)
    r = ''
    l = n.components.voltage_sources+a.components.resistors
    for j in l:
        u = str(a[j].v)
        i = str(a[j].i)
        r += (j+f' {round(float(Fraction(u)), 6)} {round(float(Fraction(i)), 6)}')
    return r