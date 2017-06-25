package ukma.tprk.core.automaton.state;

import ukma.tprk.core.token.IterationToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class IterationState implements AutomatonState<IterationToken> {

	private IterationToken iterationToken = new IterationToken();
	private String etalonValue = iterationToken.getValue();

	private String currentValue = "";

	@Override
	public boolean hasRead(char ch) {

		if (!currentValue.equals(etalonValue) && etalonValue.charAt(currentValue.length()) == ch) {
			currentValue += ch;
			return true;
		}

		if (currentValue.length() > 0 && !currentValue.equals(etalonValue))
			throw new IllegalArgumentException("Partial element");

		return false;
	}

	@Override
	public IterationToken getValue() {
		if (currentValue.equals(etalonValue))
			return new IterationToken();

		throw new UnfinishedReadingException("Iteration	 value: " + currentValue);
	}

	@Override
	public Class<?>[] possibleNextStates() {
		return new Class<?>[] { CloseState.class, OperationState.class, SeparatorState.class };
	}

	@Override
	public void clean() {
		currentValue = "";
	}

}
