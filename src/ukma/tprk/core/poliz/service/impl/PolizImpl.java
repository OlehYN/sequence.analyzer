package ukma.tprk.core.poliz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ukma.tprk.core.poliz.operation.Operation;
import ukma.tprk.core.poliz.operation.OperationManager;
import ukma.tprk.core.poliz.service.Poliz;
import ukma.tprk.core.token.AbstractToken;
import ukma.tprk.core.token.CloseToken;
import ukma.tprk.core.token.DoubleToken;
import ukma.tprk.core.token.FunctionToken;
import ukma.tprk.core.token.OpenToken;
import ukma.tprk.core.token.OperationToken;
import ukma.tprk.core.token.SeparatorToken;
import ukma.tprk.exception.IncorrectFunctionException;
import ukma.tprk.exception.IncorrectNumberOfArgumentsException;
import ukma.tprk.exception.IncorrectOperationException;
import ukma.tprk.exception.OperationOverOperationException;

public class PolizImpl implements Poliz {

	public double calculate(Stack<AbstractToken<?>> stack) {

		Integer prevSize = null;
		outer: while (stack.size() != 1) {

			if (prevSize != null && prevSize == stack.size()) {
				throw new IncorrectNumberOfArgumentsException("Incorrect arguments");
			}

			prevSize = stack.size();
			for (int i = 0; i < stack.size(); i++) {
				AbstractToken<?> currentToken = stack.get(i);

				if (isOperation(currentToken)) {
					if (i < 1) {
						throw new IncorrectNumberOfArgumentsException("Incorrect operation position");
					}

					AbstractToken<?> tokenOperand1 = stack.get(i - 2);
					AbstractToken<?> tokenOperand2 = stack.get(i - 1);

					if (!isOperand(tokenOperand1) || !isOperand(tokenOperand2))
						throw new OperationOverOperationException("Operand isn't correctly setted");

					double operand1 = ((DoubleToken) tokenOperand1).getValue();
					double operand2 = ((DoubleToken) tokenOperand2).getValue();

					double result = performOperation(stack.get(i), operand1, operand2);

					stack.set(i - 2, new DoubleToken(result));
					stack.remove(i);
					stack.remove(i - 1);

					continue outer;
				} else if (isFunction(currentToken)) {
					int numberOfArguments = getNumberOfArguments((FunctionToken) currentToken);
					List<Double> arguments = new ArrayList<>();

					for (int j = i - numberOfArguments; j < i; j++) {
						AbstractToken<?> abstractToken = stack.get(j);

						if (!isOperand(abstractToken))
							throw new OperationOverOperationException("Operand isn't correctly setted");

						double operand = ((DoubleToken) abstractToken).getValue();
						arguments.add(operand);
					}

					double result = performFunction((FunctionToken) currentToken, arguments);

					for (int j = i, k = 0; k < numberOfArguments; k++) {
						stack.remove(j - k);
					}

					stack.set(i - numberOfArguments, new DoubleToken(result));

					continue outer;
				}
			}
		}
		return ((DoubleToken) stack.get(0)).getValue();
	}

	private int getNumberOfArguments(FunctionToken functionToken) {
		for (Operation operation : OperationManager.operations)
			if (operation.getValue().equals(functionToken.getValue()) && operation.isFunction())
				return operation.getNumberOfArguments();

		throw new IncorrectFunctionException("No such function");
	}

	private double performFunction(FunctionToken functionToken, List<Double> arguments) {
		for (Operation operation : OperationManager.operations)
			if (operation.getValue().equals(functionToken.getValue()) && operation.isFunction())
				return operation.performAction(arguments);

		throw new IncorrectFunctionException("No such function");
	}

