package ctlFormula;

public interface Formula {
    public final static int TYPE_BOOLEAN_CONST =  1;
    public final static int TYPE_VARIABLE      =  2;
    public final static int TYPE_NOT           =  3;
    public final static int TYPE_AND           =  4;
    public final static int TYPE_OR            =  5;
    public final static int TYPE_IMP           =  6;
    public final static int TYPE_IFF           =  7;
    public final static int TYPE_EX            =  8;
    public final static int TYPE_EY            =  9;
    public final static int TYPE_EF            = 10;
    public final static int TYPE_EG            = 11;
    public final static int TYPE_EU            = 12;
    public final static int TYPE_AX            = 13;
    public final static int TYPE_AY            = 14;
    public final static int TYPE_AF            = 15;
    public final static int TYPE_AG            = 16;
    public final static int TYPE_AU            = 17;
    public final static int TYPE_NOMINAL       = 18;
    public final static int TYPE_AT_STATE      = 19;
    public final static int TYPE_EXISTS_STATE  = 20;
    public final static int TYPE_STATE_BINDER  = 21;
    
    public abstract int type();
    
    public Formula convertToCTLBase();
}
