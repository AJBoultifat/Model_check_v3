package factoryCtl;

public class Variable extends Formula implements QuantifierSubject {

    private String name;

    public Variable(String  name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public int type() {
        return Formula.TYPE_VARIABLE;
    }
    
    public String toString() {
        return name;
    }
}