	private double performOperation(AbstractToken<?> actionToken, double operand1, double operand2) {

		List<Double> arguments = new ArrayList<>();
		arguments.add(operand1);
		arguments.add(operand2);

		for (Operation operation : OperationManager.operations)
			if (operation.getValue().equals(actionToken.getValue()) && !operation.isFunction())
				return operation.performAction(arguments);

		throw new UnsupportedOperationException("No such operation");
	}

	public Stack<AbstractToken<?>> formStack(List<AbstractToken<?>> tokens) {
		Stack<AbstractToken<?>> result = new Stack<>();
		Stack<AbstractToken<?>> operationStack = new Stack<>();

		for (AbstractToken<?> token : tokens) {
			if (isSeparator(token)) {
				cleanStack(operationStack, result, false);
				continue;
			}

			if (isCleanOperation(token)) {
				if (isOpen(token)) {
					operationStack.push(token);

				} else if (isClose(token)) {
					cleanStack(operationStack, result, true);

				} else if (!operationStack.isEmpty()) {

					AbstractToken<?> last = operationStack.lastElement();

					if (isOpen(token)) {
						operationStack.push(token);
					} else {
						int lastPriority = last instanceof OpenToken ? -1 : getPriority(last);
						int currentPriority = getPriority(token);

						if (currentPriority <= lastPriority) {
							cleanStack(operationStack, result, false);
							operationStack.push(token);
						} else {
							operationStack.push(token);
						}
					}
				} else {
					operationStack.push(token);
				}

			} else {
				result.push(token);
			}
		}

		while (!operationStack.isEmpty()) {
			AbstractToken<?> popValue = operationStack.pop();
			if (!isOpen(popValue))
				result.push(popValue);
		}

		return result;
	}

	private boolean isCleanOperation(AbstractToken<?> token) {
		return token instanceof CloseToken || token instanceof OpenToken || token instanceof OperationToken
				|| token instanceof FunctionToken;
	}

	private void cleanStack(Stack<AbstractToken<?>> operationStack, Stack<AbstractToken<?>> result, boolean close) {
		AbstractToken<?> lastValue;

		if (!operationStack.isEmpty()) {
			do {
				lastValue = operationStack.lastElement();

				if (!isOpen(lastValue)) {
					result.push(lastValue);
					operationStack.pop();
				} else if (close) {
					operationStack.pop();
				}

			} while (!isOpen(lastValue) && !operationStack.isEmpty());
		}

		if (close && !operationStack.isEmpty()) {
			lastValue = operationStack.lastElement();

			if (isFunction(lastValue)) {
				result.push(lastValue);
				operationStack.pop();
			}
		}
	}

	private boolean isOperation(AbstractToken<?> token) {
		if (!(token instanceof OperationToken))
			return false;

		for (Operation operation : OperationManager.operations)
			if (operation.getValue().equals(token.getValue()) && !operation.isFunction())
				return true;

		throw new IncorrectOperationException(token.getValue().toString());
	}

	private boolean isSeparator(AbstractToken<?> token) {
		return token instanceof SeparatorToken;
	}

	private boolean isOpen(AbstractToken<?> token) {
		return token instanceof OpenToken;
	}

	private boolean isClose(AbstractToken<?> token) {
		return token instanceof CloseToken;
	}

	private boolean isOperand(AbstractToken<?> token) {
		return token instanceof DoubleToken;
	}

	private boolean isFunction(AbstractToken<?> token) {
		if (!(token instanceof FunctionToken))
			return false;

		for (Operation operation : OperationManager.operations)
			if (operation.getValue().equals(token.getValue()) && operation.isFunction())
				return true;

		throw new IncorrectFunctionException(token.getValue().toString());
	}

	private int getPriority(AbstractToken<?> token) {
		if (!(token instanceof FunctionToken) && !(token instanceof OperationToken))
			throw new IllegalArgumentException();

		for (Operation operation : OperationManager.operations)
			if (operation.getValue().equals(token.getValue()))
				return operation.getPriority();

		throw new IncorrectFunctionException(token.getValue().toString());
	}
}
