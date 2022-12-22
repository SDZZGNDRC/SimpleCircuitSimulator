package CircuitSim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Wire extends Component {
    public final double U = 0;
    public int x1, y1, x2, y2;
    public Color color = new Color(0x4d, 0x92, 0x3a);
    public int XFirst = -1; // 有拐角的导线的画法有两种, 先横后竖或先竖后横. -1表示为设置, 0表示后者, 1表示前者
    public Wire(String _name, Integer _Np, Integer _Nm){
        super(_name, _Np, _Nm);
        if(_name.charAt(0)!='W'){
            throw new IllegalArgumentException("导线部件的名称第一个字母必须为W");
        }
    }
    public Wire(String _name, Integer _Np, Integer _Nm,
                int _x1, int _y1, int _x2, int _y2
    ){
        super(_name, _Np, _Nm);
        if(_name.charAt(0)!='W'){
            throw new IllegalArgumentException("导线部件的名称第一个字母必须为W");
        }
        this.x1 = _x1; this.y1 = _y1;
        this.x2 = _x2; this.y2 = _y2;
    }

    // 设置导线的位置
    public void setLocal(int _x1, int _y1, int _x2, int _y2){
        x1 = _x1; y1 = _y1;
        x2 = _x2; y2 = _y2;
    }

    // 绘制导线
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(this.LineWidth));
        g2d.setColor(this.color);
        if(this.XFirst==1){
            g2d.drawLine(this.x1, this.y1, this.x2, this.y1);
            g2d.drawLine(this.x2, this.y1, this.x2, this.y2);    
        }else{
            g2d.drawLine(this.x1, this.y1, this.x1, this.y2);
            g2d.drawLine(this.x1, this.y2, this.x2, this.y2);
        }
        g2d.dispose();
    }
}
