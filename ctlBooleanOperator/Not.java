package ctlBooleanOperator;

import java.util.Objects;

import ctlFormula.Formula;

public class Not implements Formula {

    private final Formula operand;

    public Not(Formula operand) {
        this.operand = operand;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Not not = (Not) o;
        return Objects.equals(operand, not.operand);
    }

    public int hashCode() {
        return Objects.hash(operand);
    }

    public static Formula not(Formula op) {
        return new Not(op);
    }


    public String toString() {
        return "NOT (" + operand + ")";
    }

    public Formula getOperand() {
        return operand;
    }

    public Formula convertToCTLBase() {
        return not(operand.convertToCTLBase());
    }

	public int type() {
		return 0;
	}
}
