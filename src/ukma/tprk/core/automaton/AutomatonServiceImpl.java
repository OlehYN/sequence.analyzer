package ukma.tprk.core.automaton;

import java.util.ArrayList;
import java.util.List;

import ukma.tprk.core.automaton.state.AutomatonState;
import ukma.tprk.core.automaton.token.AutomatonToken;
import ukma.tprk.exception.InvalidFormulaException;

public class AutomatonServiceImpl implements AutomatonService {

	@Override
	public List<AutomatonToken<?>> getTokens(List<AutomatonState<?>> possibleStates, List<AutomatonState<?>> currentStates,
			String input) {

		List<AutomatonToken<?>> tokens = new ArrayList<>();

		outer: for (int i = 0; i < input.length(); i++) {
			for (int j = 0; j < currentStates.size(); j++) {
				AutomatonState<?> automatState = currentStates.get(j);
				if (automatState.hasRead(input.charAt(i))) {

					++i;

					while (i < input.length() && automatState.hasRead(input.charAt(i)))
						++i;

					tokens.add(automatState.getValue());
					--i;

					currentStates.clear();
					for (AutomatonState<?> possibleAutomatState : possibleStates)
						for (Class<?> stateClass : automatState.possibleNextStates())
							if (possibleAutomatState.getClass().equals(stateClass)
									&& !currentStates.contains(possibleAutomatState))
								currentStates.add(possibleAutomatState);

					automatState.clean();

					continue outer;
				}
			}

			throw new InvalidFormulaException("Automat didn't match current character" + input.charAt(i));
		}

		return tokens;
	}

}
