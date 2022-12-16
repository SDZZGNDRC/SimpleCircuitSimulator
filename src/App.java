public class App {
    public static void main(String[] args) throws Exception {
        Son son = new Son();
        Father father = son;
        father.getName();
        son.setName("Son");
        son.getName();
        father.getName();
    }
}

class Father{
    protected String name = "Father";
    public void getName() {
        System.out.println(this.name);
    }
}

class Son extends Father{
    // @Override
    // public void getName() {
    //     System.out.println(this.name);
    // }
    public void setName(String s) {
        name = s;
    }
}
