package factoryCtl;

public class Or extends Formula {

    private Formula leftSubformula;
    private Formula rightSubformula;

    public Or(  Formula    leftSubformula,
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
        return Formula.TYPE_OR;
    }
    
    public String toString() {
        return "(" + leftSubformula + " | " + rightSubformula + ")";
    }
}
