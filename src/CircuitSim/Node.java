package CircuitSim;

import java.util.Collections;
import java.util.Vector;

// 该类表示有向图形式的电路中邻接表的每一个顶点, 对应于电路中的每一个节点.
// 主线程初始化该类时, 按照无向图进行处理, 因为没有经过计算难以确定电路中节点的参考方向.
// 子线程进行计算后, 根据电路的关联参考方向将无向图更改为有向图.
public class Node {
    public Integer ID; // 该节点的ID编号, 在所有的电路节点中全局唯一
    public Vector<Integer> nextIDs = new Vector<Integer>(); // 存放和该节点相邻(即有边连接)的节点的ID
    protected double U; // 该节点的端电压
    protected double I; // 流过该节点的电流
    public Node(){
    }
    public Node(Vector<Integer> _nextIDs){
        this.nextIDs.setSize(_nextIDs.size());
        Collections.copy(this.nextIDs, _nextIDs);
    }
    public NodeType getType() {
        return NodeType.Node;
    }
    public double getU() { // 获取端电压
        return U;
    }
    public double getI() { // 获取电流
        return I;
    }
}


// 表示节点的类型
enum NodeType{
    Node, // 特殊, 不代表任何类型的节点
    Switch, // Switch, 开关
    Resistance, // Resistance, 电阻
    Bulb, // Bulb, 灯泡
    I_DC_VS, // Independent DC Voltage Source, 独立直流电压源
    C_DC_VS, // Controlled DC Voltage Source, 受控直流电压源
    I_DC_CS, // Independent DC Current Source, 独立直流电流源
    C_DC_CS, // Controlled DC Current Source, 受控直流电流源
}
