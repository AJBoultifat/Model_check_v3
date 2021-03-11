package mainpack;

import static ctlFormula.Atom.*;
import static ctlOperator.EF.EF;
import static ctlOperator.AU.AU;
import static ctlOperator.EF.EF;

import ctlFormula.Formula;
import exceptions.StateAssignment;
import model.KripkeStructure;
import model.ModelBoolVariable;
import model.State;

public class ExampleMain {
    public static void main(String[] args) {

        ModelBoolVariable m = new ModelBoolVariable();
        m.CreateVariable("a", "1", "2", "3");
        m.CreateVariable("b", "1", "2", "3","4");
        m.CreateVariable("c", "5", "6");
        m.CreateModel();
        m.condition("a","1","b","1","&&","c","5");
        Formula formula = AU(predicate("b","2"),predicate("b", "5"));
        m.satisfies(formula);
    }
}
