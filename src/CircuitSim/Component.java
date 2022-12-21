package CircuitSim;

import java.awt.Color;

public class Component {
    public String name;
    public Integer Np, Nm;
    public double U;
    public double I;
    public Color color;
    public int LineWidth = 3;
    public boolean finished;
    public Component(String _name, Integer _Np, Integer _Nm){
        this.name = _name;
        this.Np = _Np;
        this.Nm = _Nm;
        color = Color.BLACK;
        finished = false;
    }
    @Override
    public String toString() {
        return new String(name+" "+Np.toString()+" "+Nm.toString());
    }
}
