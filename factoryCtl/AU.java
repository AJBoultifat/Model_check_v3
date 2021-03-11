package factoryCtl;

public class AU extends Formula {

    private Formula leftSubformula;
    private Formula rightSubformula;

    public AU(  Formula    leftSubformula,
                Formula    rightSubformula) {
        this.leftSubformula  = leftSubformula;
        this.rightSubformula = rightSubformula;
    }
    
    public Formula getLeftSubformula() {
        return leftSubformula;
    }
    
    public int type() {
        return Formula.TYPE_AU;
    }
    
    public Formula getRightSubformula() {
        return rightSubformula;
    }
    
    public String toString() {
        return "A[" + leftSubformula + " U " + rightSubformula + "]";
    }
}
