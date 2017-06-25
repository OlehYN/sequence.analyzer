package ukma.tprk.core.automaton.state;

import java.util.List;

import ukma.tprk.core.token.FunctionToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class FunctionState implements AutomatonState<FunctionToken> {

	private String currentValue = "";

	private List<String> functionNames;

	public FunctionState(List<String> functionNames) {
		this.functionNames = functionNames;
	}

	@Override
	public boolean hasRead(char ch) {

		currentValue += ch;
		for (String functionName : functionNames) {
			if (currentValue.length() > 1
					&& functionName.equals(currentValue.substring(0, currentValue.length() - 1))) {
				currentValue = currentValue.substring(0, currentValue.length() - 1);
				return false;
			} else if (functionName.startsWith(currentValue)) {
				return true;
			}
		}

		if (currentValue.length() > 1)
			throw new UnsupportedOperationException("Unknown function " + currentValue);

		clean();
		return false;
	}

	@Override
	public FunctionToken getValue() {
		for (String functionName : functionNames)
			if (functionName.equals(currentValue))
				return new FunctionToken(currentValue);

		throw new UnfinishedReadingException("Function value: " + currentValue);
	}

	@Override
	public Class<?>[] possibleNextStates() {
		return new Class<?>[] { OpenState.class };
	}

	@Override
	public void clean() {
		currentValue = "";
	}

}
