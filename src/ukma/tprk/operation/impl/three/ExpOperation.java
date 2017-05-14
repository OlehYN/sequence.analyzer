package ukma.tprk.operation.impl.three;

import java.util.List;

import ukma.tprk.operation.Operation;

public class ExpOperation extends Operation {

    public ExpOperation() {
        this.name = "EXP";
        this.value = "^";
        this.priority = 3;
        this.pattern = "[\\^]";
        this.isFunction = false;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return Math.pow(arguments.get(0), arguments.get(1));
    }

}
