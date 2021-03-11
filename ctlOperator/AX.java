package ctlOperator;

import java.util.Objects;

import ctlFormula.Formula;

public class AX implements Formula {

    private final Formula operand;

    public static AX AX(Formula operand) {
        return new AX(operand);
    }

    public AX(Formula operand) {
        this.operand = operand;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AX not = (AX) o;
        return Objects.equals(operand, not.operand);
    }
    
    public int type() {
		return 0;
	}
    
    public int hashCode() {
        return Objects.hash(operand);
    }

    public String toString() {
        return "AX " + operand;
    }

    public Formula getOperand() {
        return operand;
    }

    public Formula convertToCTLBase() {
        return AX(operand.convertToCTLBase());
    }

	
}
