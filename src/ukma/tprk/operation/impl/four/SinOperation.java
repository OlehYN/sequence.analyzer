package ukma.tprk.operation.impl.four;

import java.util.List;

import ukma.tprk.operation.Operation;

public class SinOperation extends Operation {

    public SinOperation() {
        this.name = "SIN";
        this.value = "sin";
        this.priority = 4;
        this.pattern = "sin";
        this.isFunction = true;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return Math.sin(arguments.get(0));
    }

}
