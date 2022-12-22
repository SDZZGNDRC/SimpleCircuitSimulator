package GUI;

import java.lang.Math;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import CircuitSim.Wire;
import CircuitSim.Resistance;
import CircuitSim.Component;
import CircuitSim.I_DC_VS;


// 创建图形界面
public class mainGUI extends JFrame 
    implements ActionListener,MouseListener,MouseMotionListener{
    guiState state;
    Color color;
    JPanel p0;
    PCB pcb;
    String shape;
    JMenuBar menuBar;
    JMenu menu_File, menu_Sim;
    JMenuItem menuItem_File_Open, menuItem_File_Save, menuItem_File_Exit;
    JMenuItem menuItem_Sim_Start, menuItem_Sim_Stop, menuItem_Sim_Clear;
    

    public mainGUI(){
        state = guiState.Default;
        p0 = new JPanel();
        pcb = new PCB();
        // pcb.add(new Wire("W1", 0, 1, 10, 10, 100, 100));
        // pcb.netlist.components.lastElement().finished = true;

        // 创建菜单栏
        menuBar = new JMenuBar();
        menu_File = new JMenu("文件");
        menu_Sim = new JMenu("仿真");
        menuItem_File_Open = new JMenuItem("打开文件");
        menuItem_File_Save = new JMenuItem("保存文件");
        menuItem_File_Exit = new JMenuItem("退出");
        menuItem_Sim_Start = new JMenuItem("开始仿真");
        menuItem_Sim_Stop = new JMenuItem("停止仿真");
        menuItem_Sim_Clear = new JMenuItem("清空");
        menu_File.add(menuItem_File_Open);
        menu_File.add(menuItem_File_Save);
        menu_File.add(menuItem_File_Exit);
        menu_Sim.add(menuItem_Sim_Start);
        menu_Sim.add(menuItem_Sim_Stop);
        menu_Sim.add(menuItem_Sim_Clear);
        menuBar.add(menu_File);
        menuBar.add(menu_Sim);

        menuItem_File_Save.addActionListener(this);

        setTitle("简易电路仿真器");
        this.setSize(1400,900);
        this.setLocation(100,100);
        pcb.setBackground(new Color(0xff,0xff,0xff));
        // 元件按钮
        String [] Shape={"导线","独立直流电压源","电阻","开关"};
        p0.setLayout(new BoxLayout(p0, BoxLayout.Y_AXIS));
        for(int i=0;i<Shape.length;i++){
            JButton button=new JButton(Shape[i]);
            button.addActionListener(this); //添加事件监听机制
            button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
            p0.add(button);
        }
        menuBar.setOpaque(true);
        this.add(p0,BorderLayout.LINE_START);
        this.add(pcb,BorderLayout.CENTER);
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true);

        pcb.addMouseListener(this);
        pcb.addMouseMotionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        // 菜单处理逻辑
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();
            shape = item.getActionCommand();
            System.out.println("String = " + shape);
            pcb.saveAS("./temp.pcb");
            return;
        }

        // 按钮处理逻辑
        if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();  
            shape = button.getActionCommand();   
            System.out.println("String = " + shape);
            switch (state) {
                case Default:
                    if(shape=="导线"){
                        state = guiState.DrawWire;
                        System.out.println("Start drawing a wire.");
                    }else if(shape=="电阻"){
                        state = guiState.DrawResistance;
                        Resistance tw = (Resistance)pcb.netlist.create(Resistance.class);
                        tw.x = 0;
                        tw.y = 0;
                        pcb.netlist.components.add((Component)tw);
                        System.out.println("Create a New Resistance");
                        System.out.println("Start drawing a resistance");
                    }else if(shape=="独立直流电压源"){
                        state = guiState.DrawIVS;
                        I_DC_VS tw = (I_DC_VS)pcb.netlist.create(I_DC_VS.class);
                        tw.x = 0;
                        tw.y = 0;
                        pcb.netlist.components.add((Component)tw);
                        System.out.println("Create a New I_DC_VS");
                        System.out.println("Start drawing a I_DC_VS");
                    }
                    break;
                default:
                    break;
            }
            if(state != guiState.Default){
                pcb.ifDrawDashedLines = true;
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX()+", "+e.getY());
        switch (state) {
            case DrawWire:
                if(SwingUtilities.isRightMouseButton(e)){ // 右键被点击
                    state = guiState.Default;
                    System.out.print("Switch to Default State");
                    pcb.ifDrawDashedLines = false;
                    if(!pcb.netlist.components.isEmpty() && !pcb.netlist.components.lastElement().finished){
                        pcb.netlist.components.remove(pcb.netlist.components.size()-1);
                    }
                    pcb.repaint();
                }else if(SwingUtilities.isLeftMouseButton(e)) { // 左键被点击
                    if(pcb.netlist.components.isEmpty() || pcb.netlist.components.lastElement().finished){
                        Wire tw = (Wire)pcb.netlist.create(Wire.class);
                        tw.x1 = (e.getX()%pcb.DeltaX>pcb.DeltaX/2) ? e.getX()-e.getX()%pcb.DeltaX+pcb.DeltaX : e.getX()-e.getX()%pcb.DeltaX;
                        tw.y1 = (e.getY()%pcb.DeltaY>pcb.DeltaY/2) ? e.getY()-e.getY()%pcb.DeltaY+pcb.DeltaY : e.getY()-e.getY()%pcb.DeltaY;
                        tw.x2 = tw.x1; tw.y2 = tw.y1;
                        pcb.netlist.components.add((Component)tw);
                        System.out.println("Create a New Wire");
                    }else{
                        pcb.netlist.components.lastElement().finished = true;
                    }
                }
                break;
            case DrawResistance:
                if(SwingUtilities.isRightMouseButton(e)){ // 右键被点击
                    state = guiState.Default;
                    System.out.print("Switch to Default State");
                    pcb.ifDrawDashedLines = false;
                    if(!pcb.netlist.components.isEmpty() && !pcb.netlist.components.lastElement().finished){
                        pcb.netlist.components.remove(pcb.netlist.components.size()-1);
                    }
                    pcb.repaint();
                }else if(SwingUtilities.isLeftMouseButton(e)) { // 左键被点击
                    pcb.netlist.components.lastElement().finished = true;
                    Resistance tw = (Resistance)pcb.netlist.create(Resistance.class);
                    tw.x = pcb.DashedLines_X;
                    tw.y = pcb.DashedLines_Y;
                    pcb.netlist.components.add((Component)tw);
                }
                break;
            case DrawIVS:
                if(SwingUtilities.isRightMouseButton(e)){ // 右键被点击
                    state = guiState.Default;
                    System.out.print("Switch to Default State");
                    pcb.ifDrawDashedLines = false;
                    if(!pcb.netlist.components.isEmpty() && !pcb.netlist.components.lastElement().finished){
                        pcb.netlist.components.remove(pcb.netlist.components.size()-1);
                    }
                    pcb.repaint();
                }else if(SwingUtilities.isLeftMouseButton(e)) { // 左键被点击
                    pcb.netlist.components.lastElement().finished = true;
                    I_DC_VS tw = (I_DC_VS)pcb.netlist.create(I_DC_VS.class);
                    tw.x = pcb.DashedLines_X;
                    tw.y = pcb.DashedLines_Y;
                    pcb.netlist.components.add((Component)tw);
                }
                break;
            default:
                break;
        }
    }
    // 在组件上单击（按下并释放）鼠标按钮时调用。
    public void mouseClicked(MouseEvent e){
    }
    // 当鼠标进入组件时调用。
    public void mouseEntered(MouseEvent e){
    }
    // 当鼠标退出组件时调用。
    public void mouseExited(MouseEvent e){
    }
    // 当鼠标松开时调用
    public void mouseReleased(MouseEvent e){
    }
    // 在组件上按下鼠标按钮然后拖动时调用。
    public void mouseDragged(MouseEvent e){
    }
    // 当鼠标光标移动到组件上但没有按钮被按下时调用
    public void mouseMoved(MouseEvent e) {
        updateDashedLines(e);
        switch (state) {
            case DrawWire:
                // 更新导线
                if(!pcb.netlist.components.isEmpty() && !pcb.netlist.components.lastElement().finished){
                    Wire t = (Wire)pcb.netlist.components.lastElement();
                    if(t.XFirst==-1){
                        if(Math.abs(Math.abs(pcb.DashedLines_X-t.x1)-Math.abs(pcb.DashedLines_Y-t.y1)) > pcb.DeltaX){
                            t.XFirst = (Math.abs(pcb.DashedLines_X-t.x1)-Math.abs(pcb.DashedLines_Y-t.y1)>pcb.DeltaX) ? 1 : 0;
                        }
                    }else if(pcb.DashedLines_X==t.x1&&pcb.DashedLines_Y==t.y1){
                        t.XFirst = -1;
                    }
                    t.x2 = pcb.DashedLines_X;
                    t.y2 = pcb.DashedLines_Y;
                }
                break;
            case DrawResistance:
                // 更新电阻
                if(!pcb.netlist.components.isEmpty()){
                    Resistance t = (Resistance)pcb.netlist.components.lastElement();
                    if(t.finished){
                        break;
                    }
                    t.x = pcb.DashedLines_X; t.y = pcb.DashedLines_Y;
                }
                break;
            case DrawIVS:
                // 更新独立直流电压源
                if(!pcb.netlist.components.isEmpty()){
                    I_DC_VS t = (I_DC_VS)pcb.netlist.components.lastElement();
                    if(t.finished){
                        break;
                    }
                    t.x = pcb.DashedLines_X; t.y = pcb.DashedLines_Y;
                }
                break;
            default:
                break;
        }
        pcb.repaint();
    }

    private void updateDashedLines(MouseEvent e){
        pcb.DashedLines_X = (e.getX()%pcb.DeltaX>pcb.DeltaX/2) ? e.getX()-e.getX()%pcb.DeltaX+pcb.DeltaX : e.getX()-e.getX()%pcb.DeltaX;
        pcb.DashedLines_Y = (e.getY()%pcb.DeltaY>pcb.DeltaY/2) ? e.getY()-e.getY()%pcb.DeltaY+pcb.DeltaY : e.getY()-e.getY()%pcb.DeltaY;
    }
}
