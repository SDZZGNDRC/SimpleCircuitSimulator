package CircuitSim;

import java.awt.Color;
import java.awt.Graphics;

public class Component {
    public String name;
    public Integer Np, Nm;
    public double U;
    public double I;
    public Color color;
    public int LineWidth = 3;
    public boolean finished;
    public int DeltaX = 10;
    public int DeltaY = 10;
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

    // 判断给定坐标x,y是否在本部件范围内
    public boolean ifSelect(int x, int y){
        return false;
    }

    // 判断给定坐标x,y是否在指定矩形范围内
    public boolean inRect(int x, int y, int x1, int y1, int x2, int y2){
        // 参考https://blog.51cto.com/u_15899439/5909303
        return ((x1-x)*(x2-x)<0 && (y1-y)*(y2-y)<0) ? true : false;
    }

    public void draw(Graphics g){

    }
}
