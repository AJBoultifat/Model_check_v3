package ctlOperator;

import java.util.Objects;

import ctlFormula.Formula;

public class EU implements Formula {

    private final Formula operand1;
    private final Formula operand2;

    public static EU EU(Formula operand1, Formula operand2) {
        return new EU(operand1, operand2);
    }

    public EU(Formula operand1, Formula operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EU au = (EU) o;
        return Objects.equals(operand1, au.operand1) &&
                Objects.equals(operand2, au.operand2);
    }

    public int hashCode() {
        return Objects.hash(operand1, operand2);
    }
    
    public int type() {
		return 0;
	}

    public String toString() {
        return "E(" + operand1 + " U " + operand2 + ")";
    }

    public Formula getOperand1() {
        return operand1;
    }

    public Formula getOperand2() {
        return operand2;
    }

    public Formula convertToCTLBase() {
        return EU(operand1.convertToCTLBase(), operand2.convertToCTLBase());
    }

}
