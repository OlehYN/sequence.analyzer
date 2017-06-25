package ukma.tprk.core.token;

import ukma.tprk.core.automaton.TokenConstants;

public class CloseToken implements AbstractToken<Character> {

	@Override
	public Character getValue() {
		return TokenConstants.CLOSE_TOKEN;
	}

	@Override
	public String toString() {
		return TokenConstants.CLOSE_TOKEN + "";
	}
}
