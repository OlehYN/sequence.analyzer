package ukma.tprk.core.automaton;

import java.util.List;

import ukma.tprk.core.automaton.state.AutomatonState;
import ukma.tprk.core.automaton.token.AutomatonToken;

public interface AutomatonService {
	List<AutomatonToken<?>> getTokens(List<AutomatonState<?>> possibleStates, List<AutomatonState<?>> currentStates,
			String input);
}
