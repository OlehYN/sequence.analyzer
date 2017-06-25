package ukma.tprk.core.automaton.token;

import ukma.tprk.core.automaton.TokenConstants;

public class CloseToken implements AutomatonToken<Character> {

	@Override
	public Character getValue() {
		return TokenConstants.CLOSE_TOKEN;
	}

	@Override
	public String toString() {
		return TokenConstants.CLOSE_TOKEN + "";
	}
}
