package ctlOperator;

import java.util.Objects;

import ctlFormula.Formula;

import static ctlFormula.True.True;
import static ctlOperator.EU.EU;

public class EF implements Formula {

    private final Formula operand;

    public static EF EF(Formula operand) {
        return new EF(operand);
    }

    public EF(Formula operand) {
        this.operand = operand;
    }
    
    public int type() {
		return 0;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EF not = (EF) o;
        return Objects.equals(operand, not.operand);
    }

    public int hashCode() {
        return Objects.hash(operand);
    }

    public String toString() {
        return "EF " + operand;
    }

    public Formula convertToCTLBase() {
        return EU(True(), operand).convertToCTLBase();
    }
	
}
