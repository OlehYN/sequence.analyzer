package ukma.tprk.core.automaton.state;

import java.util.List;

import ukma.tprk.core.automaton.token.FunctionToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class OperationState implements AutomatonState<FunctionToken> {

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
	public FunctionToken getValue() {
		for (String operationName : operationNames)
			if (operationName.equals(currentValue))
				return new FunctionToken(currentValue);

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
