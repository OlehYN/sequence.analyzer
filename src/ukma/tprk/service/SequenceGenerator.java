package ukma.tprk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ukma.tprk.core.lexer.Lexer;
import ukma.tprk.core.lexer.Token;
import ukma.tprk.core.poliz.PoLIZ;

public class SequenceGenerator {

	private static final String ITERATE_VAR = "{i}";
	private final List<Double> sequence;
	private final List<Token> tokens;
	private final Stack<String> currentStack;

	public SequenceGenerator(String input, int start, int end) {
		this.sequence = new ArrayList<Double>();

		this.tokens = Lexer.lex(input);
		this.currentStack = PoLIZ.formStack(tokens);

		for (int i = start; i < end; i++) {
			Stack<String> calculationStack = copyStack(currentStack);
			swapIterationNumber(calculationStack, i);

			sequence.add(calculate(calculationStack));
		}
	}

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

	public List<Double> getSequence() {
		return sequence;
	}
}
