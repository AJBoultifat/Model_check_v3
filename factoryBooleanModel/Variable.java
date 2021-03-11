package factoryBooleanModel;

public class Variable extends Formula {
    private String name;
    public Variable(String          name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return name;
    }
}
