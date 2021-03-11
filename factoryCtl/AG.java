package factoryCtl;

public class AG extends Formula {

    private Formula subformula;

    public AG(Formula    subformula) {
        this.subformula = subformula;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_AG;
    }
    
    public String toString() {
        return "AG(" + subformula + ")";
    }
}
