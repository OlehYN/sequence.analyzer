package ukma.tprk.core.automaton.state;

import ukma.tprk.core.automaton.TokenConstants;
import ukma.tprk.core.automaton.token.CloseToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class CloseState implements AutomatonState<CloseToken> {

	private boolean hasRead;

	@Override
	public boolean hasRead(char ch) {
		if (hasRead)
			return false;

		if (ch == TokenConstants.CLOSE_TOKEN) {
			hasRead = true;
			return true;
		}
		return false;
	}

	@Override
	public CloseToken getValue() {
		if (hasRead)
			return new CloseToken();

		throw new UnfinishedReadingException("Close state");
	}

	@Override
	public Class<?>[] possibleNextStates() {
		return new Class<?>[] { CloseState.class, OperationState.class, SeparatorState.class };
	}

	@Override
	public void clean() {
		hasRead = false;
	}

}
