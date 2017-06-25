package ukma.tprk.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ukma.tprk.core.automaton.AutomatonConfiguration;
import ukma.tprk.core.automaton.AutomatonService;
import ukma.tprk.core.automaton.AutomatonServiceImpl;
import ukma.tprk.core.automaton.state.AutomatonState;
import ukma.tprk.core.automaton.state.CloseState;
import ukma.tprk.core.automaton.state.DoubleState;
import ukma.tprk.core.automaton.state.FunctionState;
import ukma.tprk.core.automaton.state.IterationState;
import ukma.tprk.core.automaton.state.OpenState;
import ukma.tprk.core.automaton.state.OperationState;
import ukma.tprk.core.automaton.state.SeparatorState;
import ukma.tprk.core.token.AbstractToken;

public class AutomatonTest {

	@Test
	public void test1() {

		List<String> functionNames = AutomatonConfiguration.functionNames;
		List<String> operationNames = AutomatonConfiguration.operationNames;

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

		List<AutomatonState<?>> currentStates = new ArrayList<>();
		currentStates.add(openState);
		currentStates.add(functionState);
		currentStates.add(iterationState);
		currentStates.add(doubleState);

		String input = "greater({i}-2^(floor(ln({i})/ln(2))),2^(floor(ln({i})/ln(2)))div2)";

		AutomatonService automatService = new AutomatonServiceImpl();

		String result = "";
		for (AbstractToken<?> automatToken : automatService.getTokens(possibleStates, currentStates, input))
			result += automatToken.toString();

		System.out.println(result);
	}
}
