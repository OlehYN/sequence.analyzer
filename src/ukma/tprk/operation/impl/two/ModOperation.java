package ukma.tprk.operation.impl.two;

import java.util.List;

import ukma.tprk.operation.Operation;

public class ModOperation extends Operation {

    public ModOperation() {
        this.name = "MOD";
        this.value = "mod";
        this.priority = 2;
        this.pattern = "mod";
        this.isFunction = false;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return (arguments.get(0) % arguments.get(1));
    }

}
