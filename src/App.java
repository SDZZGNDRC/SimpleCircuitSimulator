import GUI.mainGUI;
import CircuitSim.*;

public class App {
    public static void main(String[] args) throws Exception {
        mainGUI gui = new mainGUI();
        NetList net = new NetList();
        net.components.add(new I_DC_VS("V1", 1, 0));
        net.components.add(new Wire("W1", 1, 2));
        net.components.add(new Resistance("R1", 2, 3));
    }
}