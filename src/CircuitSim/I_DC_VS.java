package CircuitSim;

public class I_DC_VS extends Component{
    public I_DC_VS(String _name, Integer _Np, Integer _Nm){
        super(_name, _Np, _Nm);
        if(_name.charAt(0)!='V'){
            throw new IllegalArgumentException("电压源部件的名称第一个字母必须为V");
        }
    }
    @Override
    public String toString() {
        return new String(name+" "+Np.toString()+" "+Nm.toString()+" "+String.valueOf(U));
    }
}
