package ukma.tprk.core.automaton.state;

import ukma.tprk.core.token.DoubleToken;
import ukma.tprk.exception.UnfinishedReadingException;

public class DoubleState implements AutomatonState<DoubleToken> {

	private String currentValue = "";

	private static final Character DOT_TOKEN = '.';
	private static final Character MINUS_TOKEN = '-';

	@Override
	public boolean hasRead(char ch) {

		if (Character.isDigit(ch)
				|| (!currentValue.contains(DOT_TOKEN + "") && ch == DOT_TOKEN && currentValue.length() > 0)
				|| (ch == MINUS_TOKEN && currentValue.length() == 0)) {
			currentValue += ch;
			return true;
		}

		return false;
	}

	@Override
	public DoubleToken getValue() {
		try {
			return new DoubleToken(Double.valueOf(currentValue));
		} catch (Exception e) {
			throw new UnfinishedReadingException("Doouble value: " + currentValue);
		}
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
