package ukma.tprk.core.automaton.token;

public class FunctionToken implements AutomatonToken<String> {

	private String functionName;

	public FunctionToken(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public String getValue() {
		return functionName;
	}

	@Override
	public String toString() {
		return functionName;
	}
}
