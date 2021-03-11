package factoryCtl;

public class ExistsState extends Formula {

    private String variable;
    private Formula subformula;

    public ExistsState( String variable,
                        Formula    subformula) {
        this.variable = variable;
        this.subformula = subformula;
    }
    
    public String getVariable() {
        return variable;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_EXISTS_STATE;
    }
    
    public String toString() {
        return "]" + variable + ". " + subformula;
    }
}
