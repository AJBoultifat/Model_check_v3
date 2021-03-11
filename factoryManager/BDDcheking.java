package factoryManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.State;

public class BDDcheking {
    private int ms = 0;
    private Map<State, Integer> ds = new HashMap<>();
    private Map<State, Integer> ll = new HashMap<>();
    private Stack<State> s = new Stack<>();

    public void looked(State state) {
        s.push(state);
        setDs(state, ms);
        setLL(state, ms);
        ms++;
    }

    public boolean isLooked(State state) {
        return ds.containsKey(state);
    }

    public int getDs(State state) {
        return ds.getOrDefault(state, 0);
    }

    public boolean dsEqLL(State state) {
        return getDs(state) == getLL(state);
    }

    public void setLL(State state, int lowlink) {
        this.ll.put(state, lowlink);
    }

    public int getLL(State state) {
        return ll.getOrDefault(state, 0);
    }

    public boolean isOnStack(State state) {
        return s.contains(state);
    }

    public State popS() {
        return s.pop();
    }

    public void removeS(State state) {
        s.remove(state);
    }

    public int getMs() {
        return ms;
    }

    public String getSString() {
        return s.toString();
    }

    private void setDs(State state, int dfs) {
        this.ds.put(state, dfs);
    }
}
