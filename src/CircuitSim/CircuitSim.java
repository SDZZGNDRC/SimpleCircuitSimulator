package CircuitSim;

import java.util.Vector;

// 主线程应该将用户绘制的电路转化为有向图存储在该类中
// 有向图的实现形式为邻接表
public class CircuitSim {
    public Vector<Node> nodes = new Vector<Node>(); // 存放电路中所有节点
    public boolean changed; // 表明电路模型已被主线程更新
    public Object lock = new Object(); // 同步锁, 读写该类对象前后都要锁上/释放该锁
}
