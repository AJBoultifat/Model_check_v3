package ctlOperator;

import java.util.Objects;

import ctlFormula.Formula;

import static ctlBooleanOperator.Not.not;
import static ctlOperator.AF.AF;

public class EG implements Formula {

    private final Formula operand;

    public static EG EG(Formula operand) {
        return new EG(operand);
    }

    public EG(Formula operand) {
        this.operand = operand;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EG not = (EG) o;
        return Objects.equals(operand, not.operand);
    }
    
    public int type() {
		return 0;
	}

    public int hashCode() {
        return Objects.hash(operand);
    }

    public String toString() {
        return "EG " + operand;
    }

    public Formula convertToCTLBase() {
        return not(AF(not(operand))).convertToCTLBase();
    }
	
}
