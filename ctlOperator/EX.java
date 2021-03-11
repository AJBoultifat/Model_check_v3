package ctlOperator;

import java.util.Objects;

import ctlFormula.Formula;

public class EX implements Formula {

    private final Formula operand;

    public static EX EX(Formula operand) {
        return new EX(operand);
    }

    public EX(Formula operand) {
        this.operand = operand;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EX not = (EX) o;
        return Objects.equals(operand, not.operand);
    }

    public int hashCode() {
        return Objects.hash(operand);
    }

    public String toString() {
        return "EX " + operand;
    }

    public Formula getOperand() {
        return operand;
    }

    public Formula convertToCTLBase() {
        return EX(operand.convertToCTLBase());
    }

	public int type() {
		return 0;
	}
}
