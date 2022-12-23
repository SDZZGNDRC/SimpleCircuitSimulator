package GUI;

import CircuitSim.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;
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

    // 清空所有电路部件
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
        String content = saveStr();
        try {
            FileWriter  file = new FileWriter (filepath);
            file.write(content);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // 将电路保存为字符串
    public String saveStr(){
        String content = new String();
        for(Component i : netlist.components){
            content = content.concat(i.toString()+"\n");
        }
        return content;
    }

    // 读取电路文件(当前已有的电路将被丢弃掉)
    public void readFile(String filepath){
        Vector<Component> newComponents = new Vector<Component>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            for (String s : lines) {
                String[] param1 = s.split("#")[0].split(" ");
                String[] param2 = s.split("#")[1].substring(1).split("[,;]");
                switch (s.charAt(0)) {
                    case 'W': // 解码导线
                        newComponents.add(new Wire(
                            param1[0], 
                            Integer.parseInt(param1[1]),
                            Integer.parseInt(param1[2]),
                            Integer.parseInt(param2[0]),
                            Integer.parseInt(param2[1]),
                            Integer.parseInt(param2[2]),
                            Integer.parseInt(param2[3]),
                            Integer.parseInt(param2[4])
                        ));
                        Wire w = (Wire)newComponents.lastElement();
                        w.finished = true;
                        break;
                    case 'R':
                        newComponents.add(new Resistance(
                            param1[0],
                            Integer.parseInt(param1[1]),
                            Integer.parseInt(param1[2])
                        ));
                        Resistance r = (Resistance)newComponents.lastElement();
                        r.R = Double.parseDouble(param1[3]);
                        r.x = Integer.parseInt(param2[0]);
                        r.y = Integer.parseInt(param2[1]);
                        r.Horizon = Boolean.parseBoolean(param2[2]);
                        r.finished = true;
                        break;
                    case 'V':
                        newComponents.add(new I_DC_VS(
                            param1[0],
                            Integer.parseInt(param1[1]),
                            Integer.parseInt(param1[2])
                        ));
                        I_DC_VS v = (I_DC_VS)newComponents.lastElement();
                        v.U = Double.parseDouble(param1[3]);
                        v.x = Integer.parseInt(param2[0]);
                        v.y = Integer.parseInt(param2[1]);
                        v.Horizon = Boolean.parseBoolean(param2[2]);
                        v.finished = true;
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.netlist.components = newComponents;
        repaint();
    }

    // 加载仿真结果
    public void LoadSimResult(String result){
        String[] lines = result.split("\n");
        for (String string : lines) {
            String[] param = string.split(" ");
            for (Component c : netlist.components) {
                if(c.name.equals(param[0])){
                    c.loadSimResult(string);
                    break;
                }
            }
        }
    }
}
