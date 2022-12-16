package CircuitSim;

import java.util.Vector;

// 独立直流电流源
public class I_DC_CS extends Node {
    public I_DC_CS(Integer _id, double _i){
        super();
        this.ID = _id;
        this.I = _i;
    }
    public I_DC_CS(Integer _id, Vector<Integer> _nextIDs, double _i){
        super(_nextIDs);
        this.ID = _id;
        this.I = _i;
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
