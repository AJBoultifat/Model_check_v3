package factoryCtl;

public class StateBinder extends Formula {

    private Formula subformula;
    private String       variable;

    public StateBinder( String          variable,
                        Formula    subformula) {
        this.subformula = subformula;
        this.variable = variable;
    }
    
    public String getVariable() {
        return variable;
    }
    
    public int type() {
        return Formula.TYPE_STATE_BINDER;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public String toString() {
        return "!" + variable + "." + subformula;
    }
}
