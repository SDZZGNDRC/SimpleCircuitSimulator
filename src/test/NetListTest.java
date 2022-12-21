package test;

import org.junit.Test;

import CircuitSim.I_DC_VS;
import CircuitSim.NetList;
import CircuitSim.Resistance;
import CircuitSim.Wire;

public class NetListTest {
    @Test
    public void test1(){
        NetList net = new NetList();
        net.components.add(new I_DC_VS("V1", 1, 0));
        net.components.add(new Wire("W1", 1, 2));
        net.components.add(new Resistance("R1", 2, 3));
    }

}
