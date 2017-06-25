package ukma.tprk.core.automaton.state;

import ukma.tprk.core.automaton.TokenConstants;
import ukma.tprk.core.automaton.token.SeparatorToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class SeparatorState implements AutomatonState<SeparatorToken> {

	private boolean hasRead;

	@Override
	public boolean hasRead(char ch) {
		if (hasRead && ch == TokenConstants.SEPARATOR_TOKEN)
			throw new IllegalArgumentException();

		if (ch == TokenConstants.SEPARATOR_TOKEN) {
			hasRead = true;
			return true;
		}
		return false;
	}

	@Override
	public SeparatorToken getValue() {
		if (hasRead)
			return new SeparatorToken();

		throw new UnfinishedReadingException("Separator value");

	}

	@Override
	public Class<?>[] possibleNextStates() {
		return new Class<?>[] { OpenState.class, DoubleState.class, FunctionState.class, IterationState.class };
	}

	@Override
	public void clean() {
		hasRead = false;
	}

}
