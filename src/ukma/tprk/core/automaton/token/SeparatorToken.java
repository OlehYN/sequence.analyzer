package ukma.tprk.core.automaton.token;

import ukma.tprk.core.automaton.TokenConstants;

public class SeparatorToken implements AutomatonToken<Character> {

	@Override
	public Character getValue() {
		return TokenConstants.SEPARATOR_TOKEN;
	}

	@Override
	public String toString() {
		return TokenConstants.SEPARATOR_TOKEN + "";
	}
}
