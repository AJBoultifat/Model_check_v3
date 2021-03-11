package factoryCtl;

public class EU extends Formula {

    private Formula leftSubformula;
    private Formula rightSubformula;

    public EU(  Formula    leftSubformula,
                Formula    rightSubformula) {
        this.leftSubformula  = leftSubformula;
        this.rightSubformula = rightSubformula;
    }
    
    public Formula getLeftSubformula() {
        return leftSubformula;
    }
    
    public Formula getRightSubformula() {
        return rightSubformula;
    }
    
    public int type() {
        return Formula.TYPE_EU;
    }
    
    public String toString() {
        return "E[" + leftSubformula + " U " + rightSubformula + "]";
    }
}
