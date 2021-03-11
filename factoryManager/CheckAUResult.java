package factoryManager;

import java.util.ArrayList;

import model.State;

public class CheckAUResult {
    private Continuation continuation;
    private ArrayList<State> counterExample;

    public CheckAUResult(Continuation continuation, State lastStateInCounterExample) {
        this.continuation = continuation;
        this.counterExample = new ArrayList<>();
        this.counterExample.add(lastStateInCounterExample);
    }

    public Continuation getSearchContinuation() {
        return continuation;
    }

    public ArrayList<State> getCounterExample() {
        return counterExample;
    }

    public void prependCounterExampleWith(State state) {
        counterExample.add(0, state);
    }
}
