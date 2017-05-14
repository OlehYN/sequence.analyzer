package ukma.tprk.operation.impl.four;

import java.util.List;

import ukma.tprk.operation.Operation;

public class GreaterOperation extends Operation {

    public GreaterOperation() {
        this.name = "GREATER";
        this.value = "greater";
        this.priority = 4;
        this.pattern = "greater";
        this.isFunction = true;
        this.numberOfArguments = 2;
    }

    @Override
    public double performAction(List<Double> arguments) {
        if (arguments.get(0) >= arguments.get(1)) {
            return 1;
        }
        return 0;
    }

}
