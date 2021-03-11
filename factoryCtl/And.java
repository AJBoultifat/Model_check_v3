package factoryCtl;

public class And extends Formula {

    private Formula leftSubformula;
    private Formula rightSubformula;

    public And( Formula    leftSubformula,
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
        return Formula.TYPE_AND;
    }
    
    public String toString() {
        return "(" + leftSubformula + " & " + rightSubformula + ")";
    }
}
