package ctlFormula;

import java.util.Objects;

public class Atom implements Formula {
    private final String atomicPredicate;

    public Atom(String atomicPredicate) {
        this.atomicPredicate = atomicPredicate;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atom that = (Atom) o;
        return Objects.equals(atomicPredicate, that.atomicPredicate);
    }

    public int hashCode() {
        return Objects.hash(atomicPredicate);
    }

    public static Atom atom(String atomicPredicate) {
        return new Atom(atomicPredicate);
    }
    
	public int type() {
		return 0;
	}
	
    public static Atom predicate(String atomicPredicate, String value) {
        return new Atom(atomicPredicate+value);
    }

    public String toString() {
        return atomicPredicate;
    }

    public Formula convertToCTLBase() {
        return this;
    }


}
