package factoryCtl;

public class EX extends Formula {

    private Formula subformula;

    public EX(Formula    subformula) {
        this.subformula = subformula;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_EX;
    }
    
    public String toString() {
        return "EX(" + subformula + ")";
    }
}
