package ukma.tprk.core.poliz.operation.four;

import java.util.List;

import ukma.tprk.core.poliz.operation.Operation;

public class CosOperation extends Operation {

    public CosOperation() {
        this.name = "COS";
        this.value = "cos";
        this.priority = 4;
        this.pattern = "cos";
        this.isFunction = true;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return Math.cos(arguments.get(0));
    }

}
