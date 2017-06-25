package ukma.tprk.core.automaton.token;

import ukma.tprk.core.automaton.TokenConstants;

public class IterationToken implements AutomatonToken<String> {

	@Override
	public String getValue() {
		return TokenConstants.ITERATION_TOKEN;
	}

	@Override
	public String toString() {
		return TokenConstants.ITERATION_TOKEN;
	}
}
