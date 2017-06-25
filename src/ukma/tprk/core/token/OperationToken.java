package ukma.tprk.core.token;

public class OperationToken implements AbstractToken<String> {

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
