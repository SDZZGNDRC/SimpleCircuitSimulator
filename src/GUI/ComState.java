package GUI;

import CircuitSim.*;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ComState extends JComponent{
    private Component content;

    public ComState(){
        super();
        this.setBackground(new Color(0xeef5fb));
        this.setPreferredSize(new Dimension(50, 100));
        content = null;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 绘制背景
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight()); 

    }

    public void setContent(Component c){
        this.content = c;
        System.out.println("setContent: "+c);
        this.DisplayContent();
    }

    // 显示内容
    public void DisplayContent(){
        if(content instanceof Wire){
            DisplayWire();
        }else if(content instanceof Resistance){
            DisplayResistance();
        }else if(content instanceof I_DC_VS){
            DisplayIVS();
        }
    }

    // 显示导线的参数
    private void DisplayWire(){
        Wire w = (Wire)content;
        Font font = new Font("Serif", Font.PLAIN, 18);
        this.removeAll();
        this.setLayout(new GridLayout(0,4));

        addLabelWithTextField("名称: ", w.name, font, SwingConstants.RIGHT);
        addLabelWithTextField("Np: ", w.Np.toString(), font, SwingConstants.RIGHT);
        addLabelWithTextField("Nm: ", w.Nm.toString(), font, SwingConstants.RIGHT);

    }

    // 显示电阻的参数
    private void DisplayResistance(){
        Resistance r = (Resistance)content;
        Font font = new Font("Serif", Font.PLAIN, 18);
        this.removeAll();
        this.setLayout(new GridLayout(0,4));

        addLabelWithTextField("名称: ", r.name, font, SwingConstants.RIGHT);
        addLabelWithTextField("电阻值(Ω): ", Double.toString(r.R), font, SwingConstants.RIGHT);
        addLabelWithTextField("电压(V): ", Double.toString(r.U), font, SwingConstants.RIGHT);
        addLabelWithTextField("电流(A): ", Double.toString(r.I), font, SwingConstants.RIGHT);
        
    }

    // 显示独立直流电压源的参数
    private void DisplayIVS(){
        I_DC_VS v = (I_DC_VS)content;
        Font font = new Font("Serif", Font.PLAIN, 18);
        this.removeAll();
        this.setLayout(new GridLayout(0,4));

        addLabelWithTextField("名称: ", v.name, font, SwingConstants.RIGHT);
        addLabelWithTextField("电压(V): ", Double.toString(v.U), font, SwingConstants.RIGHT);
        addLabelWithTextField("电流(A): ", Double.toString(v.I), font, SwingConstants.RIGHT);
    }

    // 添加Label with TextField
    private void addLabelWithTextField(
        String _label, 
        String _textField, 
        Font _font, 
        int labelHorizontalAlignment){
        JLabel l1;
        JTextField t1;

        l1 = new JLabel(_label, labelHorizontalAlignment);
        l1.setFont(_font);
        this.add(l1);
        t1 = new JTextField(_textField, 10);
        this.add(t1);
        t1.setFont(_font);
    }
}
