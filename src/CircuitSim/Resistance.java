package CircuitSim;

import java.util.Vector;

public class Resistance extends Node{
    private double R; // 表明电阻阻值, 单位欧姆Ω
    public Resistance(Integer _id, double _r){
        super();
        this.ID = _id;
        this.R = _r;
    }
    public Resistance(Integer _id, Vector<Integer> _nextIDs, double _r){
        super(_nextIDs);
        this.ID = _id;
        this.R = _r;
    }
    @Override
    public NodeType getType(){
        return NodeType.Resistance;
    }
    public double getR() { // 获取电阻阻值
        return this.R;
    }
    // 注意, 更新电阻值后, 应该根据电压不变或电流不变的条件对电阻的电压/电流值进行更新, 否则会出错
    public void setR(double _r) {
        if(_r <= 0){
            throw new IllegalArgumentException("电阻的阻值应为正数");
        }
        this.R = _r;
    }
    public void setU(double _u) { // 设置端电压(同时根据U=IR设置电流)
        this.U = _u;
        this.I = _u/this.R;
    }
    public void setI(double _i) { // 设置电流(同时根据U=IR设置端电压)
        this.I = _i;
        this.U = _i*this.R;
    }
}
