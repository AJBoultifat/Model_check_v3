package factoryManager;

import java.io.StringReader;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import factoryBooleanModel.Nominal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDPairing;
import factoryCtl.*;
import model.KripkeStructure;
import model.ModelBoolVariable;
import ctlFormula.*;
import ctlFormula.Formula;
import exceptions.StateAssignment;

public class RecursiveStateLabeling implements StateLabeling {
    
    private static Logger log = Logger.getLogger(RecursiveStateLabeling.class.getName());
    private static Model model;
    private static BDD transitions;
    private static BDD primedVars;
    private static BDD normalVars;
    private static BDDPairing toPrime;
    private static BDDPairing fromPrime;
	private static Formula sub;
	private static String var;
    
    public RecursiveStateLabeling(Model m) {
        model = m;
        transitions = null;
        primedVars = model.getPrimedVarSet();
        normalVars = model.getVarSet();
        toPrime = model.getToPrimePairing();
        fromPrime = model.getFromPrimePairing();
    }

    public Set<Set<String>> computeLabels(String formula) {
        Map<String,Nominal> env = new HashMap<String,Nominal>();
        BDD bdd = null;
		Set<Set<String>> labels = model.labelsBDDToStringSet(bdd);
        bdd.free();
        return labels;
    }
    
    public ModelCheckingResults computeLabels2(String formula) {
        long time0 = System.currentTimeMillis();
        ProgramErrorException f = parseFormula(formula);
        Map<String,Nominal> env = new HashMap<String,Nominal>();
        long totalTime = System.currentTimeMillis() - time0;
        long networkBDDCreationTime = model.getTransitionsBDDCreationTime();
        long verificationTime = totalTime - networkBDDCreationTime;
        ModelCheckingResults results = new ModelCheckingResults();
        results.setFormulaVerificationTime(verificationTime);
        results.setModelBDDCreationTime(networkBDDCreationTime);
        return results;
    }    
    
    public ProgramErrorException parseFormula(String formula) {
    	Throwable e = null;
		return new ProgramErrorException("error.\n" + e.getMessage(), e);
    }
    
