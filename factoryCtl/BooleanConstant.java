package factoryCtl;

public class BooleanConstant extends Formula {

    private boolean value;

    public BooleanConstant(boolean     value) {
        this.value = value;
    }
    
    public boolean getValue() {
        return value;
    }
    
    public int type() {
        return Formula.TYPE_BOOLEAN_CONST;
    }
    
    public String toString() {
        return "" + value;
    }
}
