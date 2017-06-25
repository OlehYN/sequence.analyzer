package ukma.tprk.exception;

public class IncorrectOperationException extends RuntimeException {

	private static final long serialVersionUID = 6723882428321369727L;

	public IncorrectOperationException(String message) {
		super(message);
	}
}