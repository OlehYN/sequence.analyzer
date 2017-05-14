package ukma.tprk.operation.impl.four;

import java.util.List;

import ukma.tprk.operation.Operation;

public class FloorOperation extends Operation {

    public FloorOperation() {
        this.name = "FLOOR";
        this.value = "floor";
        this.priority = 4;
        this.pattern = "floor";
        this.isFunction = true;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return Math.floor(arguments.get(0));
    }

}
