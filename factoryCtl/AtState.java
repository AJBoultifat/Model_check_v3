package factoryCtl;

import factoryBooleanModel.Nominal;

public class AtState extends Formula {

    private QuantifierSubject subject;
    private Formula subformula;

    public AtState( String          subjectString,
                    Formula    subformula) {
        subjectString = subjectString.substring(1).trim(); // discard '@' and leading spaces
        this.subject = (Character.isDigit(subjectString.charAt(0)) ?
            new Nominal(subjectString) : new Variable(subjectString));
        this.subformula = subformula;
    }
    
    public QuantifierSubject getSubject() {
        return subject;
    }
    
    public Formula getSubformula() {
        return subformula;
    }
    
    public int type() {
        return Formula.TYPE_AT_STATE;
    }
    
    public String toString() {
        return "@" + subject + ". " + subformula;
    }
}
