package CircuitSim;

public class Resistance extends Component{
    public double R; // 表明电阻阻值, 单位欧姆Ω
    public Resistance(String _name, Integer _Np, Integer _Nm){
        super(_name, _Np, _Nm);
        if(_name.charAt(0)!='R'){
            throw new IllegalArgumentException("电阻部件的名称第一个字母必须为R");
        }
    }
    @Override
    public String toString() {
        return new String(name+" "+Np.toString()+" "+Nm.toString()+" "+String.valueOf(R));
    }
}