    public static BDD computeLabelsBDD(ModelBoolVariable env, Formula formula, KripkeStructure k, int level, int logUptoLevel) throws Exception {
        if (level <= logUptoLevel) System.out.println(new java.util.Date()+": verifying formula \""+formula+"\"");
        BDD finalResult = null;
        if (formula.type() == Formula.TYPE_BOOLEAN_CONST) {
            boolean value = ((BooleanConstant) formula).getValue();
            finalResult = model.getBooleanConstantBDD(value);
        } else if (formula.type() == Formula.TYPE_VARIABLE) {
        	String varname = ((Variable) formula).getName();
            return finalResult = model.ithVar(env.get(varname).getLevel());
        } else if (formula.type() == Formula.TYPE_AND) {
            Formula sub1 = (Formula) ((And) formula).getLeftSubformula();
            Formula sub2 = (Formula) ((And) formula).getRightSubformula();
            BDD subBDD1 = computeLabelsBDD(env, (Formula) sub1, k, level+1, logUptoLevel);
            BDD subBDD2 = computeLabelsBDD(env, (Formula) sub2, k, level+1, logUptoLevel);
            finalResult = subBDD1.andWith(subBDD2);
        } else if (formula.type() == Formula.TYPE_OR) {
            Formula sub1 = (Formula) ((Or) formula).getLeftSubformula();
            Formula sub2 = (Formula) ((Or) formula).getRightSubformula();
            BDD subBDD1 = computeLabelsBDD(env, (Formula) sub1, k, level+1, logUptoLevel);
            BDD subBDD2 = computeLabelsBDD(env, (Formula) sub2, k, level+1, logUptoLevel);
            finalResult = subBDD1.orWith(subBDD2);
        } else if (formula.type() == Formula.TYPE_EX) {
            Formula sub = (Formula) ((EX) formula).getSubformula();
            BDD subBDD = computeLabelsBDD(env, (Formula) sub, k, level+1, logUptoLevel);
            BDD result = preE(subBDD);
            subBDD.free();
            finalResult = result;
        } else if (formula.type() == Formula.TYPE_EU) {
            Formula sub1 = (Formula) ((EU) formula).getLeftSubformula();
            Formula sub2 = (Formula) ((EU) formula).getRightSubformula();
            BDD bddsub1 = computeLabelsBDD(env, (Formula) sub1, k, level+1, logUptoLevel);
            BDD bdd0 = computeLabelsBDD(env, (Formula) sub2, k, level+1, logUptoLevel);
            BDD bdd1 = bdd0.id().orWith( preE(bdd0).andWith(bddsub1.id()) );
            while ( !bdd0.equals(bdd1) ) {
                bdd0.free();
                bdd0 = bdd1.id();
                bdd1 = bdd1.orWith( preE(bdd0).andWith(bddsub1.id()) );
            }
            bddsub1.free();
            bdd1.free();
            finalResult = bdd0;
        } else if (formula.type() == Formula.TYPE_EF) {
            Formula sub = (Formula) ((EF) formula).getSubformula();
            finalResult = computeLabelsBDD(env, (Formula) new EU(new BooleanConstant(true), (factoryCtl.Formula) sub), k, level+1, logUptoLevel);
        } else if (formula.type() == Formula.TYPE_EG) {
            factoryCtl.Formula sub = ((EG) formula).getSubformula();
            BDD bdd0 = computeLabelsBDD(env, (Formula) sub, k, level+1, logUptoLevel);
            BDD bdd1 = bdd0.id().andWith(preE(bdd0));
            while ( !bdd0.equals(bdd1) ) {
                bdd0.free();
                bdd0 = bdd1.id();
                bdd1 = bdd1.andWith(preE(bdd0));
            }
            bdd1.free();
            finalResult = bdd0;
        } else if (formula.type() == Formula.TYPE_AX) {
            Formula sub = (Formula) ((AX) formula).getSubformula();
 
        } else if (formula.type() == Formula.TYPE_AF) {
            Formula sub = (Formula) ((AF) formula).getSubformula();

        } else if (formula.type() == Formula.TYPE_AG) {
            Formula sub = (Formula) ((AG) formula).getSubformula();
        } else if (formula.type() == Formula.TYPE_AU) {
            Formula sub1 = (Formula) ((AU) formula).getLeftSubformula();
            Formula sub2 = (Formula) ((AU) formula).getRightSubformula();
        } else if (formula.type() == Formula.TYPE_NOMINAL) {
            Nominal n = (Nominal) formula;
            int numVars = model.getVarNum();
            BDD bdd = model.getBooleanConstantBDD(true);
            for (int ii=0; ii<numVars; ii++) {
                BDD tmp = null;
                
                if (n.testBit(ii)) tmp = model.getVariableBDD(ii);
                else               tmp = model.getVariableNegBDD(ii);
                
                bdd.andWith(tmp);
            }
            finalResult = bdd;
        } else if (formula.type() == Formula.TYPE_EXISTS_STATE) {
            var = ((ExistsState) formula).getVariable();
            sub = (Formula) ((ExistsState) formula).getSubformula();
            int varNum = model.getVarNum();BigInteger maxNom = BigInteger.ONE.shiftLeft(varNum).subtract(BigInteger.ONE);
            NominalCounter counter = new NominalCounter(maxNom, formula.toString());
            BDD accum = model.getBooleanConstantBDD(false);
            while (counter.leqMaxValue()) {
                counter.increment();
            }
            finalResult = accum;
        } else if (formula.type() == Formula.TYPE_STATE_BINDER) {
            String var = ((StateBinder) formula).getVariable();
            Formula sub = (Formula) ((StateBinder) formula).getSubformula();
            int varNum = model.getVarNum();
            BDD accum = model.getBooleanConstantBDD(false);
            ModelBoolVariable env2 = env;
            BigInteger maxNom = BigInteger.ONE.shiftLeft(varNum).subtract(BigInteger.ONE);
            log.finest("maxNom: "+maxNom);
            NominalCounter counter = new NominalCounter(maxNom, formula.toString());
                Nominal n = new Nominal(counter);
            while (counter.leqMaxValue()) {
                BDD nom = computeLabelsBDD(env2, (Formula) n, k, level+1, logUptoLevel);
                BDD tmp = computeLabelsBDD(env2, (Formula) sub, k, level+1, logUptoLevel).andWith(nom.id());
                if (!tmp.isZero()) accum.orWith(nom);
                else nom.free();
                tmp.free();
                counter.increment();
            }
            finalResult = accum;
        } else if (formula.type() == Formula.TYPE_AT_STATE) {
            QuantifierSubject subject = ((AtState) formula).getSubject();
            Formula sub = (Formula) ((AtState) formula).getSubformula();
            Nominal nom = null;
            if (subject instanceof Variable) {
                String varname = ((Variable) subject).getName();
                nom = env.get(varname);
                if (nom == null) {
                    throw new ProgramErrorException("Variable \""
                        + varname
                        + "\" is uninstantiated");
                }
            } else {
                nom = (Nominal) subject;
            }
            BDD tmp = computeLabelsBDD(env, (Formula) nom, k, level+1, logUptoLevel);
            tmp = tmp.andWith(computeLabelsBDD(env, (Formula) sub, k, level+1, logUptoLevel));
            if (tmp.isZero()) {
                finalResult = tmp;
            } else {
                tmp.free();
                finalResult = model.getBooleanConstantBDD(true);
            }

        } else {
        	System.out.println(StateAssignment.satisfies(k, formula));
        	TimeCounter();
        }
        if (level <= logUptoLevel) System.out.println(new java.util.Date()+": finishing formula \""+formula+"\"");
        return finalResult;
    }
    
