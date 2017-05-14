package ukma.tprk.operation.impl.two;

import java.util.List;

import ukma.tprk.operation.Operation;

public class DivOperation extends Operation {

    public DivOperation() {
        this.name = "DIV";
        this.value = "/";
        this.priority = 2;
        this.pattern = "[\\/]";
        this.isFunction = false;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return arguments.get(0) / arguments.get(1);
    }

}
