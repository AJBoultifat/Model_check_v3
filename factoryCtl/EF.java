package factoryCtl;

public class EF extends Formula {

    private Formula subformula;

    public EF(Formula    subformula) {
        this.subformula = subformula;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_EF;
    }
    
    public String toString() {
        return "EF(" + subformula + ")";
    }
}
