package ukma.tprk.core.automaton.state;

import ukma.tprk.core.automaton.TokenConstants;
import ukma.tprk.core.automaton.token.OpenToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class OpenState implements AutomatonState<OpenToken> {

	private boolean hasRead;

	@Override
	public boolean hasRead(char ch) {
		if (hasRead)
			return false;

		if (ch == TokenConstants.OPEN_TOKEN) {
			hasRead = true;
			return true;
		}
		return false;
	}

	@Override
	public OpenToken getValue() {
		if (hasRead)
			return new OpenToken();

		throw new UnfinishedReadingException("Open state");
	}

	@Override
	public Class<?>[] possibleNextStates() {
		return new Class<?>[] { OpenState.class, DoubleState.class, FunctionState.class, IterationState.class,
				SeparatorState.class };
	}

	@Override
	public void clean() {
		hasRead = false;
	}

}
