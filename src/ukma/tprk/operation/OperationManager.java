package ukma.tprk.operation;

import java.util.ArrayList;
import java.util.List;

import ukma.tprk.operation.impl.four.CosOperation;
import ukma.tprk.operation.impl.four.FloorOperation;
import ukma.tprk.operation.impl.four.GreaterOperation;
import ukma.tprk.operation.impl.four.LessOperation;
import ukma.tprk.operation.impl.four.LnOperation;
import ukma.tprk.operation.impl.four.MaxOperation;
import ukma.tprk.operation.impl.four.RandOperation;
import ukma.tprk.operation.impl.four.SinOperation;
import ukma.tprk.operation.impl.four.TgOperation;
import ukma.tprk.operation.impl.one.AddOperation;
import ukma.tprk.operation.impl.one.MinusOperation;
import ukma.tprk.operation.impl.three.ExpOperation;
import ukma.tprk.operation.impl.two.DivOperation;
import ukma.tprk.operation.impl.two.DivisionOperation;
import ukma.tprk.operation.impl.two.ModOperation;
import ukma.tprk.operation.impl.two.MultipleOperation;

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
