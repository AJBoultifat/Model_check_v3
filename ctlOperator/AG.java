package ctlOperator;

import java.util.Objects;

import ctlFormula.Formula;

import static ctlBooleanOperator.Not.not;
import static ctlOperator.EF.EF;

public class AG implements Formula {

    private Formula operand;

    public AG(Formula operand) {
        this.operand = operand;
    }
    
    public static AG AG(Formula operand) {
        return new AG(operand);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AG not = (AG) o;
        return Objects.equals(operand, not.operand);
    }

    public int hashCode() {
        return Objects.hash(operand);
    }

    public String toString() {
        return "AG " + operand;
    }

    public Formula convertToCTLBase() {
        return not(EF(not(operand))).convertToCTLBase();
    }

	public int type() {
		return 0;
	}
}
