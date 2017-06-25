package ukma.tprk.core.token;

public class FunctionToken implements AbstractToken<String> {

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
