package ukma.tprk.core.automaton.token;

public class OperationToken implements AutomatonToken<String> {

	private String operationName;

	public OperationToken(String operationName) {
		this.operationName = operationName;
	}

	@Override
	public String getValue() {
		return operationName;
	}

	@Override
	public String toString() {
		return operationName;
	}

}
