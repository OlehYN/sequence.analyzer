package ukma.tprk.core.automaton.state;

import ukma.tprk.core.token.AbstractToken;

public interface AutomatonState<T extends AbstractToken<?>> {
	boolean hasRead(char ch);

	T getValue();

	Class<?>[] possibleNextStates();

	void clean();
}
