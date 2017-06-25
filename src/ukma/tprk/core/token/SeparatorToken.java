package ukma.tprk.core.token;

import ukma.tprk.core.automaton.TokenConstants;

public class SeparatorToken implements AbstractToken<Character> {

	@Override
	public Character getValue() {
		return TokenConstants.SEPARATOR_TOKEN;
	}

	@Override
	public String toString() {
		return TokenConstants.SEPARATOR_TOKEN + "";
	}
}
