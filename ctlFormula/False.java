package ctlFormula;

public class False implements Formula {

    public static False False() {
        return new False();
    }

    public Formula convertToCTLBase() {
        return this;
    }

    public String toString() {
        return "false";
    }
    
	public int type() {
		return 0;
	}

    public boolean equals(Object o) {
        return this == o || (getClass() == o.getClass());
    }

    public int hashCode() {
        return 0;
    }



}
