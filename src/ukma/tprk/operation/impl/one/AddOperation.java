package ukma.tprk.operation.impl.one;

import java.util.List;

import ukma.tprk.operation.Operation;

public class AddOperation extends Operation {

    public AddOperation() {
        this.name = "PLUS";
        this.value = "+";
        this.priority = 1;
        this.pattern = "[\\+]";
        this.isFunction = false;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return arguments.get(0) + arguments.get(1);
    }

}
