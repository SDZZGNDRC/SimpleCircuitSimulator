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
    public Component create(Class<?> c){
        int name_index = 0;
        int Np_index = 0;
        try {
            if(c==Wire.class){
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
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }
        return null;
    }
}
