package factoryCtl;

public class AF extends Formula {

    private Formula subformula;

    public AF(Formula    subformula) {
        this.subformula = subformula;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_AF;
    }
    
    public String toString() {
        return "AF(" + subformula + ")";
    }
}
