package ukma.tprk.core.poliz.operation.four;

import java.util.List;

import ukma.tprk.core.poliz.operation.Operation;

public class LessOperation extends Operation {

    public LessOperation() {
        this.name = "LESS";
        this.value = "less";
        this.priority = 4;
        this.pattern = "less";
        this.isFunction = true;
        this.numberOfArguments = 2;
    }

    @Override
    public double performAction(List<Double> arguments) {
        if (arguments.get(0) <= arguments.get(1)) {
            return 1;
        }
        return 0;
    }

}
