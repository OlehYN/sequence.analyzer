package ukma.tprk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ukma.tprk.core.automaton.AutomatonConfiguration;
import ukma.tprk.core.automaton.AutomatonService;
import ukma.tprk.core.automaton.AutomatonServiceImpl;
import ukma.tprk.core.poliz.service.impl.PolizImpl;
import ukma.tprk.core.token.AbstractToken;
import ukma.tprk.core.token.DoubleToken;
import ukma.tprk.core.token.IterationToken;
import ukma.tprk.service.PartSequenceGenerator;

public class PartSequenceGeneratorImpl implements PartSequenceGenerator {

	private AutomatonService automatService = new AutomatonServiceImpl();

	private PolizImpl poliz = new PolizImpl();

	private Stack<AbstractToken<?>> copyStack(Stack<AbstractToken<?>> sourceStack) {
		Stack<AbstractToken<?>> stack = new Stack<>();
		stack.addAll(sourceStack);

		return stack;
	}

	private void swapIterationNumber(Stack<AbstractToken<?>> calculationStack, int number) {
		for (int i = 0; i < calculationStack.size(); i++) {
			if (calculationStack.get(i) instanceof IterationToken) {
				calculationStack.set(i, new DoubleToken(number));
			}
		}
	}

	private double calculate(Stack<AbstractToken<?>> calculationStack) {
		return poliz.calculate(calculationStack);
	}

	public List<Double> getSequence(String input, int start, int end) {
		List<Double> sequence = new ArrayList<Double>();

		List<AbstractToken<?>> tokens = automatService.getTokens(AutomatonConfiguration.getPossibleStates(),
				AutomatonConfiguration.getInitStates(), input.replace(" ", ""));

		Stack<AbstractToken<?>> currentStack = poliz.formStack(tokens);

		for (int i = start; i < end; i++) {
			Stack<AbstractToken<?>> calculationStack = copyStack(currentStack);
			swapIterationNumber(calculationStack, i);

			sequence.add(calculate(calculationStack));
		}

		return sequence;
	}
}
