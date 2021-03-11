package model;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import factoryBooleanModel.*;
import factoryManager.*;
import ctlFormula.Atom;
import ctlFormula.Formula;


public class ModelBoolVariable {

	KripkeStructure kS = new KripkeStructure();

	ArrayList<VarBoolName> varsNames = new ArrayList<VarBoolName>();
	ArrayList<VarBoolName> opBufferBefore = new ArrayList<VarBoolName>();
	ArrayList<VarBoolName> opBufferAfter = new ArrayList<VarBoolName>();
	ArrayList<String> conditionVars;
	ArrayList<String> conditionOps;
	ArrayList<State> states = new ArrayList<State>();
	ArrayList<Boolean> stOps;
	
	public VarBoolName getBDDVariable(String variableName) {
		VarBoolName varboolname = null;
		for (int i = 0; i < varsNames.size(); i++) {
			if (varsNames.get(i).getVarName().equals(variableName)) {
				return varsNames.get(i);
			}
		}
		return varboolname;
	}

	public void CreateVariable(String variable, String... variableValues) {
		if (!CheckExistingVariable(variable)) {
			ArrayList<String> varValues = new ArrayList<>();
			for (String variableValue : variableValues) {
				varValues.add(variable+variableValue);
			}
			VarBoolName var = new VarBoolName(variable, varValues);
			varsNames.add(var);
		}

	}

	public void CreateModel() {		
		ArrayList<ArrayList<String>> lists = new ArrayList<>();
		for (int i = 0; i < varsNames.size(); i++) {
			ArrayList<String> listvarnames = varsNames.get(i).getVarValues();
			lists.add(listvarnames);
		}
		List<List<String>> A = computeStates(lists);
		for (int i = 0; i < A.size(); i++) {
			String StateName = "s"+i;
			State s = new State(StateName, (ArrayList<String>) A.get(i));
			states.add(s);
		}
		State s0 = getState("s0");
		kS.addInitialState(s0);
		ArrayList<State> states1 = (ArrayList<State>) states.clone();
		states1.remove(0);
		kS.addState(states1.toArray(new State[states1.size()]));
		
		
	}
	
	public static <T> List<List<T>> computeStates(ArrayList<ArrayList<String>> lists) {

	    List<List<T>> product = new ArrayList<List<T>>();

	    for (ArrayList<String> list : lists) {

	        List<List<T>> newProduct = new ArrayList<List<T>>();

	        for (String listElement : list) {

	            if (product.isEmpty()) {

	                List<T> newProductList = new ArrayList<T>();
	                newProductList.add((T) listElement);
	                newProduct.add(newProductList);
	            } else {

	                for (List<T> productList : product) {

	                    List<T> newProductList = new ArrayList<T>(productList);
	                    newProductList.add((T) listElement);
	                    newProduct.add(newProductList);
	                }
	            }
	        }

	        product = newProduct;
	    }

	    return product;
	}
	
	public static <T> List<List<T>> computeStates2(List<List<T>> lists) {
	    List<List<T>> combinations = Arrays.asList(Arrays.asList());
	    for (List<T> list : lists) {
	        List<List<T>> extraColumnCombinations = new ArrayList<>();
	        for (List<T> combination : combinations) {
	            for (T element : list) {
	                List<T> newCombination = new ArrayList<>(combination);
	                newCombination.add(element);
	                extraColumnCombinations.add(newCombination);
	            }
	        }
	        combinations = extraColumnCombinations;
	    }
	    return combinations;
	}
	
	public Nominal get(String varname) {
		return new Nominal(this.getState(varname).toString());
	}

	public boolean CheckExistingVariable(String variableName) {
		for (int i = 0; i < varsNames.size(); i++) {
			if (varsNames.get(i).getVarName().equals(variableName)) {
				return true;
			}
		}
		return false;
	}

	public int AND(String variable2, boolean boolVar2, String variable3, boolean boolVar3) {

		VarBoolName var = getBDDVariable(variable2);
		opBufferBefore.add(var);
		var.VarCondition(boolVar2);
		opBufferAfter.add(var);

		VarBoolName var2 = getBDDVariable(variable3);
		opBufferBefore.add(var);
		var.VarCondition(boolVar3);
		opBufferAfter.add(var);

		return 0;
	}

	public int AND(String variable2, boolean boolVar2, int operation) {
		return 2;
	}

	public int OR(String variable2, boolean boolVar2, String variable3, boolean boolVar3) {

		VarBoolName var = getBDDVariable(variable2);
		opBufferBefore.add(var);
		var.VarCondition(boolVar2);
		opBufferAfter.add(var);

		VarBoolName var2 = getBDDVariable(variable3);
		opBufferBefore.add(var);
		var.VarCondition(boolVar3);
		opBufferAfter.add(var);
		return 1;
	}

	public int OR(String variable2, boolean boolVar2, int operation) {
		return 3;
	}

	public int OP(int boolOp) {
		return -1;
	}

	public int OP(String variable2, boolean boolVar2, boolean boolvar2) {
		return -2;
	}

	public void condition(String variable1, String value, String... cons) {
		conditionVars = new ArrayList<>();
		conditionOps = new ArrayList<>();
		stOps = new ArrayList<>();
		ArrayList<ArrayList<String>> cals = new ArrayList<>();
		ArrayList<String> res = new ArrayList<>();
		String goalState ="";
		
		for (String con : cons) {
			if(con == "&&" || con == "||") {
				conditionOps.add(con);
			}else {
				conditionVars.add(con);
			}
		}
	
		for(int i = 0; i < conditionVars.size(); i++) {
			ArrayList<String> cal = new ArrayList<>();
			for(int j=0; j<states.size(); j++) {
				if(states.get(j).satisfies(new Atom(conditionVars.get(i)+conditionVars.get(i+1)))) {
					cal.add(states.get(j).toString());
				}
			}
			cals.add(cal);
			i++;
		}
		for(int j=0; j<states.size(); j++) {
			boolean verify = true ;
			for(int i=0; i<conditionVars.size(); i++) {
				if(!(states.get(j).satisfies(new Atom(conditionVars.get(i)+conditionVars.get(i+1))))) {
					verify = false;
				}
				i++;
			}
			if (!(states.get(j).satisfies(new Atom(variable1+value)))) {
				verify = false;
			}
			
			if(verify) {
				goalState = states.get(j).toString();
			}
			
		}
		for(int i=0; i<conditionOps.size();i++) {
			if(conditionOps.get(i).equals("&&")) {
				ArrayList<String> res1;
				res1 = uncasting(cals.get(i), cals.get(i+1));
				res = casting(res1, res);
			}
			
			if(conditionOps.get(i).equals("||")) {
				ArrayList<String> res1;
				res1 = casting(cals.get(i), cals.get(i+1));
				res = casting(res1, res);
			}
			i++;
		}
		
		for(int i=0; i<res.size();i++) {
			kS.addTransition(getState(res.get(i)), getState(goalState));
		}
	}
	
	public State getState(String stateName) {
		for(int i=0; i<states.size();i++) {
			if(stateName.equals(states.get(i).toString())) {
				return states.get(i);
			}
		}
		return null;
	}
	
    public <T> ArrayList<T> casting(ArrayList<T> list1, ArrayList<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public <T> ArrayList<T> uncasting(ArrayList<T> list1, ArrayList<T> list2) {
    	ArrayList<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    
    public boolean satisfies(Formula formula) {
    	RecursiveStateLabeling.getOptimizationNum(formula);
    	try {
			RecursiveStateLabeling.computeLabelsBDD(this, formula, kS, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return true;
    }

	

}
