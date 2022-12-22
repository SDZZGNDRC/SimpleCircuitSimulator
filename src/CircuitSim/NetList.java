package CircuitSim;

import java.util.Vector;

public class NetList {
    public Vector<Component> components;
    public NetList() {
        this.components = new Vector<Component>();
    }
    @Override
    public String toString() {
        String s = new String();
        for(Component i : components){
            s = s.concat(i.toString()+"\n");
        }
        return s;
    }

    // 在保证名字, 编号等参数不重复的情况下创建新的部件
    public Component create(Class<?> c){
        try {
            int Np = getNextNode();
            int Nm = getNextNode(Np+1);
            if(c==Wire.class){ // 创建新的导线
                return new Wire("W"+Integer.toString(getNextNameID('W')),Np, Nm);
            }else if(c==Resistance.class){ // 创建新的电阻
                return new Resistance("R"+Integer.toString(getNextNameID('R')),Np, Nm);
            }else if(c==I_DC_VS.class){ // 创建新的独立直流电压源
                return new I_DC_VS("V"+Integer.toString(getNextNameID('R')),Np, Nm);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }
        return null;
    }

    // 获取从_start开始, 下一个最小的, 没有用过的节点编号
    public int getNextNode(int _start){
        int i = _start;
        for (i = _start; hasNode(i); i++);
        return i;
    }
    // 从0开始算起...
    public int getNextNode(){
        int i = 0;
        for (i = 0; hasNode(i); i++);
        return i;
    }

    // 判断是否存在节点编号
    public boolean hasNode(int _id){
        for (Component component : components) {
            if(component.Np == _id || component.Nm == _id){
                return true;
            }
        }
        return false;
    }

    // 获取下一个最小的, 没有用过的名称编号
    public int getNextNameID(char type){
        int i = 0;
        for(i = 0; hasNameID(type, i); i++);
        return i;
    }

    // 判断是否存在名称编号
    public boolean hasNameID(char type, int _id){
        for (Component component : components) {
            if(component.name.charAt(0)!=type){
                continue;
            }
            if(Integer.parseInt(component.name.substring(1)) == _id){
                return true;
            }
        }
        return false;
    }
    
}
