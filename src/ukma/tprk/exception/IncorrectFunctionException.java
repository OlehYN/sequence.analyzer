package ukma.tprk.exception;

public class IncorrectFunctionException extends RuntimeException {

	private static final long serialVersionUID = 6723882428321369727L;

	public IncorrectFunctionException(String message) {
		super(message);
	}
}