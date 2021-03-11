package factoryBooleanModel;

public class NextStateAssignment {

    private Variable variable;
    private Formula formula1;
    private Formula formula2;
    public NextStateAssignment( Variable        variable,
                                Formula    formula1,
                                Formula    formula2) {
        this.variable  = variable;
        this.formula1 = formula1;
        this.formula2 = formula2;
    }
    
    public Variable getVariable() {
        return variable;
    }
    
    public Formula getFormula1() {
        return formula1;
    }
    
    public Formula getFormula2() {
        return formula2;
    }
    public String toString() {
        return variable + " := " + formula1 + (formula2==null ? "" : (", " + formula2)) + ";";
    }
}
