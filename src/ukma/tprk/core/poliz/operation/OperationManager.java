package ukma.tprk.core.poliz.operation;

import java.util.ArrayList;
import java.util.List;

import ukma.tprk.core.poliz.operation.four.CosOperation;
import ukma.tprk.core.poliz.operation.four.FloorOperation;
import ukma.tprk.core.poliz.operation.four.GreaterOperation;
import ukma.tprk.core.poliz.operation.four.LessOperation;
import ukma.tprk.core.poliz.operation.four.LnOperation;
import ukma.tprk.core.poliz.operation.four.MaxOperation;
import ukma.tprk.core.poliz.operation.four.RandOperation;
import ukma.tprk.core.poliz.operation.four.SinOperation;
import ukma.tprk.core.poliz.operation.four.TgOperation;
import ukma.tprk.core.poliz.operation.one.AddOperation;
import ukma.tprk.core.poliz.operation.one.MinusOperation;
import ukma.tprk.core.poliz.operation.three.ExpOperation;
import ukma.tprk.core.poliz.operation.two.DivOperation;
import ukma.tprk.core.poliz.operation.two.DivisionOperation;
import ukma.tprk.core.poliz.operation.two.ModOperation;
import ukma.tprk.core.poliz.operation.two.MultipleOperation;

public class OperationManager {

    public static final List<Operation> operations;

    static {
        operations = new ArrayList<Operation>();

        operations.add(new AddOperation());
        operations.add(new MinusOperation());

        operations.add(new DivisionOperation());
        operations.add(new DivOperation());
        operations.add(new ModOperation());
        operations.add(new MultipleOperation());

        operations.add(new ExpOperation());

        operations.add(new CosOperation());
        operations.add(new FloorOperation());
        operations.add(new GreaterOperation());
        operations.add(new LessOperation());
        operations.add(new LnOperation());
        operations.add(new MaxOperation());
        operations.add(new RandOperation());
        operations.add(new SinOperation());
        operations.add(new TgOperation());
    }
}
