package CircuitSim;

public class I_DC_CS extends Component{
    public I_DC_CS(String _name, Integer _Np, Integer _Nm){
        super(_name, _Np, _Nm);
        if(_name.charAt(0)!='I'){
            throw new IllegalArgumentException("电流源部件的名称第一个字母必须为I");
        }
    }
    @Override
    public String toString() {
        return new String(name+" "+Np.toString()+" "+Nm.toString()+" "+String.valueOf(I));
    }
}
