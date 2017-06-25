package ukma.tprk.core.token;

import ukma.tprk.core.automaton.TokenConstants;

public class OpenToken implements AbstractToken<Character> {

	@Override
	public Character getValue() {
		return TokenConstants.OPEN_TOKEN;
	}

	@Override
	public String toString() {
		return TokenConstants.OPEN_TOKEN + "";
	}
}
