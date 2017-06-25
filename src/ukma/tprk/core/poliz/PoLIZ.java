package ukma.tprk.core.poliz;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ukma.tprk.core.poliz.operation.Operation;
import ukma.tprk.core.poliz.operation.OperationManager;
import ukma.tprk.exception.IncorrectNumberOfArgumentsException;
import ukma.tprk.exception.OperationOverOperationException;

public class PoLIZ {

	private static final String SEPARATOR = ",";

	private static final String OPEN = "(";
	private static final String CLOSE = ")";

	public static double calculate(Stack<String> stack) {

		Integer prevSize = null;
		outer: while (stack.size() != 1) {

			if (prevSize != null && prevSize == stack.size()) {
				throw new IncorrectNumberOfArgumentsException("Incorrect arguments");
			}

			prevSize = stack.size();
			for (int i = 0; i < stack.size(); i++) {
				if (isOperation(stack.get(i))) {

					if (!isFunction(stack.get(i))) {
						if (i < 1) {
							throw new IncorrectNumberOfArgumentsException("Incorrect operation position");
						}

						String stringOperand1 = stack.get(i - 2);
						String stringOperand2 = stack.get(i - 1);

						if (!isOperand(stringOperand1) || !isOperand(stringOperand1))
							throw new OperationOverOperationException("Operand isn't correctly setted");

						double operand1 = Double.valueOf(stringOperand1);
						double operand2 = Double.valueOf(stringOperand2);

						double result = performOperation(stack.get(i), operand1, operand2);

						stack.set(i - 2, String.valueOf(result));
						stack.remove(i);
						stack.remove(i - 1);

						continue outer;
					} else if (isFunction(stack.get(i))) {
						int numberOfArguments = getNumberOfArguments(stack.get(i));
						List<Double> arguments = new ArrayList<>();

						for (int j = i - numberOfArguments; j < i; j++) {
							String stringOperand = stack.get(j);

							if (!isOperand(stringOperand))
								throw new OperationOverOperationException("Operand isn't correctly setted");

							double operand = Double.valueOf(stack.get(j));
							arguments.add(operand);
						}

						double result = performFunction(stack.get(i), arguments);

						for (int j = i, k = 0; k < numberOfArguments; k++) {
							stack.remove(j - k);
						}

						stack.set(i - numberOfArguments, String.valueOf(result));

						continue outer;
					}
				}
			}
		}
		return Double.valueOf(stack.get(0));
	}

	private static int getNumberOfArguments(String function) {
		for (Operation operation : OperationManager.operations) {
			if (operation.getValue().equals(function)) {
				return operation.getNumberOfArguments();
			}
		}

		return 1;
	}

	private static double performFunction(String function, List<Double> arguments) {
		for (Operation operation : OperationManager.operations) {
			if (operation.getValue().equals(function)) {
				return operation.performAction(arguments);
			}
		}

		throw new UnsupportedOperationException("No sucn function");
	}

	private static double performOperation(String function, double operand1, double operand2) {

		List<Double> arguments = new ArrayList<>();
		arguments.add(operand1);
		arguments.add(operand2);

		for (Operation operation : OperationManager.operations) {
			if (operation.getValue().equals(function)) {
				return operation.performAction(arguments);
			}
		}

		throw new UnsupportedOperationException("No such operation");
	}

	public static Stack<String> formStack(List<String> tokens) {
		Stack<String> result = new Stack<String>();
		Stack<String> operationStack = new Stack<String>();

		for (String token : tokens) {
			if (isSeparator(token)) {
				cleanStack(operationStack, result, false);
				continue;
			}

			if (isOperation(token)) {
				if (isOpen(token)) {
					operationStack.push(token);

				} else if (isClose(token)) {
					cleanStack(operationStack, result, true);

				} else if (!operationStack.isEmpty()) {

					String last = operationStack.lastElement();

					if (isOpen(token)) {
						operationStack.push(token);
					} else {
						int lastPriority = getPriority(last);
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
			String popValue = operationStack.pop();
			if (!isOpen(popValue)) {
				result.push(popValue);
			}
		}

		return result;
	}

	private static void cleanStack(Stack<String> operationStack, Stack<String> result, boolean close) {
		String lastValue;

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

	private static boolean isOperation(String token) {
		for (Operation operation : OperationManager.operations) {
			if (operation.getValue().equals(token)) {
				return true;
			}
		}
		return false || isOpen(token) || isClose(token);
	}

	private static boolean isSeparator(String token) {
		return SEPARATOR.equals(token);
	}

	private static boolean isOpen(String token) {
		return OPEN.equals(token);
	}

	private static boolean isClose(String token) {
		return CLOSE.equals(token);
	}

	private static boolean isOperand(String token) {
		try {
			Double.valueOf(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static boolean isFunction(String token) {
		for (Operation operation : OperationManager.operations) {
			if (operation.getValue().equals(token)) {
				return operation.isFunction();
			}
		}
		return false;
	}

	private static int getPriority(String token) {
		for (Operation operation : OperationManager.operations) {
			if (operation.getValue().equals(token)) {
				return operation.getPriority();
			}
		}
		return -1;
	}
}
