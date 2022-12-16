package CircuitSim;

import java.util.Vector;

// 独立直流电压源
public class I_DC_VS extends Node {
    public I_DC_VS(Integer _id, double _u){
        super();
        this.ID = _id;
        this.U = _u;
    }
    public I_DC_VS(Integer _id, Vector<Integer> _nextIDs, double _u){
        super(_nextIDs);
        this.ID = _id;
        this.U = _u;
    }
    @Override
    public NodeType getType() {
        return NodeType.I_DC_VS;
    }
    public void setU(double _u) { // 设置电压值
        this.U = _u;
    }
    public void setI(double _i) { // 设置电流值
        this.I = _i;
    }
}
