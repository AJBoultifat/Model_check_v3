package ctlBooleanOperator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import ctlFormula.Formula;

public class Or implements Formula {

    private Set<Formula> operands = new HashSet<>();

    public Or(Formula... operands) {
        Collections.addAll(this.operands, operands);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Or or = (Or) o;
        return Objects.equals(operands, or.operands);
    }

    public int hashCode() {
        return Objects.hash(operands);
    }

    public static Or or(Formula... operands) {
        return new Or(operands);
    }

    public String toString() {
        return "(" + operands.stream().map(Formula::toString).collect(Collectors.joining(" OR ")) + ")";
    }

    public Set<Formula> getOperands() {
        return new HashSet<>(operands);
    }

    public Formula convertToCTLBase() {
        return or(operands.stream().map(Formula::convertToCTLBase).collect(Collectors.toList()).toArray(new Formula[]{}));
    }

	public int type() {
		return 0;
	}}
