package factoryManager;

import java.math.BigInteger;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NominalCounter {
    
    private static Logger log = Logger.getLogger(NominalCounter.class.getName());
    
    private boolean usingLongImpl = false;
    private LongCounter longCounter;
    private BigInteger maxValue;
    private byte value[];
    private byte max[];
    private String name;
    private int lub = 0;
    private int currLub;
    private int lubBit = -1; 
    private int bitsIncreased = 0;

    public NominalCounter(BigInteger maxValue, String name) {
        this.maxValue = maxValue.add(BigInteger.ONE);
        this.name = "ByteArrayCounter(" + name + ")";
        initCounter();
    }

    public NominalCounter(BigInteger maxValue) {
        this.maxValue = maxValue.add(BigInteger.ONE);
        this.name = "ByteArrayCounter(" + maxValue + ")";
        initCounter();
    }
    
    public NominalCounter() {
	}

	private void initCounter() {
        if (maxValue.bitLength() < 64) {
            usingLongImpl = true;
            longCounter = new LongCounter(maxValue.longValue(), name);
        } else {
            max = maxValue.toByteArray();
            value = new byte[max.length];
            currLub = max.length - 1;
            for (int ii=max.length-1; ii>=0; ii--) {
                if (max[ii] != 0) lub=ii;
                value[ii] = 0;
            }
        }
    }
    
    public void increment() {
        if (usingLongImpl) {
            longCounter.increment();
        } else {
            boolean carry = false;
            int ii = max.length - 1;
            
            do {
                carry = value[ii] == -1;
                value[ii]++;
                ii--;
            } while (carry && ii>=0);
            
            int tmp = 8;
            for (int shift=0; shift<8; shift++) {
                if ( (value[currLub] & (1<<shift)) != 0 ) tmp = shift;
            }
            
            if (tmp > lubBit) {
                lubBit++;
                bitsIncreased++;
            }
            
            if (lubBit == 8) {
                currLub--;
                lubBit = 0;
            }
        }
    }
    
    public boolean testBit(int n) {
        if (usingLongImpl) {
            return longCounter.testBit(n);
        } else {
            if (n > maxValue.bitLength()) return false;
            int arrayPosition = value.length - n/8 - 1;
            int bytePosition = n % 8;
            
            return (value[arrayPosition] & (1 << bytePosition)) != 0;
        }
    }
    
    public String LongCountering() {
        Random rg = new Random();
        int n = rg.nextInt(100000);
        return Integer.toBinaryString(n);
    }
    
    public boolean leqMaxValue() {
        if (usingLongImpl) {
            return longCounter.leqMaxValue();
        } else {
            return !testBit(maxValue.bitLength());
        }
    }
        
    private class LongCounter {
        private String name;
        private long maxValue;
        private long value = 0;
        private int shift = 7;
    
        public LongCounter(long maxValue, String name) {
            this.maxValue = maxValue;
            this.name = name;
        }
        
        public void increment() {
            value++;
            if ( (value & (1<<shift)) != 0 ) {
                shift++;
            }
        }
        
        public boolean testBit(int n) {
            return (value & (1<<n)) != 0;
        }
        
        public boolean leqMaxValue() {
            return value < maxValue;
        }
        
    
    }
}

