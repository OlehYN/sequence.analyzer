package ukma.tprk.core.automaton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ukma.tprk.core.automaton.state.AutomatonState;
import ukma.tprk.core.automaton.state.CloseState;
import ukma.tprk.core.automaton.state.DoubleState;
import ukma.tprk.core.automaton.state.FunctionState;
import ukma.tprk.core.automaton.state.IterationState;
import ukma.tprk.core.automaton.state.OpenState;
import ukma.tprk.core.automaton.state.OperationState;
import ukma.tprk.core.automaton.state.SeparatorState;

public class AutomatonConfiguration {
	public final static List<String> functionNames;
	public final static List<String> operationNames;

	static {

		List<String> modifiableFunctionNames = new ArrayList<>();
		modifiableFunctionNames.add("cos");
		modifiableFunctionNames.add("floor");
		modifiableFunctionNames.add("greater");
		modifiableFunctionNames.add("less");
		modifiableFunctionNames.add("ln");
		modifiableFunctionNames.add("max");
		modifiableFunctionNames.add("rand");
		modifiableFunctionNames.add("sin");
		modifiableFunctionNames.add("tg");

		functionNames = Collections.unmodifiableList(modifiableFunctionNames);

		List<String> modifiableOperationNames = new ArrayList<>();
		modifiableOperationNames.add("+");
		modifiableOperationNames.add("-");
		modifiableOperationNames.add("/");
		modifiableOperationNames.add("*");
		modifiableOperationNames.add("div");
		modifiableOperationNames.add("mod");
		modifiableOperationNames.add("^");

		operationNames = Collections.unmodifiableList(modifiableOperationNames);
	}

	public static List<AutomatonState<?>> getInitStates() {
		OpenState openState = new OpenState();
		DoubleState doubleState = new DoubleState();
		FunctionState functionState = new FunctionState(functionNames);
		IterationState iterationState = new IterationState();

		List<AutomatonState<?>> currentStates = new ArrayList<>();
		currentStates.add(openState);
		currentStates.add(functionState);
		currentStates.add(iterationState);
		currentStates.add(doubleState);

		return currentStates;
	}

	public static List<AutomatonState<?>> getPossibleStates() {

		CloseState closeState = new CloseState();
		DoubleState doubleState = new DoubleState();
		FunctionState functionState = new FunctionState(functionNames);
		IterationState iterationState = new IterationState();
		OpenState openState = new OpenState();
		OperationState operationState = new OperationState(operationNames);
		SeparatorState separatorState = new SeparatorState();

		List<AutomatonState<?>> possibleStates = new ArrayList<>();
		possibleStates.add(separatorState);
		possibleStates.add(operationState);
		possibleStates.add(openState);
		possibleStates.add(functionState);
		possibleStates.add(iterationState);
		possibleStates.add(doubleState);
		possibleStates.add(closeState);
		return possibleStates;
	}
}
