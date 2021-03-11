package factoryCtl;

public class EG extends Formula {

    private Formula subformula;

    public EG(Formula    subformula) {
        this.subformula = subformula;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_EG;
    }
    
    public String toString() {
        return "EG(" + subformula + ")";
    }
}
