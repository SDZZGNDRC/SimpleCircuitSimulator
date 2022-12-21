package CircuitSim;

// 开关
public class Switch extends Component{
    public boolean ON; // 开关状态, true表示闭合on, false表示断开off
    public  Switch(String _name, Integer _Np, Integer _Nm) {
        super(_name, _Np, _Nm);
        if(_name.charAt(0)!='S'){
            throw new IllegalArgumentException("开关部件的名称第一个字母必须为S");
        }
    }
    @Override
    public String toString() {
        if(ON){
            return new String(name+" "+Np.toString()+" "+Nm.toString()+" "+"no 0");
        }else{
            return new String(name+" "+Np.toString()+" "+Nm.toString()+" "+"nc 0");
        }
    }
}
