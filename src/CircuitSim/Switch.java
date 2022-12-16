package CircuitSim;

import java.util.Vector;

// 开关
public class Switch extends Node{
    private boolean ON; // 开关状态, true表示闭合on, false表示断开off
    public  Switch(Integer _id, boolean _on) {
        super();
        this.ID = _id;
        this.ON = _on;
        this.U = 0;
        this.I = 0;
    }
    public  Switch(Integer _id, Vector<Integer> _nextIDs, boolean _on) {
        super(_nextIDs);
        this.ID = _id;
        this.ON = _on;
        this.U = 0;
        this.I = 0;
    }
    
    @Override
    public NodeType getType(){
        return NodeType.Switch;
    }
    public boolean getON() { // 获取开关状态
        return this.ON;
    }
    public void setON(boolean _on) { // 设置开关状态
        this.ON = _on;
        this.U = (_on) ? 0 : this.U;
        this.I = (_on) ? this.I : 0;
    }
    public void setU(double _u) { // 设置电压
        if (this.ON == true && _u != 0) {
            throw new IllegalArgumentException("当开关闭合时, 无法将开关的端电压设为非0");
        }
        this.U = _u;
    }
    public void setI(double _i) { // 设置电流
        if (this.ON == false && _i != 0) {
            throw new IllegalArgumentException("当开关断开时, 无法将开关的电流设为非0");
        }
        this.I = _i;
    }

}
