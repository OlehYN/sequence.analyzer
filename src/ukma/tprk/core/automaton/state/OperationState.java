package ukma.tprk.core.automaton.state;

import java.util.List;

import ukma.tprk.core.token.OperationToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class OperationState implements AutomatonState<OperationToken> {

	private String currentValue = "";

	private List<String> operationNames;

	public OperationState(List<String> operationNames) {
		this.operationNames = operationNames;
	}

	@Override
	public boolean hasRead(char ch) {

		currentValue += ch;
		for (String operationName : operationNames) {
			if (currentValue.length() > 1
					&& operationName.equals(currentValue.substring(0, currentValue.length() - 1))) {
				currentValue = currentValue.substring(0, currentValue.length() - 1);
				return false;
			} else if (operationName.startsWith(currentValue)) {
				return true;
			}
		}

		if (currentValue.length() > 1) {
			throw new UnsupportedOperationException("Unknown operation" + currentValue);
		}

		clean();
		return false;
	}

	@Override
	public OperationToken getValue() {
		for (String operationName : operationNames)
			if (operationName.equals(currentValue))
				return new OperationToken(currentValue);

		throw new UnfinishedReadingException("Operation value: " + currentValue);
	}

	@Override
	public Class<?>[] possibleNextStates() {
		return new Class<?>[] { OpenState.class, DoubleState.class, FunctionState.class, IterationState.class };
	}

	@Override
	public void clean() {
		currentValue = "";
	}

}
