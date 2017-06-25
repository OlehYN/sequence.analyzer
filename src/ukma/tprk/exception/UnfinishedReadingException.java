package ukma.tprk.exception;

public class UnfinishedReadingException extends RuntimeException {
	private static final long serialVersionUID = 6723882428321369727L;

	public UnfinishedReadingException(String message) {
		super(message);
	}
}
