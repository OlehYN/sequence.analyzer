package ukma.tprk.operation.impl.four;

import java.util.List;

import ukma.tprk.operation.Operation;

public class MaxOperation extends Operation {

    public MaxOperation() {
        this.name = "MAX";
        this.value = "max";
        this.priority = 4;
        this.pattern = "max";
        this.isFunction = true;
        this.numberOfArguments = 2;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return Math.max(arguments.get(0), arguments.get(1));
    }

}
