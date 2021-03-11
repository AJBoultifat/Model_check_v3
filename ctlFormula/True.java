package ctlFormula;

public class True implements Formula {

    public static True True() {
        return new True();
    }

    public Formula convertToCTLBase() {
        return this;
    }

    public String toString() {
        return "true";
    }
    
    public int type() {
		return 0;
	}

    public boolean equals(Object o) {
        return this == o || (getClass() == o.getClass());
    }

    public int hashCode() {
        return 1;
    }

}
