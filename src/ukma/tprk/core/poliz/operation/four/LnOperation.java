package ukma.tprk.core.poliz.operation.four;

import java.util.List;

import ukma.tprk.core.poliz.operation.Operation;

public class LnOperation extends Operation {

    public LnOperation() {
        this.name = "LN";
        this.value = "ln";
        this.priority = 4;
        this.pattern = "ln";
        this.isFunction = true;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return Math.log(arguments.get(0));
    }

}
