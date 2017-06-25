package ukma.tprk.core.automaton.token;

import ukma.tprk.core.automaton.TokenConstants;

public class OpenToken implements AutomatonToken<Character> {

	@Override
	public Character getValue() {
		return TokenConstants.OPEN_TOKEN;
	}

	@Override
	public String toString() {
		return TokenConstants.OPEN_TOKEN + "";
	}
}
