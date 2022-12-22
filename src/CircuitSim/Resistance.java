package CircuitSim;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Resistance extends Component{
    public double R; // 表明电阻阻值, 单位欧姆Ω
    public int x, y;
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

    // 绘制电阻
    public void draw(Graphics g, int DeltaX, int DeltaY){
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(this.LineWidth));
        g2d.setColor(this.color);
        g2d.drawRect(x-2*DeltaX, y-DeltaY, 4*DeltaX, 2*DeltaY);
        g2d.drawLine(x-2*DeltaX, y, x-4*DeltaX, y);
        g2d.drawLine(x+2*DeltaX, y, x+4*DeltaX, y);
        g2d.dispose();
    }
}
