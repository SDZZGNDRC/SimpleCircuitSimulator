package test;

import CircuitSim.*;

import java.util.Arrays;
import java.util.Vector;

import org.junit.Test;

public class CircuitSimTest {
    @Test
    public void test1(){
        CircuitSim circuitsim =  new CircuitSim();

        // 添加电路中的所有节点及节点间的连接
        circuitsim.nodes.add(new I_DC_VS(1, new Vector<Integer>(Arrays.asList(new Integer[] {2,5})), 10));
        circuitsim.nodes.add(new Resistance(2, new Vector<Integer>(Arrays.asList(new Integer[] {1,3,4})), 5));
        circuitsim.nodes.add(new Resistance(3, new Vector<Integer>(Arrays.asList(new Integer[] {2,5})), 5));
        circuitsim.nodes.add(new Resistance(4, new Vector<Integer>(Arrays.asList(new Integer[] {2,5})), 5));
        circuitsim.nodes.add(new Switch(5, new Vector<Integer>(Arrays.asList(new Integer[] {1,3, 4})), true));
        
        // 输出
        for(Node i:circuitsim.nodes){
            System.out.println(i.getType()+i.getClass().toString());
        }
    }
}
