package ukma.tprk.operation.impl.four;

import java.util.List;

import ukma.tprk.operation.Operation;

public class TgOperation extends Operation {

    public TgOperation() {
        this.name = "TG";
        this.value = "tg";
        this.priority = 4;
        this.pattern = "tg";
        this.isFunction = true;
        this.numberOfArguments = 1;
    }

    @Override
    public double performAction(List<Double> arguments) {
        return Math.tan(arguments.get(0));
    }

}
