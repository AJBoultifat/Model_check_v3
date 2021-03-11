package factoryBooleanModel;

import java.math.BigInteger;

import factoryCtl.QuantifierSubject;
import factoryCtl.Formula;
import factoryManager.NominalCounter;

public class Nominal extends Formula implements QuantifierSubject {

    private String stringRepresentation;
    private BigInteger value;
    private NominalCounter counter;
    private int level;
    public int getLevel() {
		return level;
	}
    public Nominal(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
        String tmp = stringRepresentation.toLowerCase();
        if ( tmp.startsWith("0x")) {
            value = new BigInteger(tmp.substring(2), 16);
        } else if ( tmp.startsWith("0b")) {
            value = new BigInteger(tmp.substring(2), 2);
        } else {
            value = new BigInteger(tmp, 10);
        }
        counter = new NominalCounter(value);
    }
    public Nominal(BigInteger value) {
        this.value = value;
        this.stringRepresentation = value.toString();
        counter = new NominalCounter(value);
    }
    public Nominal(NominalCounter counter) {
        this.counter = counter;
    }
    public boolean testBit(int n) {
        if (value == null) return counter.testBit(n);
        else  return value.testBit(n);
    }
    public int type() {
        return Formula.TYPE_NOMINAL;
    }
    public String toString() {
        if (stringRepresentation == null) return counter.toString();
        return stringRepresentation;
    }
}
