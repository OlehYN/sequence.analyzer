package ukma.tprk.core.automaton.state;

import ukma.tprk.core.automaton.token.AutomatonToken;

public interface AutomatonState<T extends AutomatonToken<?>> {
	boolean hasRead(char ch);

	T getValue();

	Class<?>[] possibleNextStates();

	void clean();
}
