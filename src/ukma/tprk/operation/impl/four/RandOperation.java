package ukma.tprk.operation.impl.four;

import java.util.List;
import java.util.Random;

import ukma.tprk.operation.Operation;

public class RandOperation extends Operation {

    public RandOperation() {
        this.name = "RAND";
        this.value = "rand";
        this.priority = 4;
        this.pattern = "rand";
        this.isFunction = true;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return new Random().nextInt(arguments.get(0).intValue());
    }

}
