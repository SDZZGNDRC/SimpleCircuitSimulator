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
        int name_index = 0;
        int Np_index = 0;
        try {
            if(c==Wire.class){ // 创建新的导线
                for(Component i : components){
                    if(!(i instanceof Wire)){
                        continue;
                    }
                    if (name_index <= Integer.parseInt(i.name.substring(1))) {
                        name_index = Integer.parseInt(i.name.substring(1))+1;
                    }
                    if (Np_index <= i.Np){
                        Np_index = i.Np+2;
                    }
                }
                return new Wire("W"+Integer.toString(name_index),Np_index,Np_index+1);
            }else if(c==Resistance.class){ // 创建新的电阻
                for(Component i : components){
                    if(!(i instanceof Resistance)){
                        continue;
                    }
                    if (name_index <= Integer.parseInt(i.name.substring(1))) {
                        name_index = Integer.parseInt(i.name.substring(1))+1;
                    }
                    if (Np_index <= i.Np){
                        Np_index = i.Np+2;
                    }
                }
                return new Resistance("R"+Integer.toString(name_index),Np_index,Np_index+1);
            }else if(c==I_DC_VS.class){ // 创建新的独立直流电压源
                for(Component i : components){
                    if(!(i instanceof I_DC_VS)){
                        continue;
                    }
                    if (name_index <= Integer.parseInt(i.name.substring(1))) {
                        name_index = Integer.parseInt(i.name.substring(1))+1;
                    }
                    if (Np_index <= i.Np){
                        Np_index = i.Np+2;
                    }
                }
                return new I_DC_VS("V"+Integer.toString(name_index),Np_index,Np_index+1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }
        return null;
    }
}
