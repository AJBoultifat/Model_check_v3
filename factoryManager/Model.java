package factoryManager;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDPairing;

public interface Model {
    public void                 initBDDEngine();
    public void                 makeTransitionsAsynchronous();
    public void                 makeTransitionsStrictlyAsynchronous();
    public int                  getVarNum();
    public long                 getTransitionsBDDCreationTime();
    public SortedSet<String>    getVariables();
    public BDD                  getTransitionsBDD();
    public BDD                  getBooleanConstantBDD(boolean b);
    public BDD                  getVariableBDD(String varname);
    public BDD                  getVariableBDD(int varNum);
    public BDD                  getVariableNegBDD(String varname);
    public BDD                  getVariableNegBDD(int varNum);
    public BDD                  getVarSet();
    public BDD                  ithVar(int var);
    public BDD                  getPrimedVarSet();
    public BDDPairing           getToPrimePairing();
    public BDDPairing           getFromPrimePairing();
    public Set<Set<String>>     labelsBDDToStringSet(BDD bdd);
    public List<byte[]>         labelsBDDToArray(BDD bdd);
    public String               getEFPFormula(boolean isConjunctive);
}
