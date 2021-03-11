package factoryManager;

import java.util.ArrayList;

import model.State;

public class CheckEUResult {
    private Continuation continuation;
    private ArrayList<State> witnessPath;

    public CheckEUResult(Continuation continuation, State lastStateInWitnessPath) {
        this.continuation = continuation;
        this.witnessPath = new ArrayList<>();
        this.witnessPath.add(lastStateInWitnessPath);
    }

    public Continuation getSearchContinuation() {
        return continuation;
    }

    public ArrayList<State> getWitnessPath() {
        return witnessPath;
    }

    public void prependWitnessPathWith(State state) {
        witnessPath.add(0, state);
    }
}
