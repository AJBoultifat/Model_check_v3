package ctlOperator;

import java.util.Objects;

import ctlFormula.Formula;

import static ctlFormula.True.True;
import static ctlOperator.AU.AU;

public class AF implements Formula {

    private final Formula operand;

    public static AF AF(Formula operand) {
        return new AF(operand);
    }

    public AF(Formula operand) {
        this.operand = operand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AF not = (AF) o;
        return Objects.equals(operand, not.operand);
    }

    public int hashCode() {
        return Objects.hash(operand);
    }

    public String toString() {
        return "AF " + operand;
    }

    public Formula convertToCTLBase() {
        return AU(True(), operand).convertToCTLBase();
    }

	public int type() {
		return 0;
	}
}
