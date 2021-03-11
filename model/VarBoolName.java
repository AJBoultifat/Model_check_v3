package model;

import java.util.ArrayList;

public class VarBoolName {

	private String varName = "";
	private ArrayList<String> varValues = new ArrayList<String>();
	private boolean varCondition;

	public VarBoolName(String varName, ArrayList<String> varValues) {
		this.varName = varName;
		this.varValues = varValues;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public ArrayList<String> getVarValues() {
		return varValues;
	}

	public void setVarValues(ArrayList<String> varValues) {
		this.varValues = varValues;
	}

	public void setVarCondition(boolean varCondition) {
		this.varCondition = varCondition;
	}


	public boolean isVarCondition() {
		return varCondition;
	}

	public void VarCondition(boolean varCondition) {
		this.varCondition = varCondition;
	}

}
