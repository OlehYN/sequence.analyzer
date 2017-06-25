package ukma.tprk.core.automaton;

import java.util.List;

import ukma.tprk.core.automaton.state.AutomatonState;
import ukma.tprk.core.token.AbstractToken;

public interface AutomatonService {
	List<AbstractToken<?>> getTokens(List<AutomatonState<?>> possibleStates, List<AutomatonState<?>> currentStates,
			String input);
}
