package factoryManager;

import java.io.FileReader;
import java.io.Reader;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Access {

    private static Logger log = Logger.getLogger(Access.class.getName());
    
    private Model model;
    
    public void loadModel(String filename) {
        log.log(Level.FINE, "Loading model: " + filename);
        try {
            FileReader reader = new FileReader(filename);
        } catch (Exception e) {
            throw new ProgramErrorException(e);
        }
    }

    public String getEFPFormula(boolean isConjunctive) {
        return model.getEFPFormula(isConjunctive);
    }
    
    public SortedSet<String> getModelVariables() {
        return model.getVariables();
    }
    
    public Set<Set<String>> getLabelsSet(String formula) {
        StateLabeling sl = new RecursiveStateLabeling(model);
        Set<Set<String>> labels = sl.computeLabels(formula);
        return labels;
    }
    
    public ModelCheckingResults getLabelsArray(String formula) {
        StateLabeling sl = new RecursiveStateLabeling(model);
        ModelCheckingResults results = sl.computeLabels2(formula);
        return results;
    }
    
    public boolean checkCTLFormulaSyntax(String formula) {
        return true;
    }
}
