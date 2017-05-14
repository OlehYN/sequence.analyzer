package ukma.tprk.operation;

import java.util.List;

public abstract class Operation {

    protected String name;
    protected String pattern;
    protected String value;

    protected boolean isFunction;
    protected int priority;
    protected int numberOfArguments;

    public abstract double performAction(List<Double> arguments);

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    public int getNumberOfArguments() {
        return numberOfArguments;
    }

    public boolean isFunction() {
        return isFunction;
    }

    public String getPattern() {
        return pattern;
    }

    public String getName() {
        return name;
    }
}
