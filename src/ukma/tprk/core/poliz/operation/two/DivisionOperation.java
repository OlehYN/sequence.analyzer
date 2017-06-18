package ukma.tprk.core.poliz.operation.two;

import java.util.List;

import ukma.tprk.core.poliz.operation.Operation;

public class DivisionOperation extends Operation {

    public DivisionOperation() {
        this.name = "DIVISION";
        this.value = "div";
        this.priority = 2;
        this.pattern = "div";
        this.isFunction = false;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return (int) (arguments.get(0) / arguments.get(1));
    }

}
