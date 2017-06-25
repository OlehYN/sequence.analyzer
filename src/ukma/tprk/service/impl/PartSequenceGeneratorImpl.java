package ukma.tprk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ukma.tprk.core.automaton.AutomatonConfiguration;
import ukma.tprk.core.automaton.AutomatonService;
import ukma.tprk.core.automaton.AutomatonServiceImpl;
import ukma.tprk.core.automaton.token.AutomatonToken;
import ukma.tprk.core.poliz.PoLIZ;
import ukma.tprk.service.PartSequenceGenerator;

public class PartSequenceGeneratorImpl implements PartSequenceGenerator {

	private static final String ITERATE_VAR = "{i}";
	private AutomatonService automatService = new AutomatonServiceImpl();

	private Stack<String> copyStack(Stack<String> sourceStack) {
		Stack<String> stack = new Stack<>();
		stack.addAll(sourceStack);

		return stack;
	}

	private void swapIterationNumber(Stack<String> calculationStack, int number) {
		for (int i = 0; i < calculationStack.size(); i++) {
			if (calculationStack.get(i).equals(ITERATE_VAR)) {
				calculationStack.set(i, String.valueOf(number));
			}
		}
	}

	private double calculate(Stack<String> calculationStack) {
		return PoLIZ.calculate(calculationStack);
	}

	public List<Double> getSequence(String input, int start, int end) {
		List<Double> sequence = new ArrayList<Double>();

		List<String> lexems = new ArrayList<>();

		for (AutomatonToken<?> automatToken : automatService.getTokens(AutomatonConfiguration.getPossibleStates(),
				AutomatonConfiguration.getInitStates(), input.replace(" ", ""))) {
			lexems.add(automatToken.toString());
		}

		Stack<String> currentStack = PoLIZ.formStack(lexems);

		for (int i = start; i < end; i++) {
			Stack<String> calculationStack = copyStack(currentStack);
			swapIterationNumber(calculationStack, i);

			sequence.add(calculate(calculationStack));
		}

		return sequence;
	}
}
