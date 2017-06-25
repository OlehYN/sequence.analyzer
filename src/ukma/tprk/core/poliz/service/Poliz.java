package ukma.tprk.core.poliz.service;

import java.util.List;
import java.util.Stack;

import ukma.tprk.core.token.AbstractToken;

public interface Poliz {
	double calculate(Stack<AbstractToken<?>> stack);

	Stack<AbstractToken<?>> formStack(List<AbstractToken<?>> tokens);
}
