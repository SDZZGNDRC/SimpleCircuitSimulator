package CircuitSim;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class I_DC_VS extends Component{
    public int x, y;
    public double U = 10; // 电压, 单位V
    public boolean Horizon = true;
    public I_DC_VS(String _name, Integer _Np, Integer _Nm){
        super(_name, _Np, _Nm);
        if(_name.charAt(0)!='V'){
            throw new IllegalArgumentException("电压源部件的名称第一个字母必须为V");
        }
    }
    @Override
    public String toString() {
        return new String(
            name+" "+Np.toString()+" "+Nm.toString()+" "+String.valueOf(U)+" "+
            "# "+Integer.toString(x)+","+Integer.toString(y)+";"+Boolean.toString(Horizon)
        );
    }

    // 绘制独立直流电压源
    public void draw(Graphics g, int DeltaX, int DeltaY){
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(this.LineWidth));
        g2d.setColor(this.color);
        g2d.drawOval(x-2*DeltaX, y-2*DeltaY, 4*DeltaX, 4*DeltaY);
        g2d.drawLine(x, y-2*DeltaY, x, y+2*DeltaY);
        g2d.drawLine(x-2*DeltaX, y, x-4*DeltaX, y);
        g2d.drawLine(x+2*DeltaX, y, x+4*DeltaX, y);
        g2d.dispose();
    }
}