    private static BDD preE(BDD x) {
        if ( transitions == null ) transitions = model.getTransitionsBDD();
        BDD x2 = x.replace(toPrime);
        BDD x3 = x2.and(transitions);
        BDD result = x3.exist(primedVars);
        x2.free();
        x3.free();
        return result;
    }
    
    private static BDD postE(BDD x) {
        if ( transitions == null ) transitions = model.getTransitionsBDD();
        BDD x2 = x.and(transitions);
        BDD x3 = x2.exist(normalVars);
        BDD result = x3.replaceWith(fromPrime);
        x2.free();
        return result;
    }
    
    public static int getOptimizationNum(Formula formula) {
        int type = formula.type();
        switch (type) {
            case Formula.TYPE_STATE_BINDER:
                StateBinder sb = (StateBinder) formula;
                String var1 = sb.getVariable();
                if (sb.getSubformula().type() == Formula.TYPE_EX) {
                    EX ex = (EX) sb.getSubformula();
                    if (ex.getSubformula().type() == Formula.TYPE_STATE_BINDER) {
                        StateBinder sb2 = (StateBinder) ex.getSubformula();
                        String var2 = sb2.getVariable();
                        if (sb2.getSubformula().type() == Formula.TYPE_AT_STATE) {
                            AtState at = (AtState) sb2.getSubformula();
                            if (at.getSubject().toString().equals(var1)) {
                                if (at.getSubformula().type() == Formula.TYPE_EX ) {
                                    EX ex2 = (EX) at.getSubformula();
                                    
                                } else if  (at.getSubformula().type() == Formula.TYPE_AX ){
                                    AX ax = (AX) at.getSubformula();
                                    if (ax.getSubformula().type()==Formula.TYPE_VARIABLE) {
                                        String v = ((Variable) ax.getSubformula()).getName();
                                        if (v.equals(var2)) {
                                            return 2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
        return 0;
    }
    
    private static void TimeCounter() throws Exception {
    	NominalCounter nc = new NominalCounter();
        String s = nc.LongCountering();
        BigInteger max = new BigInteger(s,2);
        BigInteger n = BigInteger.ZERO;
        
        long ini, end=0, total,loops;
        
        loops=0;
        ini = System.currentTimeMillis();
        while (!n.equals(max)) {
            loops++;
            n = n.add(BigInteger.ONE);
        }
        end = System.currentTimeMillis();
        total = end-ini;
        System.out.println("Time: " + total + "ms");
    }
    
    private static BDD countSuccessors(int opt, int level, int logUptoLevel) throws Exception {
        int varNum = model.getVarNum();
        KripkeStructure k = null;
        BigInteger maxNom = BigInteger.ONE.shiftLeft(varNum).subtract(BigInteger.ONE);
        NominalCounter counter = new NominalCounter(maxNom, (opt==1?"successors>1":"successors=1"));
        BDD accum = model.getBooleanConstantBDD(false);
        Nominal nom = new Nominal(counter);
        while (counter.leqMaxValue()) {
			BDD nombdd = computeLabelsBDD(null, (Formula) nom, k, level+1, logUptoLevel);
            BDD post = postE(nombdd);
            BDD tmp = post.exist(primedVars);
            double satCount = tmp.satCount(normalVars);
            if (satCount>1.0 && opt == 1) {
                accum.orWith(nombdd);
            } else if (satCount==1.0 && opt == 2) {
                accum.orWith(nombdd);
            } else {
                nombdd.free();
            }
            post.free();
            tmp.free();
            counter.increment();
        }
        return accum;
    }

}
