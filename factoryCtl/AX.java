package factoryCtl;

public class AX extends Formula {

    private Formula subformula;

    public AX(Formula    subformula) {
        this.subformula = subformula;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_AX;
    }
    
    public String toString() {
        return "AX(" + subformula + ")";
    }
}
