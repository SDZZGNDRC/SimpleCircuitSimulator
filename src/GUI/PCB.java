package GUI;

import CircuitSim.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComponent;

public class PCB extends JComponent{
    public NetList netlist;
    // 一个小方格的长宽
    final int DeltaX = 10;
    final int DeltaY = 10;
    // 网格线的颜色
    final Color color_Gridlines = new Color(0xde,0xde,0xde);
    // 虚线的颜色
    final Color color_DashedLines = new Color(0xff,0x61,0x6a);
    // 是否绘制虚线
    public boolean ifDrawDashedLines;
    public int DashedLines_X;
    public int DashedLines_Y;
    public PCB(){
        super();
        netlist = new NetList();
        this.ifDrawDashedLines = false;
        DashedLines_X = 0;
        DashedLines_Y = 0;
    }

    public void add(Component _c){
        netlist.components.add(_c);
        repaint();
    }
    public void clear(){
        netlist.components.clear();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight()); 

        // 绘制网格
        drawGridLines(g);
        
        // 绘制虚线
        if(ifDrawDashedLines){
            drawDashedLines(g, DashedLines_X, 0, DashedLines_X, this.getHeight());
            drawDashedLines(g, 0, DashedLines_Y, this.getWidth(), DashedLines_Y);
        }

        // 绘制部件
        for(Component c : netlist.components){
            c.draw(g);
        }
    }
    private void drawGridLines(Graphics g) {
        g.setColor(this.color_Gridlines);
        for(int i = 0; i < this.getWidth(); i+=DeltaX){
            g.drawLine(i,0,i,this.getHeight());
        }
        for(int i = 0; i < this.getHeight();i+=DeltaY){
            g.drawLine(0, i, this.getWidth(), i);
        }
    }
    private void drawDashedLines(Graphics g,int x1,int y1,int x2,int y2){
        g.setColor(color_DashedLines);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
                                        0, new float[]{10}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();
    }


    // 将电路保存为文件
    public void saveAS(String filepath){
        String content = new String();
        for(Component i : netlist.components){
            content = content.concat(i.toString()+"\n");
        }
        try {
            FileWriter  file = new FileWriter (filepath);
            file.write(content);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
